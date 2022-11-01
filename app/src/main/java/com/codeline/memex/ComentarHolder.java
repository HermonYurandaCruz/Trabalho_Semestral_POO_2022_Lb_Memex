package com.codeline.memex;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codeline.memex.model.Comentario;

public class ComentarHolder extends RecyclerView.ViewHolder {

    TextView txtNomeUsuario;
    TextView txtComentario;
    TextView txtDataComentario;
    TextView txtHoraComentario;

    public ComentarHolder(@NonNull View itemView) {
        super(itemView);

//        txtNomeUsuario = itemView.findViewById(R.id.txtNomeUsuario);
        txtComentario = itemView.findViewById(R.id.tv_comentarioPublicacao);
//        txtDataComentario = itemView.findViewById(R.id.txtDataComentario);
//        txtHoraComentario = itemView.findViewById(R.id.txtHoraComentario);
    }

    public void setComentario(Comentario comentario) {
//        txtNomeUsuario.setText(comentario.getNome_usuario());
//        txtComentario.setText(comentario.getTexto_comentario());
//        txtDataComentario.setText(comentario.getData_comentario());
//        txtHoraComentario.setText(comentario.getHora_comentario());
//        Glide.with(itemView.getContext()).load(comentario.getUrl_foto_usuario()).into(imgUsuarioComentario);
//        Glide.with(itemView.getContext()).load(comentario.getUrl_imagem()).into(imgComentario);
    }
}

