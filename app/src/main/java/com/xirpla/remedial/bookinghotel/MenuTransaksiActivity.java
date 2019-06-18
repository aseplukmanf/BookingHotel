package com.xirpla.remedial.bookinghotel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuTransaksiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_transaksi);

        Button btnRiwayat,btnBil;

        btnRiwayat = findViewById(R.id.btnRiwayat);
        btnRiwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent riwayat = new Intent(MenuTransaksiActivity.this,ListVoucherActivity.class);
                startActivity(riwayat);
            }
        });

        btnBil = findViewById(R.id.btnBel);
        btnBil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent belum = new Intent(MenuTransaksiActivity.this,BelumBayarActivity.class);
                startActivity(belum);
            }
        });

        
    }
}
