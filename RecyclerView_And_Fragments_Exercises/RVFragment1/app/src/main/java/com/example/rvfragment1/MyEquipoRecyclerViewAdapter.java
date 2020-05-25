package com.example.rvfragment1;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rvfragment1.FragmentEquipo.OnItemClickListener;
import com.example.rvfragment1.Equipos.Equipos.Equipo;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Equipo} and makes a call to the
 * specified.
 */
public class MyEquipoRecyclerViewAdapter extends RecyclerView.Adapter<MyEquipoRecyclerViewAdapter.ViewHolder> {

    private final List<Equipo> listaEquiposAdapter;
    private final OnItemClickListener mListener;

    public MyEquipoRecyclerViewAdapter(List<Equipo> items, OnItemClickListener listener) {
        listaEquiposAdapter = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_equipo_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Equipo equipo = listaEquiposAdapter.get(position);
        holder.nombre.setText(listaEquiposAdapter.get(position).getNombre());
        holder.escudo.setImageDrawable(listaEquiposAdapter.get(position).getEscudo());
        holder.puntos.setText(String.valueOf(listaEquiposAdapter.get(position).getPuntos()));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onItemClick(equipo);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaEquiposAdapter.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        public final TextView nombre;
        public final ImageView escudo;
        public final TextView puntos;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            nombre = (TextView) view.findViewById(R.id.tvNombre);
            escudo = view.findViewById(R.id.ivEscudo);
            puntos = view.findViewById(R.id.tvPuntos);
        }
    }
}
