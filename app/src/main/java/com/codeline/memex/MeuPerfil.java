package com.codeline.memex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MeuPerfil extends AppCompatActivity {
    TextView tv_meuPerfil;
    Button bt_editar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_meu_perfil);
        tv_meuPerfil=findViewById(R.id.MeuPerfil);
        tv_meuPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // FragmentManager.popBackStack();

            }
        });

      bt_editar =findViewById(R.id.bt_editar);
     bt_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MeuPerfil.this,EditarMeuPerfil.class);
                startActivity(intent);
                }

        });


    }
}