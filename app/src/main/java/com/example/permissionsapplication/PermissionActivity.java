package com.example.permissionsapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class PermissionActivity extends AppCompatActivity {

    private Button btnCallPhone, btnShowWebpage, btnNumberScreen, btnSendText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        initComponents();
        sendText();
        callPhone();
        numberScreen();
        showWebpage();
    }


    public void initComponents() {
        btnSendText = findViewById(R.id.btn_SendText);
        btnCallPhone = findViewById(R.id.btn_CallPhone);
        btnNumberScreen = findViewById(R.id.btn_IntentNumberScreen);
        btnShowWebpage = findViewById(R.id.btn_ShowWebpage);
    }


    //Metin Gönderme
    public void sendText() {
        btnSendText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, "Selam Emre, naber?");
                intent.setType("text/plain");
                startActivity(Intent.createChooser(intent, "Uygulama Seçiniz:"));
            }
        });
    }


    //Arama yapma
    public void callPhone() {
        btnCallPhone.setOnClickListener(new View.OnClickListener() {
            private static final int REQUEST_CALL = 1234;

            @Override
            public void onClick(View view) {
                //Arama izni alma
                String permission = Manifest.permission.CALL_PHONE;
                boolean isGranted = ContextCompat.checkSelfPermission(PermissionActivity.this,
                        permission) == PackageManager.PERMISSION_GRANTED;
                if (!isGranted) {
                    ActivityCompat.requestPermissions(PermissionActivity.this, new
                            String[]{permission}, REQUEST_CALL);
                } else {
                    String number = "tel:901112233";
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse(number));
                    startActivity(intent);
                }

            }
        });
    }

    //Numarayı arama ekranına aktarma
    public void numberScreen() {
        btnNumberScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numara = "tel:5356462728"; //Telefon numarası
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(numara));
                startActivity(intent);
            }
        });
    }


    //Websitesi görüntüle
    public void showWebpage() {
        btnShowWebpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                String adres = "https://github.com/yunusemreyakisan";
                intent.setData(Uri.parse(adres));
                startActivity(intent);
            }
        });
    }


}


