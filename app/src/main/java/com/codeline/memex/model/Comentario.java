package com.codeline.memex.model;

import static android.content.ContentValues.TAG;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Comentario implements Serializable {

    private final FirebaseFirestore dbFirestore = FirebaseFirestore.getInstance();
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private String id_comentario;
    private String id_publicacao;
    private String id_usuario;
    private String comentario;

    public Comentario() {
    }

    public Comentario(String id_comentario, String id_publicacao, String id_usuario, String comentario) {
        this.id_comentario = id_comentario;
        this.id_publicacao = id_publicacao;
        this.id_usuario = id_usuario;
        this.comentario = comentario;
    }

    public String getId_comentario() {
        return id_comentario;
    }

    public void setId_comentario(String id_comentario) {
        this.id_comentario = id_comentario;
    }

    public String getId_publicacao() {
        return id_publicacao;
    }

    public void setId_publicacao(String id_publicacao) {
        this.id_publicacao = id_publicacao;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void comentar(Comentario comentario) {
        Map<String, Object> comentarioMap = new HashMap<>();
        comentarioMap.put("id_usuario", mAuth.getCurrentUser().getUid());
        comentarioMap.put("id_publicacao", comentario.getId_publicacao());
        comentarioMap.put("comentario", comentario.getComentario());
        comentarioMap.put("nome_usuario", mAuth.getCurrentUser().getDisplayName());
        dbFirestore.collection("comentarioMap")
                .add(comentarioMap)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    public void deletarComentario(String id_comentario) {
        dbFirestore.collection("comentarioMap").document(id_comentario)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error deleting document", e);
                    }
                });
    }

    public void editarComentario(String id_comentario, String comentario) {
        dbFirestore.collection("comentarioMap").document(id_comentario)
                .update("comentario", comentario)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully updated!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                    }
                });
    }


//    //listar comentarios de uma publicacao
//    public void listarComentarios(String id_publicacao, final ComentarioCallback comentarioCallback){
//        dbFirestore.collection("comentarioMap")
//                .whereEqualTo("id_publicacao", id_publicacao)
//                .addSnapshotListener(new EventListener<QuerySnapshot>() {
//                    @Override
//                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
//                        if (e != null) {
//                            Log.w(TAG, "Listen failed.", e);
//                            return;
//                        }
//                        for (DocumentChange dc : queryDocumentSnapshots.getDocumentChanges()) {
//                            switch (dc.getType()) {
//                                case ADDED:
//                                    Log.d(TAG, "New city: " + dc.getDocument().getData());
//                                    comentarioCallback.onCallback(dc.getDocument().getData());
//                                    break;
//                                case MODIFIED:
//                                    Log.d(TAG, "Modified city: " + dc.getDocument().getData());
//                                    comentarioCallback.onCallback(dc.getDocument().getData());
//                                    break;
//                                case REMOVED:
//                                    Log.d(TAG, "Removed city: " + dc.getDocument().getData());
//                                    comentarioCallback.onCallback(dc.getDocument().getData());
//                                    break;
//                            }
//                        }
//                    }
//                });
//    }

        //TODO: listar comentarios de um usuario

//    public List<Comentario> listarComentarios(String id_publicacao){
//        dbFirestore.collection("publicacao")
//                .whereEqualTo("id_publicacao", id_publicacao)
//                .addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
//                if(e != null){
//                    Log.e("Firestore error", e.getMessage());
//                    return;
//                }
//                for(DocumentChange dc : queryDocumentSnapshots.getDocumentChanges()){
//                    if(dc.getType() == DocumentChange.Type.ADDED){
//                        switch (dc.getType()) {
//                            case ADDED:
//                                publicacoes.add(dc.getDocument().toObject(Publicacao.class));
//                                break;
//                            case MODIFIED:
//                                publicacoes.add(dc.getDocument().toObject(Publicacao.class));
//                                break;
//                            case REMOVED:
//                                publicacoes.add(dc.getDocument().toObject(Publicacao.class));
//                                break;
//                        }
//
//                    }
//                    adapter.notifyDataSetChanged();
//                }
//            }
//        });
//    }

}