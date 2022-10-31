package com.codeline.memex;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codeline.memex.model.Publicacao;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeHolder> {

    Context context;
    List<Publicacao> publicacoes;
    int view;

    private OnItemClickListener mListener;

    public HomeAdapter(Context context, List<Publicacao> publicacoes, OnItemClickListener mListener) {
        this.context = context;
        this.publicacoes = publicacoes;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public HomeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_publicacaao, parent, false);
        return new HomeHolder(view);
    }

    //



    @Override
    public void onBindViewHolder(@NonNull HomeHolder holder, int position) {
        Publicacao publicacao = publicacoes.get(position);
        if(publicacao.getUrl_imagem() != null) {
            String URL = publicacao.getUrl_imagem();
            Glide.with(context).load(URL).into(holder.imagem_publicacao);

//            Glide.with(context).load(publicacao.getUrl_imagem()).into(holder.imagem_publicacao);
//            holder.tv_nome_publicacao.setText(publicacao.getNome_usuario());
        }
        holder.tv_nome_publicacao.setText(publicacao.getNome_usuario());
        holder.tv_texto_publicacao.setText(publicacao.getTexto_publicacao());
//        recyclerView.findViewHolderForAdapterPosition(p)
        holder.itemView.setOnClickListener(View -> {
            mListener.onItemClick(publicacao); // pega a publicacao clicada e passa para o metodo onItemClick
        });
    }

    @Override
    public int getItemCount() {
        return this.publicacoes.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Publicacao publicacao);
    }
}

