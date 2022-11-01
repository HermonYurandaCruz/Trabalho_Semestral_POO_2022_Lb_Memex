package com.codeline.memex.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.codeline.memex.holder.MemeirosHolder;
import com.codeline.memex.R;
import com.codeline.memex.model.Memeiros;

import java.util.List;

public class MemeirosAdapter extends RecyclerView.Adapter<MemeirosHolder> {

    Context context;
    List<Memeiros> memeiros;
    private OnItemClickedListener mListener;

    public MemeirosAdapter(Context context, List<Memeiros> memeiros, OnItemClickedListener mListener) {
        this.context = context;
        this.memeiros = memeiros;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public MemeirosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_usuario, parent, false);
        return new MemeirosHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemeirosHolder holder, int position) {

        Memeiros memeiros = this.memeiros.get(position);
        if(memeiros.getUrl_foto_perfil() != null)
            Glide.with(context).load(memeiros.getUrl_foto_perfil()).into(holder.imagem);
        holder.tv_nomeUsuario.setText(memeiros.getNome_usuario());
        holder.itemView.setOnClickListener(View -> {
            mListener.onItemClicked(memeiros);
        });
    }

    @Override
    public int getItemCount() {
        return memeiros.size();
    }

    public interface OnItemClickedListener{
        void onItemClicked(Memeiros memeiros);
    }
}
