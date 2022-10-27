package com.codeline.memex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.codeline.memex.model.Memeiros;

import java.util.List;

public class MemeirosAdapter extends RecyclerView.Adapter<MemeirosHolder> {

    Context context;
    List<Memeiros> memeiros;

    public MemeirosAdapter(Context context, List<Memeiros> memeiros) {
        this.context = context;
        this.memeiros = memeiros;
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

//        Glide.with(context).load(usuario.getUrl_foto_perfil()).into(holder.imagem);
        holder.tv_nomeUsuario.setText(memeiros.getNome_usuario());

    }

    @Override
    public int getItemCount() {
        return memeiros.size();
    }


}
