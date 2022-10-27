package com.codeline.memex;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UsuariosHolder extends RecyclerView.ViewHolder {
    TextView tv_numeroSeguidores,tv_numeroPostes,tv_nomeUsuario;
    Button bt_seguir;
    ImageView imagem;

    public UsuariosHolder(@NonNull View itemView) {
        super(itemView);
        itemView.findViewById(R.id.tv_nomeSeguir);
        itemView.findViewById(R.id.iv_perfil_usuario);
        itemView.findViewById(R.id.tv_numeroPostes);
        itemView.findViewById(R.id.tv_numeroSeguidores);
        itemView.findViewById(R.id.bt_seguir);
    }
}
