package com.xirpla.remedial.bookinghotel;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button btn_login;
    TextView yuk;
    AlertDialog.Builder builder;

    private boolean userClickedBack = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.valUser);
        password = (EditText) findViewById(R.id.valPAss);
        btn_login = (Button) findViewById(R.id.btnLogin);
        yuk = (TextView) findViewById(R.id.txtRegister);
        yuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().equals("")||password.getText().toString().equals("")){
                    builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setMessage("Kolom tidak boleh kosong");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                else {
                    ProsesLogin backgroundTask = new ProsesLogin(LoginActivity.this);
                    backgroundTask.execute("Login", username.getText().toString(),password.getText().toString());
                }
            }
        });

    }
    @Override
    public void onBackPressed()
    {

        if (!userClickedBack){
            Toast.makeText(this, "Klik sekali lagi untuk keluar", Toast.LENGTH_LONG).show();
            userClickedBack = true;
        }else {
            builder = new AlertDialog.Builder(LoginActivity.this);
            builder.setTitle("Keluar Aplikasi?");
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