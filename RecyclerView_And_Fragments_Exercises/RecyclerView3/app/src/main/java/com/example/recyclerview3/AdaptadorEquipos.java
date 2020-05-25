package com.example.recyclerview3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorEquipos extends RecyclerView.Adapter<AdaptadorEquipos.EquiposViewHolder> {

    private List<Equipo> listaEquipos;
    private OnItemClickListener onItemClickListener;

    public AdaptadorEquipos(List<Equipo> lista) {
        this.listaEquipos = lista;
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.onItemClickListener=listener;
    }

    @NonNull
    @Override
    public EquiposViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_equipos,parent,false);
        EquiposViewHolder equiposViewHolder=new EquiposViewHolder(itemView,this.onItemClickListener);
        return equiposViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EquiposViewHolder holder, int position) {
        Equipo equipo=listaEquipos.get(position);
        holder.bindEquipo(equipo);
    }

    @Override
    public int getItemCount() {
        return listaEquipos.size();
    }

    public class EquiposViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNombre;
        private TextView tvPuntos;
        private ImageView irEscudo; // Si pongo Drawable da error al hacer el findViewById

        public EquiposViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(getAdapterPosition());
                }
            });

            tvNombre =itemView.findViewById(R.id.tvNombre);
            tvPuntos=itemView.findViewById(R.id.tvPuntos);
            irEscudo =itemView.findViewById(R.id.ivEscudo);
        }


        public void bindEquipo(Equipo equipo){
            tvNombre.setText(equipo.getNombre());
            tvPuntos.setText(String.valueOf(equipo.getPuntos()));
            irEscudo.setImageDrawable(equipo.getEscudo());
        }
    }
}
