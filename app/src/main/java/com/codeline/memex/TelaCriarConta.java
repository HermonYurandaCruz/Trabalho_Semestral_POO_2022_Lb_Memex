package com.codeline.memex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Timer;
import java.util.TimerTask;

public class TelaCriarConta extends AppCompatActivity {


    FirebaseAuth mAuth;
    EditText edt_nome;
    EditText  edt_email;
    EditText edt_senha;
    EditText edt_confirmarSenha;
    Button btn_criarConta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_criar_conta);
        getSupportActionBar().hide();
        initiateComponenent();
        mAuth = FirebaseAuth.getInstance();
        btn_criarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificarDados();
            }
        });

    }

    private void initiateComponenent(){
        edt_email = findViewById(R.id.et_emailNew);
        edt_nome = findViewById(R.id.et_usuarioNew);
        edt_senha = findViewById(R.id.et_senhaNew);
        edt_confirmarSenha = findViewById(R.id.et_confirmar_senhaNew);
        btn_criarConta = findViewById(R.id.bt_login);
    }

    private void verificarDados(){
        String email = edt_email.getText().toString();
        String senha = edt_senha.getText().toString();
        String confirmarSenha = edt_confirmarSenha.getText().toString();
        String nome = edt_nome.getText().toString();

        if(dadosPreenchidos(email, senha, confirmarSenha, nome)
                && senhasIguais(senha, confirmarSenha)){

            cadastrar(nome,email, senha);
        }else{
            Toast.makeText(this, "Verifique os campos", Toast.LENGTH_LONG).show();
        }

    }

    private boolean senhasIguais(String senha, String confirmarSenha){
        return senha.equals(confirmarSenha);
    }

    private boolean dadosPreenchidos(String email, String senha, String confirmarSenha, String nome){
        return !email.isEmpty() && !senha.isEmpty() && !confirmarSenha.isEmpty() && !nome.isEmpty();
    }

    private void cadastrar(String nome, String email, String senha){
        mAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    boolean sucesso = task.isSuccessful();
                    if (sucesso) {
                        Toast.makeText(getApplicationContext(), "Cadastrado com sucesso", Toast.LENGTH_LONG).show();
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                Intent intent=new Intent(getApplicationContext(),TelaLogin.class);
                                startActivity(intent);
                                finish();
                            }
                        },3000);
                    }else {
                        Toast.makeText(getApplicationContext(), "Erro ao cadastrar", Toast.LENGTH_LONG).show();
                    }
                }
            });
    }

    public void teste(){
        // Teste

    }







}