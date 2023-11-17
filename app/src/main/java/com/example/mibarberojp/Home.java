package com.example.mibarberojp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends AppCompatActivity {
    private FirebaseAuth auth;
    private FirebaseUser user;
    private Button btn_agendar, btn_citas, btn_logout;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tv = findViewById(R.id.txt_bienvenida);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        btn_logout = findViewById(R.id.btn_logout);
        btn_citas = findViewById(R.id.btn_citas);
        btn_agendar = findViewById(R.id.btn_agendar);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(Home.this,Login.class);
                auth.signOut();
                startActivity(intent3);

            }
        });

        btn_agendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Agendar.class);
                startActivity(intent);
            }
        });

        btn_citas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,Citas.class);
                startActivity(intent);
            }
        });

        if (user != null){
            btn_logout.setVisibility(View.VISIBLE);
            tv.setText("Hola "+user.getEmail());

        }else{
            btn_logout.setVisibility(View.GONE);
            tv.setVisibility(View.GONE);
        }

    }
}