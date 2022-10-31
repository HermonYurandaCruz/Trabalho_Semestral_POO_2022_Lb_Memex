package com.codeline.memex;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MeuPerfilHolder extends RecyclerView.ViewHolder {
    TextView tv_numeroSeguidores,tv_numeroPostes,tv_nomeUsuario;
    Button bt_seguir;
    ImageView imagem;

    public MeuPerfilHolder(@NonNull View itemView) {
        super(itemView);
        tv_nomeUsuario = itemView.findViewById(R.id.tv_usuario_publicacao);
        imagem = itemView.findViewById(R.id.iv_publicacao);
//        itemView.findViewById(R.id.tv_numeroPostes);
//        itemView.findViewById(R.id.tv_numeroSeguidores);
//        itemView.findViewById(R.id.bt_seguir);

    }
}
