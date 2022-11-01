package com.codeline.memex.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codeline.memex.R;
import com.codeline.memex.adapter.ComentarAdapter;
import com.codeline.memex.model.Comentario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ComentarMeme extends AppCompatActivity {
    RecyclerView recyclerView;
    EditText et_comentario;
    ImageView iv_enviarComentario;
    FirebaseAuth mAuth;
    FirebaseFirestore dbFirebase;
//    Drawable drawable = getResources().getDrawable(R.drawable.enviar_comentario);
    String id_publicacao = "";
    ArrayList<Comentario> comentarios;
    ComentarAdapter adapter;
    TextView tv_comentario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentar_meme);
        getSupportActionBar().hide();
        inicializarComponentes();
        Bundle bundle = getIntent().getExtras();
        id_publicacao = bundle.getString("id_publicacao");
        Toast.makeText(this, "id_publicacao: " + id_publicacao, Toast.LENGTH_SHORT).show();
        mAuth = FirebaseAuth.getInstance();
        dbFirebase = FirebaseFirestore.getInstance();
        et_comentario.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!et_comentario.getText().toString().isEmpty()){
                    iv_enviarComentario.setImageResource(R.drawable.enviar_comentario);
                }else{
                    iv_enviarComentario.setImageResource(R.drawable.keyboard_voice);
                }
            }
        });

        iv_enviarComentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!et_comentario.getText().toString().isEmpty()){
                    enviarComentario();
                }else{
                    enviarAudio();
                }
            }
        });
        comentarios = new ArrayList<>();
        adapter = new ComentarAdapter(comentarios, getApplicationContext());
        recyclerView.setAdapter(adapter);
        EventChangeListener();
    }

    private void EventChangeListener(){
        dbFirebase.collection("publicacao").document(id_publicacao).collection("comentario")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if(e != null){
                    Log.e("Firestore error", e.getMessage());
                    return;
                }
                for(DocumentChange dc : queryDocumentSnapshots.getDocumentChanges()){
                    if(dc.getType() == DocumentChange.Type.ADDED){
                        switch (dc.getType()) {
                            case ADDED:
                                comentarios.add(dc.getDocument().toObject(Comentario.class));
                                break;
                            case MODIFIED:
                                comentarios.add(dc.getDocument().toObject(Comentario.class));
                                break;
                            case REMOVED:
                                comentarios.add(dc.getDocument().toObject(Comentario.class));
                                break;
                        }

                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
    private void enviarComentario(){
        Toast.makeText(getApplicationContext(), "Enviando comentario", Toast.LENGTH_LONG).show();
        Map<String, Object> comentario = new HashMap<>();
        comentario.put("comentario", et_comentario.getText().toString());
        comentario.put("id_utilizador", mAuth.getCurrentUser().getUid());
        comentario.put("id_publicacao", id_publicacao);
        dbFirebase.collection("publicacao").document(id_publicacao).collection("comentario").add(comentario).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Comentário enviado com sucesso", Toast.LENGTH_LONG).show();
                    et_comentario.setText("");
                }else{
                    Toast.makeText(getApplicationContext(), "Erro ao enviar comentário", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void enviarAudio(){
        Toast.makeText(this, "Em breve", Toast.LENGTH_SHORT).show();
    }




    public void inicializarComponentes(){
        et_comentario = findViewById(R.id.et_comentario);
        iv_enviarComentario = findViewById(R.id.iv_enviarComentario);
        recyclerView = findViewById(R.id.recyclerview_comentario);
        et_comentario = findViewById(R.id.et_comentario);
    }
}