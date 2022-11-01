package com.codeline.memex.holder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codeline.memex.R;

public class MeuPerfilHolder extends RecyclerView.ViewHolder {
    public TextView tv_numeroSeguidores,tv_numeroPostes,tv_nomeUsuario;
    public Button bt_seguir;
    public ImageView imagem;

    public MeuPerfilHolder(@NonNull View itemView) {
        super(itemView);
        tv_nomeUsuario = itemView.findViewById(R.id.tv_usuario_publicacao);
        imagem = itemView.findViewById(R.id.iv_publicacao);
//        itemView.findViewById(R.id.tv_numeroPostes);
//        itemView.findViewById(R.id.tv_numeroSeguidores);
//        itemView.findViewById(R.id.bt_seguir);

    }
}
