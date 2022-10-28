package com.codeline.memex;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.codeline.memex.model.Publicacao;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

//follow unfollow
public class  HomeFragment extends Fragment {

private TextView tvUsuarioPublicacao,et_pesquisar;
    ImageView  iv_post,iv_curtida,iv_criarMeme;
    View view;
    private HomeAdapter adapter;
    FirebaseFirestore dbfirebase;
    List<Publicacao> publicacoes;

    private androidx.appcompat.widget.AppCompatButton botaoFollow;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_home, container, false);
        Inicializar();




//        iv_curtida.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // inflate the layout of the popup window
//                LayoutInflater inflater = (LayoutInflater)
//                        getActivity(). getSystemService(LAYOUT_INFLATER_SERVICE);
//                View popupView = inflater.inflate(R.layout.activity_pop_up_reacoes, null);
//
//                // create the popup window
//                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
//                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
//                boolean focusable = true; // lets taps outside the popup also dismiss it
//                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
//
//                // show the popup window
//                // which view you pass in doesn't matter, it is only used for the window tolken
//                popupWindow.showAtLocation(v, Gravity.CENTER, 0, -10);
//
//                // dismiss the popup window when touched
//                popupView.setOnTouchListener(new View.OnTouchListener() {
//                    @Override
//                    public boolean onTouch(View v, MotionEvent event) {
//                        popupWindow.dismiss();
//                        return true;
//                    }
//                });
//            }
//        });
//
//
//
//
//
//
//
//
//
//
//
//         tvUsuarioPublicacao.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View v) {
//                 startActivity(new Intent(getActivity(), PerfilUsuarios.class));
//
//             }
//         });
        et_pesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Pesquisar.class));

            }
        });
//
//
//
//
        iv_criarMeme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CriarMeme.class));

            }
        });
//
        return view;
    }

    public void Inicializar(){
        tvUsuarioPublicacao=view.findViewById(R.id.tv_usuario_publicacao);

        iv_post=view.findViewById(R.id.iv_post);
        iv_curtida =view.findViewById(R.id.iv_reagir_meme);
        iv_criarMeme =view.findViewById(R.id.iv_criar_meme);
        et_pesquisar=view.findViewById(R.id.et_pesquisar);

    }

    public void onViewCreated(View view, @Nullable Bundle savedInstaceState){
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_publicacoes);
        dbfirebase = FirebaseFirestore.getInstance();
        publicacoes = new ArrayList<>();
        adapter = new HomeAdapter(getActivity(), publicacoes);
        recyclerView.setAdapter(adapter);
        EventChangeListener();
    }

    private void EventChangeListener(){
        dbfirebase.collection("publicacao").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if(e != null){
                    Log.e("Firestore error", e.getMessage());
                    return;
                }
                for(DocumentChange dc : queryDocumentSnapshots.getDocumentChanges()){
                    if(dc.getType() == DocumentChange.Type.ADDED){
                        publicacoes.add(dc.getDocument().toObject(Publicacao.class));
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }




}


