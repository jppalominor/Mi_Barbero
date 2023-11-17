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
import com.google.firebase.auth.FirebaseUser;

public class Registro extends AppCompatActivity {

    private EditText correo, pw;
    private Button btn_enviar;
    private FirebaseAuth auth;
    private FirebaseUser usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        correo = findViewById(R.id.txt_email);
        pw = findViewById(R.id.txt_pw);
        btn_enviar = findViewById(R.id.btn_login);
        btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                crearUsuario();
            }
        });



    }





    private void crearUsuario() {
        Intent intent = new Intent(Registro.this,Login.class);
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
                        startActivity(intent);
                    }
                }
            });
        }else{
            Toast.makeText(Registro.this, "Error al crear usuario", Toast.LENGTH_SHORT).show();
        }
    }
}