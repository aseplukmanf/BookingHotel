package com.xirpla.remedial.bookinghotel;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import android.widget.DatePicker;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class PesanActivity extends AppCompatActivity {

    private EditText txtNama, txtAlamat,
            txtNamaHotel, txtAlamatHotel, txtHarga, txtCI, txtCO, txtJamCI, txtJamCO,txtJmlTamu, txtLmMeng;
    private Button btnPesan1,btnhitung;
    private ImageView btnChi,btnCho;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private Spinner spPembayaran;


    private static String URL = "http://192.168.43.228/api_android/htl/pesan.php";

    private String txtNm, txtAL, txtNmh, txtAlh, txtChI, txtChO, txtHrg,txtpembayaran, txtJamChI, txtJamCho,txtjmltm,txtlmm;

    private String[] mOptionSpinner =  {"-Pilih-","Alfamart","Indomaret"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan);

        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        txtNama = findViewById(R.id.txtNama);
        txtAlamat = findViewById(R.id.txtAlamat);
        txtNamaHotel = findViewById(R.id.txtNamaHotel);
        txtAlamatHotel = findViewById(R.id.txtAlamatHotel);
        txtCI = findViewById(R.id.txtCI);
        txtCO = findViewById(R.id.txtCO);
        txtHarga = findViewById(R.id.txtHarga);
        btnChi = findViewById(R.id.btnChi);
        btnCho = findViewById(R.id.btnCho);
        txtJamCI = findViewById(R.id.txtJamCI);
        txtJamCO = findViewById(R.id.txtJamCO);
        txtJmlTamu = findViewById(R.id.txtJmlTamu);
        txtLmMeng = findViewById(R.id.txtLmMeng);
        spPembayaran = findViewById(R.id.spPembayaran);
        ArrayAdapter pembayaran = new ArrayAdapter(this,android.R.layout.simple_spinner_item, mOptionSpinner);
        pembayaran.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPembayaran.setAdapter(pembayaran);

        btnChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialogChi();
            }
        });
        btnCho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialogCho();
            }
        });
        btnhitung = findViewById(R.id.button);
        btnhitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tglCI = Integer.parseInt(txtJmlTamu.getText().toString());
                int tglCO = Integer.parseInt(txtLmMeng.getText().toString());
                int total = (tglCO * tglCI)*280000;
                txtHarga.setText(total+"");
            }
        });

        btnPesan1 = findViewById(R.id.btnPesan1);

        btnPesan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtChI = txtCI.getText().toString().trim();
                txtChO = txtCO.getText().toString().trim();
                txtNm = txtNama.getText().toString().trim();
                txtNmh = txtNamaHotel.getText().toString().trim();
                txtAL = txtAlamat.getText().toString().trim();
                txtAlh = txtAlamatHotel.getText().toString().trim();
                txtHrg = txtHarga.getText().toString().trim();
                txtjmltm = txtJmlTamu.getText().toString().trim();
                txtlmm = txtLmMeng.getText().toString().trim();
                if(txtNm.isEmpty()) {
                    txtNama.setError("Nama harus diisi!");
                }else if(txtAL.isEmpty()){
                    txtAlamat.setError("Alamat harus diisi!");
                }else if(txtNmh.isEmpty()){
                    txtNamaHotel.setError("Nama Hotel harus diisi!");
                }else if(txtAlh.isEmpty()){
                    txtAlamatHotel.setError("Alamat Hotel harus diisi!");
                }else if(txtChI.isEmpty()){
                    txtCI.setError("Check-In harus diisi!");
                }else if(txtChO.isEmpty()){
                    txtCO.setError("Check-Out harus diisi!");
                }else if(txtHrg.isEmpty()){
                    txtHarga.setError("Harga harus diisi!");
                }else if(txtjmltm.isEmpty()){
                    txtJmlTamu.setError("Jumlah Tamu harus diisi!");
                }else if(txtlmm.isEmpty()){
                    txtLmMeng.setError("Lama Menginap harus diisi!");
                }else{
                    Pesan();
                }

            }
        });
    }

    private void showDateDialogChi(){
        Calendar newCalendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                txtCI.setText(dateFormatter.format(newDate.getTime()));
            }
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
    private void showDateDialogCho(){
        Calendar newCalendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                txtCO.setText(dateFormatter.format(newDate.getTime()));
            }
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void Pesan() {
        txtChI = this.txtCI.getText().toString().trim();
        txtChO = this.txtCO.getText().toString().trim();
        txtNm = this.txtNama.getText().toString().trim();
        txtNmh = this.txtNamaHotel.getText().toString().trim();
        txtAL = this.txtAlamat.getText().toString().trim();
        txtAlh = this.txtAlamatHotel.getText().toString().trim();
        txtHrg = this.txtHarga.getText().toString().trim();
        txtJamChI = this.txtJamCI.getText().toString().trim();
        txtJamCho = this.txtJamCO.getText().toString().trim();
        txtpembayaran = spPembayaran.getSelectedItem().toString().trim();
        txtjmltm = this.txtJmlTamu.getText().toString().trim();
        txtlmm = this.txtLmMeng.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if(success.equals("1")){
                                Toast.makeText(PesanActivity.this, "Pesan Berhasil!", Toast.LENGTH_SHORT).show();
                                Intent pindah_login = new Intent(PesanActivity.this, BelumBayarActivity.class);
                                startActivity(pindah_login);
                            }
                        }catch(JSONException e){
                            e.printStackTrace();
                            Toast.makeText(PesanActivity.this, "Pesan Error : " +e.toString(), Toast.LENGTH_SHORT).show();

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PesanActivity.this, "Pesan Error : " +error.toString(), Toast.LENGTH_SHORT).show();
                }
        })
        {
            @Override
            protected Map<String, String> getParams () throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("check_in", txtChI);
                params.put("check_out", txtChO);
                params.put("nama_lengkap", txtNm);
                params.put("nama_hotel", txtNmh);
                params.put("alamat", txtAL);
                params.put("alamat_hotel", txtAlh);
                params.put("harga", txtHrg);
                params.put("jam_check_in",txtJamChI);
                params.put("jam_check_out",txtJamCho);
                params.put("pembayaran",txtpembayaran);
                params.put("lama_menginap",txtlmm);
                params.put("jml_tamu",txtjmltm);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

}

