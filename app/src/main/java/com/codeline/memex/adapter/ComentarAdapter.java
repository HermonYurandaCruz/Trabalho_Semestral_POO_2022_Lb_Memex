package com.codeline.memex.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codeline.memex.holder.ComentarHolder;
import com.codeline.memex.R;
import com.codeline.memex.model.Comentario;

import java.util.List;

public class ComentarAdapter extends RecyclerView.Adapter<ComentarHolder> {

    private List<Comentario> comentarios;
    private Context context;

    public ComentarAdapter(List<Comentario> comentarios, Context context) {
        this.comentarios = comentarios;
        this.context = context;
    }


    @NonNull
    @Override
    public ComentarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comentario, parent, false);
        return new ComentarHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComentarHolder holder, int position) {
        Comentario comentario = comentarios.get(position);
        holder.txtComentario.setText(comentario.getComentario());
//        holder.setComentario(comentario);
    }

    @Override
    public int getItemCount() {
        return comentarios.size();
    }
}




