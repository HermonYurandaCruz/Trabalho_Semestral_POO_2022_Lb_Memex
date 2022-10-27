package com.codeline.memex;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CriarMeme extends AppCompatActivity {
    ImageView iv_galeria,imagem_meme;
    Button bt_publicar;
    TextView et_meme;
    private static final int PICK_IMAGE=1;
    private FirebaseFirestore mfirestore;
    private FirebaseAuth mAuth;
    StorageReference storageReference;
    String storage_path = "imagens_de_publicacoes/*";
    private static final int COD_SEL_STORAGE = 200;
    private static final int COD_SEL_IMAGE = 300;
    private Uri image_url;
    String photo = "photo";
    String idd;
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm/ddMM/yyyy", Locale.getDefault());
    String data = sdf.format(new Date());


    ProgressDialog progressDialog;



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(resultCode == RESULT_OK){
            if (requestCode == COD_SEL_IMAGE){
                image_url = data.getData();
                Picasso.with(this).load(image_url).into(imagem_meme);

            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_meme);
        getSupportActionBar().hide();
        IniciarComponentes();


        progressDialog = new ProgressDialog(this);
        String id = getIntent().getStringExtra("id_post");
        mfirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();


        iv_galeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i, COD_SEL_IMAGE);
            }
        });

         bt_publicar.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String TextoMeme=et_meme.getText().toString().trim();
                 String linkfoto= String.valueOf(image_url);

                 if(TextoMeme.isEmpty()&& linkfoto.isEmpty() ) {
                     Toast.makeText(CriarMeme.this, "Escreva um texto ou adicione uma foto", Toast.LENGTH_SHORT).show();
                 } else
                 Publicacao(TextoMeme,"www.com.codeLine");

             }
         });









    }


    public void IniciarComponentes(){
        iv_galeria=findViewById(R.id.iv_galeria);
        imagem_meme=findViewById(R.id.imagem_meme);
        bt_publicar=findViewById(R.id.bt_publicar);
        et_meme=findViewById(R.id.et_meme);

    }

    private void Publicacao(String textMeme, String link) {
        String idUser = mAuth.getCurrentUser().getUid();
        DocumentReference id = mfirestore.collection("publicacao").document();

        String rute_storage_photo = storage_path + "" + photo + "" + mAuth.getUid() +""+ id.getId();

        Map<String, Object> map = new HashMap<>();
        map.put("id_usuario", idUser);
        map.put("id_post", id.getId());
        map.put("data",data);
        map.put("texto_publicacao",textMeme);
        map.put("url_imagem", rute_storage_photo);




        progressDialog.setMessage("A Criar meme");
        progressDialog.show();
        StorageReference reference = storageReference.child(rute_storage_photo);
        reference.putFile(image_url).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isSuccessful());
                if (uriTask.isSuccessful()){
                    uriTask.addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String download_uri = uri.toString();
                            HashMap<String, Object> map = new HashMap<>();
                            map.put("url_imagem", download_uri);
                            mfirestore.collection("publicacao").document(id.getId()).update(map);
        //                  Toast.makeText(CriarMeme.this, "Imagem adicionada", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(CriarMeme.this, "Error ao adicionar Imagem", Toast.LENGTH_SHORT).show();
            }
        });














        mfirestore.collection("publicacao").document(id.getId()).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getApplicationContext(), "Meme criado com sucesso", Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error ao criar, tente novamente", Toast.LENGTH_SHORT).show();

            }
        });
    }





}