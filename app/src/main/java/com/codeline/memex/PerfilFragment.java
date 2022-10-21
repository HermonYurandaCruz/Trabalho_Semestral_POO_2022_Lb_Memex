package com.codeline.memex;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class PerfilFragment extends Fragment {

    ImageView voltar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_perfil, container, false);
       voltar=view.findViewById(R.id.iv_voltar);
       voltar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });

        return view;
    }


}