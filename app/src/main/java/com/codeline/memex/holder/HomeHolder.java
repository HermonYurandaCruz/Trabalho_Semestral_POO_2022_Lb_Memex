package com.codeline.memex.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codeline.memex.R;

public class HomeHolder extends RecyclerView.ViewHolder {

    public TextView tv_nome_publicacao;
    public TextView tv_texto_publicacao;
    public ImageView imagem_publicacao, foto_perfil;


    public HomeHolder(@NonNull View itemView) {
        super(itemView);
        tv_nome_publicacao = itemView.findViewById(R.id.tv_usuario_publicacao);
        tv_texto_publicacao = itemView.findViewById(R.id.tv_textoMeme);
        imagem_publicacao = itemView.findViewById(R.id.iv_publicacao);
        foto_perfil = itemView.findViewById(R.id.iv_perfil_publicacao);
    }
}
