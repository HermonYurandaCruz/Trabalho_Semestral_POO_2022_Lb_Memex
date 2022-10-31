package com.codeline.memex;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.codeline.memex.model.Memeiros;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class MemeirosFragment extends Fragment {
    public Button bt_seguindo;

    RecyclerView recyclerView;
    MemeirosAdapter adapter;
    FirebaseFirestore dbfirebase;
    List<Memeiros> memeiros;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_memeiros, container, false);
        return view;


    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        recyclerView = view.findViewById(R.id.recyclerview_memeiros);
        dbfirebase = FirebaseFirestore.getInstance();
        memeiros = new ArrayList<>();
        adapter = new MemeirosAdapter(this.getContext(), memeiros, new MemeirosAdapter.OnItemClickedListener() {
            @Override
            public void onItemClicked(Memeiros memeiros) {
//                Toast.makeText(getContext(), memeiros.getNome_usuario(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), PerfilUsuarios.class);
                intent.putExtra("memeiros", memeiros);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        EventChangeListener();

    }

    private void EventChangeListener() {

        dbfirebase.collection("usuario").orderBy("nome_usuario", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if(error != null){
                            Log.e("Firebase error", error.getMessage());
                            return;
                        }

                        for(DocumentChange documentChange : value.getDocumentChanges())
                            if(documentChange.getType() == DocumentChange.Type.ADDED)
                                memeiros.add(documentChange.getDocument().toObject(Memeiros.class));
                        adapter.notifyDataSetChanged();
                    }

                });
    }
    //method on create view



}





