package com.riyadh.tugas1_10118421;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    //8-04-2021 10118421 Riyadh Rachman Firdaus IF10
    private EditText etUsername, etPassword;
    private Button btnLogin, btnRegister;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);
        db = new DatabaseHelper(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = etUsername.getText().toString().trim();
                String pwd = etPassword.getText().toString().trim();
                Boolean res = db.checkUser(user, pwd);

                //validasi username kosong dan panjang username
                if (user.isEmpty()){
                    etUsername.setError("Username harus diisi!");
                    etUsername.requestFocus();
                    return;
                }

                if (user.length() < 6){
                    etUsername.setError("Panjang username min 6 karakter!");
                    etUsername.requestFocus();
                    return;
                }

                //validasi password kosong dan panjang password
                if (pwd.isEmpty()){
                    etPassword.setError("Password harus diisi!");
                    etPassword.requestFocus();
                    return;
                }

                if (pwd.length() < 6){
                    etPassword.setError("Panjang password min 6 karakter!");
                    etPassword.requestFocus();
                    return;
                }

                //validasi result atau hasil cek user jika benar maka berhasil login dan masuk main activity, jika gagal muncul pesan salah
                if (res == true) {
                    Toast.makeText(LoginActivity.this, "Login Berhasil!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(LoginActivity.this, "Username atau Password salah! Silahkan ulangi atau Register terlebih dahulu!", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }
}

//8-04-2021 10118421 Riyadh Rachman Firdaus IF10