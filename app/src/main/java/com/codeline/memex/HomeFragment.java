package com.codeline.memex;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class HomeFragment extends Fragment {
public TextView tvUsuarioPublicacao;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home, container, false);
         tvUsuarioPublicacao=view.findViewById(R.id.tv_usuario_publicacao);
         tvUsuarioPublicacao.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Fragment perfilFragment=new PerfilFragment();
                 FragmentTransaction fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
                 fragmentTransaction.replace(R.id.conteinernavagation,perfilFragment).commit();
             }
         });




        return view;
    }




    }