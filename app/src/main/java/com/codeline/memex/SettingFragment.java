package com.codeline.memex;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SettingFragment extends Fragment {
    TextView def_meuPerfil,de_terminar;
    View view;

    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view =inflater.inflate(R.layout.fragment_setting, container, false);
        Inicializar();
        mAuth = FirebaseAuth.getInstance();
        def_meuPerfil.setOnClickListener(new View.OnClickListener() {
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
    public void Inicializar(){
        def_meuPerfil=view.findViewById(R.id.def_meuPeriil);
        de_terminar=view.findViewById(R.id.def_sairdaconta);
    }

    public void logout(){
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            mAuth.signOut();
        }
        getActivity().finish();
        startActivity(new Intent(this.getActivity(), TelaLogin.class));
    }
}