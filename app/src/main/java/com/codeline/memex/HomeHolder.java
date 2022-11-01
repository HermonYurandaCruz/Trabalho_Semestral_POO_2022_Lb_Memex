package com.codeline.memex;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomeHolder extends RecyclerView.ViewHolder {

    TextView tv_nome_publicacao;
    TextView tv_texto_publicacao;
    ImageView imagem_publicacao, foto_perfil;


    public HomeHolder(@NonNull View itemView) {
        super(itemView);
        tv_nome_publicacao = itemView.findViewById(R.id.tv_usuario_publicacao);
        tv_texto_publicacao = itemView.findViewById(R.id.tv_textoMeme);
        imagem_publicacao = itemView.findViewById(R.id.iv_publicacao);
        foto_perfil = itemView.findViewById(R.id.iv_perfil_publicacao);
    }
}
