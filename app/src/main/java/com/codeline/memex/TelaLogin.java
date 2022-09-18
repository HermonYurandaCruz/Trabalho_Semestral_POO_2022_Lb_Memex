package com.codeline.memex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TelaLogin extends AppCompatActivity {
    private Button bt_entrar;
    private TextView tv_criarConta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);
        getSupportActionBar().hide();
    IniciarNovoCadastro();

    tv_criarConta.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(TelaLogin.this,TelaCriarConta.class);
            startActivity(intent);
            finish();
        }
    });







    bt_entrar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        AbrirTelaHome();
        }
    });
    }
    private void AbrirTelaHome(){
        Intent intent=new Intent(TelaLogin.this,Home.class);
        startActivity(intent);
        finish();

    }





    private  void IniciarNovoCadastro(){
        bt_entrar=findViewById(R.id.bt_login);
        tv_criarConta=findViewById(R.id.tv_criar_conta);
      /*
        et_email=findViewById(R.id.emailLogin);
        et_passWord=findViewById(R.id.PasswordLogin);
        bt_login=findViewById(R.id.BotaoLogin);
        progressBar=findViewById(R.id.progresse);
        tv_recuperarSenha=findViewById(R.id.Tv_recuperarSenha);

        */
    }
}