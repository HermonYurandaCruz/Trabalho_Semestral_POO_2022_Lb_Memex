package com.codeline.memex;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


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
        Button bt_seguir = (Button) getView().findViewById(R.id.bt_seguir);
        Button bt_seguindo=(Button) getView().findViewById(R.id.bt_seguindo);

    bt_seguir.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Fragment seguirFragment = new SeguirFragment();
            FragmentTransaction transactionSeguir = getChildFragmentManager().beginTransaction();
            transactionSeguir.add(R.id.conteinerMemeiros, seguirFragment).commit();

        }
    });

    bt_seguindo.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Fragment seguindoFragment=new SeguindoFragment();
            FragmentTransaction transactionSeguindo=getChildFragmentManager().beginTransaction();
            transactionSeguindo.add(R.id.conteinerMemeiros,seguindoFragment).commit();
        }
    });

    }

    }





