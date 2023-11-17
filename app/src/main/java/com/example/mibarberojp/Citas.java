package com.example.mibarberojp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.mibarberojp.adapter.CitaAdapter;
import com.example.mibarberojp.data.Cita;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Citas extends AppCompatActivity {

    private List<Cita> listaCitas;
    private RecyclerView rv;
    private CitaAdapter citaAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas);

        rv = findViewById(R.id.lista_citas);
        rv.setLayoutManager(new LinearLayoutManager(this));
        listaCitas = new ArrayList<>();
        citaAdapter = new CitaAdapter(listaCitas);
        rv.setAdapter(citaAdapter);
        
        
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference dbr = db.getReference("Citas");
            dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaCitas.clear();
                Cita temp;
                for (DataSnapshot ds : snapshot.getChildren()) {
                        temp = ds.getValue(Cita.class);
                        listaCitas.add(temp);

                }
                citaAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        }
    }
