package com.riyadh.tugas1_10118421;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //8-04-2021 10118421 Riyadh Rachman Firdaus IF10

    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;

    private TextView tvFullname;
    private ImageButton ibtnLogout, ibtnProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvFullname = findViewById(R.id.tv_fullname);
        ibtnLogout = findViewById(R.id.ibtn_logout);
        ibtnProfile = findViewById(R.id.ibtn_profile);

        //membuat agar icon profile saat diklik membuka ke Profile Activity
        ibtnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        //membuat agar icon logout saat diklik membuka ke Login Activity
        ibtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this,"Berhasil Logout!", Toast.LENGTH_SHORT).show();
            }
        });
    }


    //membuat saat di klik tombol back 2x maka akan keluar aplikasi
    @Override
    public void onBackPressed() {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);
        }else { Toast.makeText(getBaseContext(), "Tekan Back 1x lagi untuk Keluar", Toast.LENGTH_SHORT).show();
        }
        mBackPressed = System.currentTimeMillis();
    }
}
//8-04-2021 10118421 Riyadh Rachman Firdaus IF10