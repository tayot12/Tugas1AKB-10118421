package com.riyadh.tugas1_10118421;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class RegisterActivity extends AppCompatActivity {

    //8-04-2021 10118421 Riyadh Rachman Firdaus IF10
    private EditText etFullname, etUsername, etPassword, etConfirmPassword;
    private Button btnLogin, btnRegister;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etFullname = findViewById(R.id.et_fullname);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        etConfirmPassword = findViewById(R.id.et_confirmpassword);

        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);
        db = new DatabaseHelper(this);

        //membuat agar button login saat diklik membuka ke Login Activity
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname = etFullname.getText().toString().trim();
                String user = etUsername.getText().toString().trim();
                String pwd = etPassword.getText().toString().trim();
                String cnf_pwd = etConfirmPassword.getText().toString().trim();

                //validasi fullname kosong
                if (fullname.isEmpty()){
                    etFullname.setError("Nama Lengkap harus diisi!");
                    etFullname.requestFocus();
                    return;
                }

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

                //validasi password kosong , panjang password dan match password
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

                if (pwd.equals(cnf_pwd)) {
                    long val = db.addUser(user, pwd, fullname);
                    if (val > 0) {
                        //jika registrasi berhasil maka muncul pesan berhasil
                        Toast.makeText(RegisterActivity.this, "Anda telah terdaftar!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(i);
                    } else {
                        //pesan registrasi gagal
                        Toast.makeText(RegisterActivity.this, "Pendaftaran Gagal!", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    //pesan jika password dan ulangi password tidak match
                    Toast.makeText(RegisterActivity.this, "Password tidak sama! silahkan ulangi!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
//8-04-2021 10118421 Riyadh Rachman Firdaus IF10