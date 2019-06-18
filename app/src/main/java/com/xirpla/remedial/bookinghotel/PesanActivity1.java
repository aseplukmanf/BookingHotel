package com.xirpla.remedial.bookinghotel;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class PesanActivity1 extends AppCompatActivity {

    private EditText txtNama, txtAlamat,
            txtNamaHotel, txtAlamatHotel, txtHarga, txtCI, txtCO, txtJamCI, txtJamCO,txtJmlTamu,txtLmMeng;
    private Button btnPesan1,btnHitung;
    private ImageView btnChi,btnCho;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private Spinner spinner;


    private static String URL = "http://192.168.43.228/api_android/htl/pesan.php";

    private String txtNm, txtAL, txtNmh, txtAlh, txtChI, txtChO, txtHrg,txtpem, txtJamChI, txtJamCho,txtjmltm,txtLmm;
    private String[] mOptionSpinner =  {"-Pilih-","Alfamart","Indomaret"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan3);

        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        txtNama = findViewById(R.id.txtNama1);
        txtAlamat = findViewById(R.id.txtAlamat1);
        txtNamaHotel = findViewById(R.id.txtNamaHotel1);
        txtAlamatHotel = findViewById(R.id.txtAlamatHotel1);
        txtCI = findViewById(R.id.txtCI1);
        txtCO = findViewById(R.id.txtCO1);
        txtHarga = findViewById(R.id.txtHarga1);
        btnChi = findViewById(R.id.btnChi1);
        btnCho = findViewById(R.id.btnCho1);
        txtJamCI = findViewById(R.id.txtJamCI1);
        txtJamCO = findViewById(R.id.txtJamCO1);
        txtJmlTamu = findViewById(R.id.txtJmlTamu1);
        txtLmMeng = findViewById(R.id.txtLmMeng1);
        spinner = findViewById(R.id.spinner);
        ArrayAdapter pembayaran = new ArrayAdapter(this,android.R.layout.simple_spinner_item, mOptionSpinner);
        pembayaran.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(pembayaran);
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

        btnHitung = findViewById(R.id.btnHitung1);
        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tanggalCI = Integer.parseInt(txtJmlTamu.getText().toString());
                int tanggalCO = Integer.parseInt(txtLmMeng.getText().toString());
                int totalHarga = (tanggalCO * tanggalCI)*433000;
                txtHarga.setText(totalHarga+"");
            }
        });

        btnPesan1 = findViewById(R.id.btnPesan11);

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
        txtjmltm= this.txtJmlTamu.getText().toString().trim();
        txtLmm= this.txtLmMeng.getText().toString().trim();
        txtpem = spinner.getSelectedItem().toString().trim();
        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if(success.equals("1")){
                                Toast.makeText(PesanActivity1.this, "Pesan Berhasil!", Toast.LENGTH_SHORT).show();
                                Intent pindah_login = new Intent(PesanActivity1.this, BelumBayarActivity.class);
                                startActivity(pindah_login);
                            }
                        }catch(JSONException e){
                            e.printStackTrace();
                            Toast.makeText(PesanActivity1.this, "Pesan Error : " +e.toString(), Toast.LENGTH_SHORT).show();

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PesanActivity1.this, "Pesan Error : " +error.toString(), Toast.LENGTH_SHORT).show();
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
                params.put("pembayaran",txtpem);
                params.put("lama_menginap",txtLmm);
                params.put("jml_tamu",txtjmltm);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

}

