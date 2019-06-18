package com.xirpla.remedial.bookinghotel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ProfilActivity extends AppCompatActivity {

    private TextView txtNamaUser, txtUsername, txtEmail, txtALamat, txtLogout;

    private RequestQueue requestQueue;
    private StringRequest stringRequest;

    ArrayList<HashMap<String, String>> list_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        String url = "http:// 192.168.43.228/api_android/htl/data_user.php";

        txtNamaUser = (TextView) findViewById(R.id.txtNamaUser);
        txtUsername = (TextView)findViewById(R.id.txtUsername);
        txtEmail = (TextView)findViewById(R.id.txtEmail);
        txtALamat = (TextView)findViewById(R.id.txtALamat);
        txtLogout = (TextView)findViewById(R.id.txtLogout);
        txtLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logout = new Intent(ProfilActivity.this,LoginActivity.class);
                startActivity(logout);
            }
        });

        requestQueue = Volley.newRequestQueue(ProfilActivity.this);

        list_data = new ArrayList<HashMap<String, String>>();

        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("user");
                    for (int a = 0; a < jsonArray.length(); a ++){
                        JSONObject json = jsonArray.getJSONObject(a);
                        HashMap<String, String> map  = new HashMap<String, String>();
                        map.put("username", json.getString("username"));
                        map.put("nama_lengkap", json.getString("nama_lengkap"));
                        map.put("email", json.getString("email"));
                        map.put("alamat", json.getString("alamat"));
                        list_data.add(map);
                    }

                    txtNamaUser.setText(list_data.get(0).get("nama_lengkap"));
                    txtUsername.setText(list_data.get(0).get("username"));
                    txtEmail.setText(list_data.get(0).get("email"));
                    txtALamat.setText(list_data.get(0).get("alamat"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ProfilActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);
    }
    }

