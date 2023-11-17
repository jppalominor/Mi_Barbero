package com.example.mibarberojp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mibarberojp.Citas;
import com.example.mibarberojp.R;
import com.example.mibarberojp.data.Cita;

import java.util.List;

public class CitaAdapter extends RecyclerView.Adapter<CitaAdapter.ViewHolder> {
    private List<Cita> listaCitas;

    public CitaAdapter(List<Cita> listaCitas){
        this.listaCitas = listaCitas;
    }



    @NonNull
    @Override
    public CitaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_cita,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CitaAdapter.ViewHolder holder, int position) {
        Cita temp = listaCitas.get(position);

        holder.barbero.setText(temp.getBarbero());
        holder.dia.setText(temp.getFecha());
        holder.hora.setText(temp.getHora());
        holder.cliente.setText(temp.getCliente());
    }

    @Override
    public int getItemCount() {
        return listaCitas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView barbero,dia,hora,cliente;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            barbero = itemView.findViewById(R.id.nombre_barber);
            dia = itemView.findViewById(R.id.dia_cita);
            hora = itemView.findViewById(R.id.hora_cita);
            cliente = itemView.findViewById(R.id.cliente_cita);
        }
    }
}
