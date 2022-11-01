package com.codeline.memex.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codeline.memex.holder.MeuPerfilHolder;
import com.codeline.memex.R;
import com.codeline.memex.model.Publicacao;

import java.util.List;

public class MeuPerfilAdapter extends RecyclerView.Adapter<MeuPerfilHolder> {
    Context context;
    List<Publicacao> publicacoes;

    public MeuPerfilAdapter(Context context, List<Publicacao> publicacoes) {
        this.context = context;
        this.publicacoes = publicacoes;
    }

    @NonNull
    @Override
    public MeuPerfilHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_publicacaao, parent, false);
        return new MeuPerfilHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MeuPerfilHolder holder, int position) {

        Publicacao publicacao = this.publicacoes.get(position);
        if(publicacao.getUrl_imagem() != null)
            Glide.with(context).load(publicacao.getUrl_imagem()).into(holder.imagem);
        holder.tv_nomeUsuario.setText(publicacao.getNome_usuario());

    }

    @Override
    public int getItemCount() {
        return publicacoes.size();
    }
}
