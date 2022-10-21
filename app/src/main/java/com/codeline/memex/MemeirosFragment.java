package com.codeline.memex;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MemeirosFragment extends Fragment {
    public Button bt_seguindo;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_memeiros, container, false);
        return view;


    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        Usuario u = new Usuario("herman", "kll");

        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(u);


        UsuariosAdapter adapter = new UsuariosAdapter(this.getContext(), usuarios);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_memeiros);
        recyclerView.setAdapter(adapter);


    }


}





