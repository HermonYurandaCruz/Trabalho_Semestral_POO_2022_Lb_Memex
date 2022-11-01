package com.codeline.memex;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class TelaLogin extends AppCompatActivity {
    private Button bt_entrar;
    private TextView tv_criarConta;
    private TextView tv_EsqueceuSenha;
    private EditText edt_email;
    private EditText edt_senha;
    private FirebaseAuth mAuth;
    private GoogleSignInClient client;
    private TextView tv_google;
    private FirebaseFirestore dbFirestore;
    private TextView tv_github;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);
        getSupportActionBar().hide();
        IniciarNovoCadastro();
        mAuth = FirebaseAuth.getInstance();
        dbFirestore = FirebaseFirestore.getInstance();
        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        client = GoogleSignIn.getClient(this, options);
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
                startActivity(new Intent(TelaLogin.this, RecuperarSenha.class));
            }
        });






        bt_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarSessao();
            }
        });

        tv_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = client.getSignInIntent();
                startActivityForResult(intent, 1234);
            }
        });

        tv_github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(TelaLogin.this, TelaLoginGithub.class);
//                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1234){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
                mAuth.signInWithCredential(credential)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    if(!dbFirestore.collection("usuario").document("email").equals(mAuth.getCurrentUser().getEmail()))
                                        salvarUsuarioNoFirestore();
                                    Intent intent = new Intent(getApplicationContext(), Home.class);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(TelaLogin.this, "Erro" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            } catch (ApiException e) {
                e.printStackTrace();
                Toast.makeText(TelaLogin.this, "Erro/Excepção" + e , Toast.LENGTH_LONG).show();;

            }
        }
    }

    private void salvarUsuarioNoFirestore(){
        Map<String, Object> usuario = new HashMap<>();
        usuario.put("email", mAuth.getCurrentUser().getEmail().toString());
        usuario.put("nome_usuario", mAuth.getCurrentUser().getDisplayName());
        usuario.put("id_usuario", mAuth.getCurrentUser().getUid());
        usuario.put("url_foto_perfil", mAuth.getCurrentUser().getPhotoUrl().toString());
        dbFirestore.collection("usuario")
                .add(usuario)
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

    private void AbrirTelaHome(){
        Intent intent=new Intent(TelaLogin.this,Home.class);
        startActivity(intent);
        this.finish();

    }

    private  void IniciarNovoCadastro(){
        edt_email = findViewById(R.id.usuarioLogin);
        edt_senha = findViewById(R.id.ed_senhaLogin);
        bt_entrar=findViewById(R.id.bt_login);
        tv_criarConta=findViewById(R.id.tv_criar_conta);
        tv_EsqueceuSenha = findViewById(R.id.tv_esquecu_senha);
        tv_google = findViewById(R.id.tv_google);
        tv_github = findViewById(R.id.tv_gitHub);
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
                            finish();
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
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            AbrirTelaHome();
            finish();
        }

    }
    //recive a result in real time from firestore
//    private void getRealTimeUpdates(){
//        dbFirestore.collection("usuario")
//                .addSnapshotListener(new EventListener<QuerySnapshot>() {
//                    @Override
//                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
//                        if(e != null){
//                            Log.w(TAG, "Listen failed.", e);
//                            return;
//                        }
//                        for (DocumentChange dc : queryDocumentSnapshots.getDocumentChanges()) {
//                            switch (dc.getType()) {
//                                case ADDED:
//                                    Log.d(TAG, "New city: " + dc.getDocument().getData());
//                                    break;
//                                case MODIFIED:
//                                    Log.d(TAG, "Modified city: " + dc.getDocument().getData());
//                                    break;
//                                case REMOVED:
//                                    Log.d(TAG, "Removed city: " + dc.getDocument().getData());
//                                    break;
//                            }
//                        }
//                    }
//                });
//    }




}