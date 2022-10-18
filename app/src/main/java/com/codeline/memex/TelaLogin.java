package com.codeline.memex;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TelaLogin extends AppCompatActivity {
    private Button bt_entrar;
    private TextView tv_criarConta;
    private TextView tv_EsqueceuSenha;
    private EditText edt_email;
    private EditText edt_senha;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);
        getSupportActionBar().hide();
    IniciarNovoCadastro();
        mAuth = FirebaseAuth.getInstance();
    tv_criarConta.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(TelaLogin.this,TelaCriarConta.class);
            startActivity(intent);

        }
    });

    tv_EsqueceuSenha.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
//            Intent intent = new Intent(TelaLogin.this, ActivityRecuperarSenha.class);
//            startActivity(intent);
        }
    });






    bt_entrar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            iniciarSessao();
        }
    });
    }
    private void AbrirTelaHome(){
        Intent intent=new Intent(TelaLogin.this,Home.class);
        startActivity(intent);
        finish();

    }





    private  void IniciarNovoCadastro(){
        edt_email = findViewById(R.id.usuarioLogin);
        edt_senha = findViewById(R.id.senhaLogin);
        bt_entrar=findViewById(R.id.bt_login);
        tv_criarConta=findViewById(R.id.tv_criar_conta);
        tv_EsqueceuSenha = findViewById(R.id.tv_esquecu_senha);
      /*
        et_email=findViewById(R.id.emailLogin);
        et_passWord=findViewById(R.id.PasswordLogin);
        bt_login=findViewById(R.id.BotaoLogin);
        progressBar=findViewById(R.id.progresse);
        tv_recuperarSenha=findViewById(R.id.Tv_recuperarSenha);

        */
    }

    private void iniciarSessao(){
        String email = edt_email.getText().toString();
        String password = edt_senha.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCustomToken:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(TelaLogin.this, "Tudo nice", Toast.LENGTH_LONG).show();
                            AbrirTelaHome();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCustomToken:failure", task.getException());
                            Toast.makeText(TelaLogin.this, "Dados incorrectos",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });
    }
}