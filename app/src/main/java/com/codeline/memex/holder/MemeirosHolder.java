package com.codeline.memex.holder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codeline.memex.R;

public class MemeirosHolder extends RecyclerView.ViewHolder {
    public TextView tv_numeroSeguidores,tv_numeroPostes,tv_nomeUsuario;
    public TextView tv_seguir;
    public ImageView imagem;


    public MemeirosHolder(@NonNull View itemView) {
        super(itemView);
        tv_nomeUsuario = itemView.findViewById(R.id.tv_nomeSeguir);
        imagem = itemView.findViewById(R.id.iv_perfil_usuario);
        tv_numeroPostes = itemView.findViewById(R.id.tv_numeroDePostagens);
        tv_numeroSeguidores = itemView.findViewById(R.id.tv_numeroSeguidores);
        tv_seguir = itemView.findViewById(R.id.tv_seguirUsuario);
    }
}
