package com.codeline.memex;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MemeirosHolder extends RecyclerView.ViewHolder {
    TextView tv_numeroSeguidores,tv_numeroPostes,tv_nomeUsuario;
    TextView tv_seguir;
    ImageView imagem;

    public MemeirosHolder(@NonNull View itemView) {
        super(itemView);
        tv_nomeUsuario = itemView.findViewById(R.id.tv_nomeSeguir);
        imagem = itemView.findViewById(R.id.iv_perfil_usuario);
        tv_numeroPostes = itemView.findViewById(R.id.tv_numeroDePostagens);
        tv_numeroSeguidores = itemView.findViewById(R.id.tv_numeroSeguidores);
        tv_seguir = itemView.findViewById(R.id.tv_seguirUsuario);
    }
}
