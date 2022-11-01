package com.codeline.memex.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.codeline.memex.R;

public class popUpReacoes extends AppCompatActivity {
    public ImageView iv_emoj_riso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_reacoes);
        iv_emoj_riso=findViewById(R.id.iv_emoj_riso);

        iv_emoj_riso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(popUpReacoes.this,Home.class);
                startActivity(intent);
                finish();
            }
        });
    }
}