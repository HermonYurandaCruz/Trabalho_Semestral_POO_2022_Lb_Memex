package com.codeline.memex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codeline.memex.model.Memeiros;

public class PerfilUsuarios extends AppCompatActivity {

    TextView tv_nome_usuario;
    ImageView iv_foto_perfil;
    TextView tv_perfil_usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuarios);
        getSupportActionBar().hide();
        inicilizarComponentes();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            Toast.makeText(this, "Chegou", Toast.LENGTH_SHORT).show();
            Memeiros memeiros = (Memeiros) bundle.get("memeiros");
            tv_nome_usuario.setText(memeiros.getNome_usuario());
            Glide.with(getApplicationContext()).load(memeiros.getUrl_foto_perfil()).into(iv_foto_perfil);
            tv_perfil_usuario.setText(memeiros.getNome_usuario());
        }



    }


    public void inicilizarComponentes(){
        tv_nome_usuario = findViewById(R.id.tv_nomeUsuario3);
        iv_foto_perfil = findViewById(R.id.iv_perfilUsuario3);
        tv_perfil_usuario = findViewById(R.id.tv_perfilUsuario3);
    }

}