package com.codeline.memex;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codeline.memex.model.Publicacao;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeHolder> {

    Context context;
    String url_foto = "";
    List<Publicacao> publicacoes;
    FirebaseFirestore dbFirebase = FirebaseFirestore.getInstance();
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
        dbFirebase.collection("usuario").whereEqualTo("id_usuario", publicacao.getId_usuario())
                    .addSnapshotListener((value, error) -> {
                        if (error != null) {
                            Log.w("TAG", "Listen failed.", error);
                            return;

                        }
                        if (value != null && !value.isEmpty()) {
                            //TODO: Pegar a foto do usuario
                            url_foto = value.getDocuments().get(0).get("url_foto_perfil").toString();

                        }
                    });
        if(publicacao.getUrl_imagem() != null) {
            String URL = publicacao.getUrl_imagem();
            Glide.with(context).load(URL).into(holder.imagem_publicacao);

            Glide.with(context).load(publicacao.getId_post());
        }
        Toast.makeText(context, url_foto + "Yes", Toast.LENGTH_SHORT).show();

        Glide.with(context).load(url_foto).into(holder.foto_perfil);


        holder.tv_nome_publicacao.setText(publicacao.getNome_usuario());
        holder.tv_texto_publicacao.setText(publicacao.getTexto_publicacao());
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

