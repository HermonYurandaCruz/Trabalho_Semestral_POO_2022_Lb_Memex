package com.codeline.memex;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.codeline.memex.model.Memeiros;
import com.codeline.memex.model.Publicacao;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MeuPerfil extends AppCompatActivity {
    TextView tv_meuPerfil;
    TextView tv_editar;
    CircleImageView cimv;
    TextView tv_nomeUsuario;
    FirebaseAuth mAuth;
    private Handler handler = new Handler();
    RecyclerView recyclerView;
    MeuPerfilAdapter adapter;
    FirebaseFirestore dbfirebase;
    List<Publicacao> publicacoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_meu_perfil);
        tv_meuPerfil=findViewById(R.id.MeuPerfil);
        cimv = findViewById(R.id.iv_perfil_meuPerfil);
        tv_nomeUsuario = findViewById(R.id.tv_nomeUsuarioMeuPerfil);

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {
            String URL = currentUser.getPhotoUrl().toString();
            tv_nomeUsuario.setText(currentUser.getDisplayName());
            carregarFotoDePerfil(URL);
        }
        tv_meuPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // FragmentManager.popBackStack();

            }
        });

      tv_editar =findViewById(R.id.tv_editar);
     tv_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MeuPerfil.this,EditarMeuPerfil.class);
                startActivity(intent);
                }

        });


        recyclerView = findViewById(R.id.recyclervirew_minhas_publicacoes);
        dbfirebase = FirebaseFirestore.getInstance();
        publicacoes = new ArrayList<>();
        adapter = new MeuPerfilAdapter(getApplicationContext(), publicacoes);
        recyclerView.setAdapter(adapter);
        EventChangeListener();


    }

    private void EventChangeListener() {

        dbfirebase.collection("publicacao").whereEqualTo("id_usuario", mAuth.getUid()).orderBy("data", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if(error != null){
                            Log.e("Firebase error", error.getMessage());
                            return;
                        }

                        for(DocumentChange documentChange : value.getDocumentChanges())
                            if(documentChange.getType() == DocumentChange.Type.ADDED)
                                publicacoes.add(documentChange.getDocument().toObject(Publicacao.class));
                        adapter.notifyDataSetChanged();
                    }

                });
    }

    public void carregarFotoDePerfil(String URL) {
        new Thread() {
            public void run() {
                Bitmap img = null;


                try {
                    URL url = new URL(URL);
                    HttpURLConnection connexao = (HttpURLConnection) url.openConnection();
                    InputStream input = connexao.getInputStream();
                    img = BitmapFactory.decodeStream(input);
                } catch (IOException ignored) {}
                    final Bitmap imgAux = img;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            cimv.setImageBitmap(imgAux);


                        }
                });


            }
        }.start();
    }

//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        bt_editar = findViewById(R.id.bt_editar);
//        bt_editar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MeuPerfil.this, EditarMeuPerfil.class);
//                startActivity(intent);
//            }
//        });
//    }

}