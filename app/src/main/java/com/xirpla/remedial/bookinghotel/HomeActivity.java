package com.xirpla.remedial.bookinghotel;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;






public class  HomeActivity extends AppCompatActivity {

    private boolean userClickedBack = false;
    AlertDialog.Builder builder;

    ViewPager viewPager;

    RecyclerView rvCategory;
    private ArrayList<Produk> list;
    final String STATE_TITLE = "state_string";
    final String STATE_LIST = "state_list";
    final String STATE_MODE = "state_mode";
    int mode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewPager = (ViewPager) findViewById(R.id.viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);

        viewPager.setAdapter(viewPagerAdapter);


        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new MyTimerTask(), 2000, 4000);

        rvCategory = findViewById(R.id.rv_hotel);
        rvCategory.setHasFixedSize(true);

        list = new ArrayList<>();
        list.addAll(ProdukData.getListData());
        showRecyclerList();




    }

    private void showRecyclerList() {
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        ListProdukAdapter listProdukAdapter = new ListProdukAdapter(this);
        listProdukAdapter.setListPresident(list);
        rvCategory.setAdapter(listProdukAdapter);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }




    public class MyTimerTask extends TimerTask {

            @Override

            public void run() {

                HomeActivity.this.runOnUiThread(new Runnable() {

                    @Override

                    public void run() {

                        if (viewPager.getCurrentItem() == 0) {

                            viewPager.setCurrentItem(1);

                        } else if (viewPager.getCurrentItem() == 1) {

                            viewPager.setCurrentItem(2);

                        } else {

                            viewPager.setCurrentItem(0);

                        }

                    }

                });

                ImageView profil = (ImageView) findViewById(R.id.Profil);
                profil.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(HomeActivity.this, ProfilActivity.class);
                        startActivity(i);
                    }
                });
                ImageView voucher = (ImageView) findViewById(R.id.Voucher);
                voucher.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(HomeActivity.this, MenuTransaksiActivity.class);
                        startActivity(i);
                    }
                });
            }
        }
            @Override
            protected void onSaveInstanceState(Bundle outState) {
                super.onSaveInstanceState(outState);
                outState.putString(STATE_TITLE, getSupportActionBar().getTitle().toString());
                outState.putParcelableArrayList(STATE_LIST, list);
                outState.putInt(STATE_MODE, mode);
            }

    @Override
    public void onBackPressed()
    {

        if (!userClickedBack){
            Toast.makeText(this, "Klik sekali lagi untuk Logout", Toast.LENGTH_LONG).show();
            userClickedBack = true;
        }else {
            builder = new AlertDialog.Builder(HomeActivity.this);
            builder.setTitle("Keluar Ke halaman login?");
            builder.setMessage("Apa Anda Yakin?");
            builder.setNegativeButton("Batal", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    System.exit(0);
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

        new CountDownTimer(2500, 1000){
            @Override
            public void onTick(long millisUntilfinished){

            }

            @Override
            public void onFinish(){
                userClickedBack = false;
            }
        }.start();
    }

}







