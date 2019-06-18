package com.xirpla.remedial.bookinghotel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class DetailTransaksiActivity extends AppCompatActivity {

    private TextView txtIDTransaksi, txtNamaPemesan, txtAlamatPemesan, txtAlamatHotel, txtNamaHotel, txtCheck_In, txtCheck_Out, txtHarga, txtJamCI,txtJamCO, txtStatus, txtPembayaran;

    private RequestQueue requestQueue;
    private StringRequest stringRequest;

    ArrayList<HashMap<String, String>> list_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_transaksi);

        String url = "http://192.168.43.226/api_android/htl/datatransaksi.php";

        txtIDTransaksi = findViewById(R.id.id_transaksi);
        txtNamaPemesan = findViewById(R.id.nama_pemesan);
        txtAlamatPemesan = findViewById(R.id.alamat_pemesan);
        txtNamaHotel = findViewById(R.id.nama_hotel);
        txtAlamatHotel = findViewById(R.id.alamat_hotel);
        txtCheck_In = findViewById(R.id.check_in);
        txtCheck_Out = findViewById(R.id.check_out);
        txtHarga = findViewById(R.id.harga);
        txtPembayaran = findViewById(R.id.pembayaran);
        txtStatus = findViewById(R.id.status);
        txtJamCI = findViewById(R.id.jam_check_in);
        txtJamCO = findViewById(R.id.jam_check_out);

        requestQueue = Volley.newRequestQueue(DetailTransaksiActivity.this);

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
                        map.put("id_transaksi", json.getString("id_transaksi"));
                        map.put("nama_lengkap", json.getString("nama_lengkap"));
                        map.put("alamat", json.getString("alamat"));
                        map.put("nama_hotel", json.getString("nama_hotel"));
                        map.put("alamat_hotel", json.getString("alamat_hotel"));
                        map.put("check_in", json.getString("check_in"));
                        map.put("check_out", json.getString("check_out"));
                        map.put("jam_check_in", json.getString("jam_check_in"));
                        map.put("jam_check_out", json.getString("jam_check_out"));
                        map.put("harga", json.getString("harga"));
                        map.put("pembayaran", json.getString("pembayaran"));
                        map.put("status", json.getString("status"));
                        list_data.add(map);
                    }

                    txtIDTransaksi.setText(list_data.get(0).get("id_transaksi"));
                    txtNamaPemesan.setText(list_data.get(0).get("nama_lengkap"));
                    txtAlamatPemesan.setText(list_data.get(0).get("alamat"));
                    txtNamaHotel.setText(list_data.get(0).get("nama_hotel"));
                    txtAlamatHotel.setText(list_data.get(0).get("alamat_hotel"));
                    txtCheck_In.setText(list_data.get(0).get("check_in"));
                    txtCheck_Out.setText(list_data.get(0).get("check_out"));
                    txtJamCI.setText(list_data.get(0).get("jam_check_in"));
                    txtJamCO.setText(list_data.get(0).get("jam_check_out"));
                    txtHarga.setText(list_data.get(0).get("harga"));
                    txtPembayaran.setText(list_data.get(0).get("pembayaran"));
                    txtStatus.setText(list_data.get(0).get("status"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetailTransaksiActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);
    }
    }

