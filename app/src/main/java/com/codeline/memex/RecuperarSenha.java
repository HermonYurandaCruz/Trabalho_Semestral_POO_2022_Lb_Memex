package com.codeline.memex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class RecuperarSenha extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_senha);
        getSupportActionBar().hide();
    }
}