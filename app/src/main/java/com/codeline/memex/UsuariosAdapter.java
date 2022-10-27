package com.codeline.memex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class UsuariosAdapter extends RecyclerView.Adapter<UsuariosHolder> {

    Context context;
    List<Usuario> usuarios;

    public UsuariosAdapter(Context context, List<Usuario> usuarios) {
        this.context = context;
        this.usuarios = usuarios;
    }

    @NonNull
    @Override
    public UsuariosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_usuario, parent, false);
        UsuariosHolder holder = new UsuariosHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull UsuariosHolder holder, int position) {

        Usuario usuario = this.usuarios.get(position);
       /// Glide.with(context).load(usuario.getUrl_image()).into(holder.imagem);
    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }
}
