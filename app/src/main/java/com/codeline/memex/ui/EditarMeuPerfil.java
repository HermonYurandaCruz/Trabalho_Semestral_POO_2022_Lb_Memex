package com.codeline.memex.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.codeline.memex.R;

import java.io.IOException;

public class EditarMeuPerfil extends AppCompatActivity {
    ImageView iv_editarFoto;
    Button bt_concluirEdicao;
    private static final int PICK_IMAGE=1;
    Uri imageUir;
    MeuPerfil meuPerfil=new MeuPerfil();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==PICK_IMAGE && resultCode==RESULT_OK){
            imageUir=data.getData();
            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),imageUir);
                iv_editarFoto.setImageBitmap(bitmap);

            }catch (IOException e){e.printStackTrace();}


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_meu_perfil);
        getSupportActionBar().hide();
        Inicializar();
        iv_editarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galeria=new Intent();
                galeria.setType("image/*");
                galeria.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(galeria,"Sellect Picture"),PICK_IMAGE);
            }
        });

        bt_concluirEdicao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            finish();
            }
        });





    }
    public void Inicializar(){
        iv_editarFoto=findViewById(R.id.iv_editarFoto);
        bt_concluirEdicao=findViewById(R.id.bt_concluir);

    }

}