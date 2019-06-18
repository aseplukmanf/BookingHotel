package com.xirpla.remedial.bookinghotel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private EditText valNmLkp, valUsername,
            valPass, valConPass, valEmail, valAlamat;
    private Button btnRegister;

    // ip address laptop/pc
    private static String URL = "http://192.168.43.228/api_android/htl/register.php";

    private String txtNmLkp, nama_user, password, konfirmasi_password, email, alamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        valNmLkp = findViewById(R.id.valNmLkp);
        valUsername = findViewById(R.id.valUsername);
        valPass = findViewById(R.id.valPass);
        valConPass = findViewById(R.id.valConPass);
        valEmail = findViewById(R.id.valEmail);
        valAlamat = findViewById(R.id.valAlamat);

        btnRegister = findViewById(R.id.btnDaftar);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtNmLkp = valNmLkp.getText().toString().trim();
                nama_user = valUsername.getText().toString().trim();
                password = valPass.getText().toString().trim();
                konfirmasi_password = valConPass.getText().toString().trim();
                email = valEmail.getText().toString().trim();
                alamat = valAlamat.getText().toString().trim();
                if(txtNmLkp.isEmpty()) {
                    valNmLkp.setError("Nama harus diisi!");
                }else if(nama_user.isEmpty()){
                    valUsername.setError("Username harus diisi!");
                }else if(password.isEmpty()){
                    valPass.setError("Password harus diisi!");
                }else if(!konfirmasi_password.equals(password)){
                    valConPass.setError("Konfirmasi password harus sama!");
                }else if(email.isEmpty()){
                    valEmail.setError("Email harus diisi!");
                }else if(alamat.isEmpty()){
                    valAlamat.setError("Alamat harus diisi!");
                }else{
                    Registrasi();
                }

            }
        });
    }

    private void Registrasi() {
        txtNmLkp = this.valNmLkp.getText().toString().trim();
        nama_user = this.valUsername.getText().toString().trim();
        password = this.valPass.getText().toString().trim();
        konfirmasi_password = this.valConPass.getText().toString().trim();
        alamat = this.valAlamat.getText().toString().trim();
        email = this.valEmail.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if(success.equals("1")){
                                Toast.makeText(RegisterActivity.this, "Register Success!", Toast.LENGTH_SHORT).show();
                                Intent pindah_login = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(pindah_login);
                            }
                        }catch(JSONException e){
                            e.printStackTrace();
                            Toast.makeText(RegisterActivity.this, "Register Error : " +e.toString(), Toast.LENGTH_SHORT).show();
                            btnRegister.setVisibility(View.VISIBLE);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegisterActivity.this, "Register Error : " +error.toString(), Toast.LENGTH_SHORT).show();
                btnRegister.setVisibility(View.VISIBLE);
            }
        })
        {
            @Override
            protected Map<String, String> getParams () throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nama_lengkap", txtNmLkp);
                params.put("username", nama_user);
                params.put("password", password);
                params.put("email", email);
                params.put("alamat", alamat);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

}
