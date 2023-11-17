package com.jpsapps.mibarberojp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.jpsapps.mibarberojp.data.Cita;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Agendar extends AppCompatActivity {

    private Button btn_calendario;
    private String barber;

    private EditText cliente;

    private Spinner spinner;

    private Button btn_crearcita;

    private  Button btn_hora;
    private TextView tv, tvHora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar);

        btn_crearcita = findViewById(R.id.btn_crearcita);
        spinner = findViewById(R.id.spinner_barberos);
        btn_hora = findViewById(R.id.btn_hora);
        tv = findViewById(R.id.txt_fecha);
        tvHora = findViewById(R.id.txt_hora);
        btn_calendario = findViewById(R.id.btn_fecha);
        cliente = findViewById(R.id.txt_cliente);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Agendar.this,R.array.barberos,
                android.R.layout.simple_spinner_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                barber = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btn_calendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int mes = cal.get(Calendar.MONTH);
                int dia = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(Agendar.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int i, int i1, int i2) {
                        String fecha =  i2 + "/" + i1 + "/" + i;
                        tv.setText(fecha);

                    }
                }, year, mes, dia);
                dpd.show();
            }
        });


        btn_hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int hora = c.get(Calendar.HOUR_OF_DAY);
                int min = c.get(Calendar.MINUTE);

                TimePickerDialog tpd = new TimePickerDialog(Agendar.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        tvHora.setText(i + ":" + i1);
                    }
                },hora, min, false);
                tpd.show();
            }
        });

        btn_crearcita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fechacita, horacita, clientecita;

                fechacita = tv.getText().toString();
                horacita = tvHora.getText().toString();
                clientecita = cliente.getText().toString();

                FirebaseDatabase db = FirebaseDatabase.getInstance();
                DatabaseReference dbr = db.getReference("Citas");
                String llave = dbr.push().getKey();
                dbr.child(llave).setValue(new Cita(barber, fechacita, horacita, clientecita));

                startActivity(new Intent(Agendar.this, Agendar.class));
            }
        });
    }
}