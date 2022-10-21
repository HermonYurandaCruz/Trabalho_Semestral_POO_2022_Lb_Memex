package com.codeline.memex;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;


public class SettingFragment extends Fragment {
    TextView def_meuPerfil,de_terminar;
    View view;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view =inflater.inflate(R.layout.fragment_setting, container, false);
        Inicializar();
        def_meuPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MeuPerfil.class));

            }
        });

de_terminar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Snackbar snackbar=Snackbar.make(view,"Preencha todos os campos!!!",Snackbar.LENGTH_SHORT);
        snackbar.setBackgroundTint(Color.WHITE);
        snackbar.setTextColor(Color.BLACK);
        snackbar.show();
    }
});




        return view;
    }
    public void  Inicializar(){
        def_meuPerfil=view.findViewById(R.id.def_meuPeriil);
        de_terminar=view.findViewById(R.id.def_sairdaconta);

    }
}