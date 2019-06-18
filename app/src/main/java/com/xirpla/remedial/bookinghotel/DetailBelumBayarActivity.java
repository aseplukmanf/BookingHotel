package com.xirpla.remedial.bookinghotel;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;

public class DetailBelumBayarActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextId;
    private EditText editTextName;
    private EditText editTextDesg;
    private EditText editTextSalary;
    private EditText editTextAlamatHotel;
    private EditText editTextCheckIn;
    private EditText editTextCheckOut;
    private EditText editTextHarga;
    private EditText editTextStatus;
    private EditText editTextPembayaran;
    private Spinner editTextKeterangan;

    private RequestQueue requestQueue;
    private StringRequest stringRequest;

    private Button buttonUpdate;

    private String id;


    ArrayList<HashMap<String, String>> list_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_belum_bayar);


        String url = "http://192.168.43.226/api_android/htl/datatransaksi.php";

        Intent intent = getIntent();

        id = intent.getStringExtra(konfigurasi.EMP_ID);

        editTextId = findViewById(R.id.txtIdTransaksi);
        editTextName = findViewById(R.id.txtNamaPemesan);
        editTextDesg = findViewById(R.id.txtAlamat);
        editTextSalary = findViewById(R.id.txtNamaHotel);
        editTextAlamatHotel = findViewById(R.id.txtAlamatHotel);
        editTextCheckIn = findViewById(R.id.txtCI);
        editTextCheckOut= findViewById(R.id.txtCO);
        editTextHarga = findViewById(R.id.txtHarga);
        editTextStatus = findViewById(R.id.txtStatus);
        editTextPembayaran = findViewById(R.id.txtPembayaran);

        buttonUpdate = findViewById(R.id.btnUpdate);
        buttonUpdate.setOnClickListener(this);
        editTextId.setText(id);

        getEmployee();

        requestQueue = Volley.newRequestQueue(DetailBelumBayarActivity.this);

        list_data = new ArrayList<HashMap<String, String>>();

        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("transaksi");
                    for (int a = 0; a < jsonArray.length(); a ++){
                        JSONObject json = jsonArray.getJSONObject(a);
                        HashMap<String, String> map  = new HashMap<String, String>();
                        map.put("nama_lengkap", json.getString("nama_lengkap"));
                        map.put("alamat", json.getString("alamat"));
                        map.put("nama_hotel", json.getString("nama_hotel"));
                        map.put("alamat_hotel", json.getString("alamat_hotel"));
                        map.put("check_in", json.getString("check_in"));
                        map.put("check_out", json.getString("check_out"));
                        map.put("jam_check_in", json.getString("jam_check_in"));
                        map.put("harga", json.getString("harga"));
                        map.put("pembayaran", json.getString("pembayaran"));
                        map.put("status", json.getString("status"));
                        list_data.add(map);
                    }

                    editTextName.setText(list_data.get(0).get("nama_lengkap"));
                    editTextDesg.setText(list_data.get(0).get("alamat"));
                    editTextSalary.setText(list_data.get(0).get("nama_hotel"));
                    editTextAlamatHotel.setText(list_data.get(0).get("alamat_hotel"));
                    editTextCheckIn.setText(list_data.get(0).get("check_in"));
                    editTextCheckOut.setText(list_data.get(0).get("check_out"));
                    editTextHarga.setText(list_data.get(0).get("harga"));
                    editTextStatus.setText(list_data.get(0).get("status"));
                    editTextPembayaran.setText(list_data.get(0).get("pembayaran"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetailBelumBayarActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);

    }

    private void getEmployee(){
        class GetEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;


            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(konfigurasi.URL_GET_EMP,id);
                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }



    private void updateEmployee(){
        final String name = editTextName.getText().toString().trim();
        final String alamat = editTextDesg.getText().toString().trim();
        final String nameh = editTextSalary.getText().toString().trim();
        final String alamath = editTextAlamatHotel.getText().toString().trim();
        final String checkin = editTextCheckIn.getText().toString().trim();
        final String checkout = editTextCheckOut.getText().toString().trim();
        final String harga = editTextHarga.getText().toString().trim();
        final String status = editTextStatus.getText().toString().trim();
        final String pembayaran = editTextPembayaran.getText().toString().trim();

        class UpdateEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DetailBelumBayarActivity.this,"Updating...","Wait...",false,false);
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(DetailBelumBayarActivity.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(konfigurasi.KEY_EMP_ID,id);
                hashMap.put(konfigurasi.KEY_EMP_NAMA,name);
                hashMap.put(konfigurasi.KEY_EMP_ALAMAT,alamat);
                hashMap.put(konfigurasi.KEY_EMP_NAMA_HOTEL,nameh);
                hashMap.put(konfigurasi.KEY_EMP_ALAMAT_HOTEL,alamath);
                hashMap.put(konfigurasi.KEY_EMP_CHECK_IN,checkin);
                hashMap.put(konfigurasi.KEY_EMP_CHECK_OUT,checkout);
                hashMap.put(konfigurasi.KEY_EMP_HARGA,harga);
                hashMap.put(konfigurasi.KEY_EMP_STATUS,status);
                hashMap.put(konfigurasi.KEY_EMP_PEMBAYARAN,pembayaran);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(konfigurasi.URL_UPDATE_EMP,hashMap);

                return s;
            }
        }

        UpdateEmployee ue = new UpdateEmployee();
        ue.execute();
    }



    @Override
    public void onClick(View v) {
        updateEmployee();

    }
}
