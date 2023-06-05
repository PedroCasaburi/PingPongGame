package com.example.pingpong;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


    public class Login extends AppCompatActivity {
        TextView register;
        EditText mEmailEditText;
        EditText mPasswordEditText;
        Button LoginButton;
        DatabaseHelper mDatabaseHelper;

        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            register = findViewById(R.id.regsiter_text);
            mEmailEditText = findViewById(R.id.loginEmail);
            mPasswordEditText = findViewById(R.id.loginSenha);
            LoginButton = findViewById(R.id.loginButton);
            mDatabaseHelper = new DatabaseHelper(this);

            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Login.this, Cadastro.class);
                    startActivity(intent);
                    finish();

                }
            });

            LoginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String email = mEmailEditText.getText().toString().trim();
                    String password = mPasswordEditText.getText().toString().trim();
                    if (TextUtils.isEmpty(email)){
                        Toast.makeText(Login.this, "Por favor, Insira o e-mail", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(password)){
                        Toast.makeText(Login.this, "Por favor, Insira a senha", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    boolean userExists = mDatabaseHelper.checkUser(email, password);
                    if (userExists){
                        Toast.makeText(Login.this, "Login realizado com sucesso", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(Login.this, "E-mail ou senha inválidos", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

