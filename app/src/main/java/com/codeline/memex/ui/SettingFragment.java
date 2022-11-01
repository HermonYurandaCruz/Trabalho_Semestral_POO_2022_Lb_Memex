package com.codeline.memex.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codeline.memex.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;


public class SettingFragment extends Fragment {
    private TextView tv_nomeUsuarioDef,de_terminar;
    private CircleImageView iv_perfilDef;
    private Handler handler = new Handler();
    View view;
    private TextView tv_privacidade;

    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view =inflater.inflate(R.layout.fragment_setting, container, false);
        Inicializar();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {
            String URL = currentUser.getPhotoUrl().toString();
            tv_nomeUsuarioDef.setText(currentUser.getDisplayName());
            carregarFotoDePerfil(URL);
        }








         tv_nomeUsuarioDef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MeuPerfil.class));

            }
        });

        tv_privacidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Privacidade.class));
            }
        });

        iv_perfilDef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MeuPerfil.class));

            }
        });

de_terminar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        logout();
    }
});




        return view;
    }
    public void  Inicializar(){
        tv_nomeUsuarioDef=view.findViewById(R.id.tv_nomeUsuarioDef);
        de_terminar=view.findViewById(R.id.def_sairdaconta);
        iv_perfilDef=view.findViewById(R.id.iv_perfilDef);
        tv_privacidade=view.findViewById(R.id.def_privacidade);

    }

    public void logout(){
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            mAuth.signOut();
        }
        getActivity().finish();
        startActivity(new Intent(getActivity(), TelaLogin.class));
    }

    public void carregarFotoDePerfil(String URL) {
        new Thread() {
            public void run() {
                Bitmap img = null;


                try {
                    java.net.URL url = new URL(URL);
                    HttpURLConnection connexao = (HttpURLConnection) url.openConnection();
                    InputStream input = connexao.getInputStream();
                    img = BitmapFactory.decodeStream(input);
                } catch (IOException ignored) {}
                final Bitmap imgAux = img;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iv_perfilDef.setImageBitmap(imgAux);


                    }
                });


            }
        }.start();
    }

}