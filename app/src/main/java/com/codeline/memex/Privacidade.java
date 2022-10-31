package com.codeline.memex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Privacidade extends AppCompatActivity {

    TextView tv_trocar_informacoes;
    TextView tv_trocar_senha;
    TextView tv_politicas_privacidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacidade);
        getSupportActionBar().hide();
        inicializarComponentes();

        tv_trocar_senha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Privacidade.this, MudarSenha.class));
            }
        });

        tv_trocar_informacoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Privacidade.this, "Em breve", Toast.LENGTH_SHORT).show();
            }
        });

        tv_politicas_privacidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(Privacidade.this, ));
                Toast.makeText(Privacidade.this, "Abrir página web", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void inicializarComponentes(){
        tv_politicas_privacidade = findViewById(R.id.politica_e_privacidade);
        tv_trocar_senha = findViewById(R.id.mudar_senha);
        tv_trocar_informacoes = findViewById(R.id.actualizar_informacao_pessoal);
    }
}