package com.codeline.memex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;

public class MeuPerfil extends AppCompatActivity {
    TextView tv_meuPerfil;
    Button bt_editar;
    CircleImageView cimv;
    TextView tv_nomeUsuario;
    FirebaseAuth mAuth;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_meu_perfil);
        tv_meuPerfil=findViewById(R.id.MeuPerfil);
        cimv = findViewById(R.id.foto);
        tv_nomeUsuario = findViewById(R.id.tv_nomeUsuario);

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {
            String URL = currentUser.getPhotoUrl().toString();
            tv_nomeUsuario.setText(currentUser.getDisplayName());
            carregarFotoDePerfil(URL);
        }
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
    public void carregarFotoDePerfil(String URL) {
        new Thread() {
            public void run() {
                Bitmap img = null;


                try {
                    URL url = new URL(URL);
                    HttpURLConnection connexao = (HttpURLConnection) url.openConnection();
                    InputStream input = connexao.getInputStream();
                    img = BitmapFactory.decodeStream(input);
                } catch (IOException ignored) {}
                    final Bitmap imgAux = img;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            cimv.setImageBitmap(imgAux);


                        }
                });


            }
        }.start();
    }

}