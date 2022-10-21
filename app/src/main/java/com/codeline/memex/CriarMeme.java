package com.codeline.memex;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.IOException;

public class CriarMeme extends AppCompatActivity {
ImageView iv_galeria,imagem_meme;
    private static final int PICK_IMAGE=1;
    Uri imageUir;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==PICK_IMAGE && resultCode==RESULT_OK){
            imageUir=data.getData();
            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),imageUir);
                imagem_meme.setImageBitmap(bitmap);

            }catch (IOException e){e.printStackTrace();}


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_meme);
        getSupportActionBar().hide();
        IniciarComponentes();

        iv_galeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galeria=new Intent();
                galeria.setType("image/*");
                galeria.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(galeria,"Sellect Picture"),PICK_IMAGE);
            }
        });








    }
    public void IniciarComponentes(){
        iv_galeria=findViewById(R.id.iv_galeria);
        imagem_meme=findViewById(R.id.imagem_meme);

    }

}