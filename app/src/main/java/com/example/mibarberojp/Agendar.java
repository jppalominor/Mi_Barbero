package com.example.mibarberojp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

public class Agendar extends AppCompatActivity {

    private Button btn_calendario;
    private String barber;

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
                //TODO: Crear cita en la bd
            }
        });
    }
}