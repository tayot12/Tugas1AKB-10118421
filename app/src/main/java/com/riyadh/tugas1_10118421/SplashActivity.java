package com.riyadh.tugas1_10118421;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    //8-04-2021 10118421 Riyadh Rachman Firdaus IF10

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // setelah aplikasi dibuka langsung pindah ke MainActivity atau login activity
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
//8-04-2021 10118421 Riyadh Rachman Firdaus IF10