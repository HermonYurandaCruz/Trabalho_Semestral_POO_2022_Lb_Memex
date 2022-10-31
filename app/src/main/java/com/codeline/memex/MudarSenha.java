package com.codeline.memex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class MudarSenha extends AppCompatActivity {

    FirebaseAuth mAuth;

    TextView tv_esqueceuSenha;
    Button btn_mudarSenha;
    EditText edt_senhaActual;
    EditText edt_novaSenha;
    EditText edt_confirmarSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mudar_senha);
        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        inicializarComponentes();
        tv_esqueceuSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.sendPasswordResetEmail(mAuth.getCurrentUser().getEmail())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(getApplicationContext(), "Enviamos uma mensagem para seu email com um link para redefinir a senha",
                                        Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Erro ao enviar email",
                                        Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });
        btn_mudarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mudarSenha();
            }
        });


    }


    public void inicializarComponentes() {
        tv_esqueceuSenha = findViewById(R.id.esqueceu_senha);
        edt_senhaActual = findViewById(R.id.senhaActual);
        edt_confirmarSenha = findViewById(R.id.confirmarNovaSenha);
        edt_novaSenha = findViewById(R.id.novaSenha);
        btn_mudarSenha = findViewById(R.id.btnMudarSenha);
    }

    public void mudarSenha() {
        if (edt_senhaActual.getText().toString().equals(edt_novaSenha.getText().toString())) {
            Toast.makeText(getApplicationContext(), "A nova senha não pode ser igual a senha actual",
                    Toast.LENGTH_LONG).show();
        } else if (edt_novaSenha.getText().toString().equals(edt_confirmarSenha.getText().toString())) {
            mAuth.getCurrentUser().updatePassword(edt_novaSenha.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(getApplicationContext(), "Senha alterada com sucesso",
                                    Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Erro ao alterar senha" + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
        } else {
            Toast.makeText(getApplicationContext(), "As senhas não coincidem",
                    Toast.LENGTH_LONG).show();
        }
    }

}