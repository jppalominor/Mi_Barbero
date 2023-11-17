package com.jpsapps.mibarberojp;

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

public class Login extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText correo, pw;
    private Button btn_login, btn_registrarse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        correo = findViewById(R.id.txt_email);
        pw = findViewById(R.id.txt_pw);
        btn_login = findViewById(R.id.btn_login);
        btn_registrarse = findViewById(R.id.btn_registrarse);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loguearse();
               // Intent intent = new Intent(Login.this, Home.class);
                //startActivity(intent);
            }
        });

        btn_registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(Login.this, Registro.class);
                startActivity(intent2);
            }
        });
    }

    private void loguearse() {
        Intent intent = new Intent(Login.this, Home.class);

        auth = FirebaseAuth.getInstance();

        String str_correo, str_pw;
        str_correo = correo.getText().toString();
        str_pw = pw.getText().toString();
        if (!str_correo.isEmpty() && !str_pw.isEmpty()) {
            auth.signInWithEmailAndPassword(str_correo, str_pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(Login.this, "Usuario Logueado", Toast.LENGTH_SHORT).show();
                        startActivity(intent);


                    } else {
                        Toast.makeText(Login.this, "Error al logearse", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else { Toast.makeText(Login.this,"Ingrese datos",Toast.LENGTH_SHORT).show();}
    }

}