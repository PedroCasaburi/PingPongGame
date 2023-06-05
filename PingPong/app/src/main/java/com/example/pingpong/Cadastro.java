package com.example.pingpong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Cadastro extends AppCompatActivity {
    EditText mEmailEditText;
    EditText mPasswordEditText;
    EditText mConfirmPasswordEditText;
    Button mRegisterButton;
    TextView mLoginTextView;
    DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        mEmailEditText = findViewById(R.id.CadastroEmail);
        mPasswordEditText = findViewById(R.id.cadastroSenha);
        mConfirmPasswordEditText = findViewById(R.id.confirmarCadastroSenha);
        mRegisterButton = findViewById(R.id.cadastroButton);
        mLoginTextView = findViewById(R.id.textLogin);

        mDatabaseHelper = new DatabaseHelper(this);

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmailEditText.getText().toString().trim();
                String senha = mPasswordEditText.getText().toString().trim();
                String confirmSenha = mConfirmPasswordEditText.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Cadastro.this, "Insira o E-mail" , Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(senha)) {
                    Toast.makeText(Cadastro.this, "Insira a senha ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(confirmSenha)) {
                    Toast.makeText(Cadastro.this, "Confirme a senha ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!senha.equals(confirmSenha)){
                    Toast.makeText(Cadastro.this, "As senhas não conferem ", Toast.LENGTH_SHORT).show();
                }
                boolean userExists = mDatabaseHelper.checkUser(email, senha);
                if(userExists){
                    Toast.makeText(Cadastro.this, "", Toast.LENGTH_SHORT).show();
                    return;
                }
                long id = mDatabaseHelper.createUser(email, senha);
                if(id>0){
                    Toast.makeText(Cadastro.this, "Cadastro relizado com sucesso", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Cadastro.this, Login.class);
                    startActivity(intent);
                    finish();

                }else{
                    Toast.makeText(Cadastro.this, "Cadastro com falha, usuario ja existe", Toast.LENGTH_SHORT).show();
                }
                mLoginTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Cadastro.this, Login.class);
                        startActivity(intent);
                        finish();

                    }
                });
            }
        });
    }
}