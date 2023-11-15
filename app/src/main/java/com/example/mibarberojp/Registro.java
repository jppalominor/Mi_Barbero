package com.example.mibarberojp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registro extends AppCompatActivity {

    private EditText correo, pw;
    private Button btn_enviar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        correo = findViewById(R.id.txt_email);
        pw = findViewById(R.id.txt_pw);
        btn_enviar = findViewById(R.id.btn_registro);
        btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearUsuario();
                Intent intent = new Intent(Registro.this,Home.class);

                startActivity(intent);
            }
        });



    }

    private void crearUsuario() {
        String str_correo, str_pw;

        auth = FirebaseAuth.getInstance();
        str_correo = correo.getText().toString();
        str_pw = pw.getText().toString();

        if (!str_correo.isEmpty() && str_pw.length() >= 6) {
            auth.createUserWithEmailAndPassword(str_correo, str_pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(Registro.this, "Usuario creado", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            Toast.makeText(Registro.this, "Error al crear usuario", Toast.LENGTH_SHORT).show();
        }
    }
}