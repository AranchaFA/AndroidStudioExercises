package com.example.recyclerviews1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorEquipos extends RecyclerView.Adapter<AdaptadorEquipos.EquiposViewHolder> {

    // Atributo con el array de Strings que va a mostrar
    private String[] listaEquipos;

    // Constructor, el array que cargará se pasa por parámetro
    public AdaptadorEquipos(String[] listaEquipos) {
        this.listaEquipos = listaEquipos;
    }

    // Devuelve el número de elementos de la lista
    @Override
    public int getItemCount() {
        return listaEquipos.length;
    }


    /*
        VIEW HOLDER (SUBCLASE)
     */
    // Es como una especie de interfaz para visualizar la información del array
    // (o BBDD, fichero,... la fuente que usemos) al usuario
    public class EquiposViewHolder extends RecyclerView.ViewHolder {

        // Atributo con el elemento que mostrará (el nombre del equipo en un TextView)
        private TextView tvNombre;

        // EJERCICIO 1 : Mostrar un toast con la posición clickada

        // Constructor
        public EquiposViewHolder(@NonNull final View itemView) {
            super(itemView);

            // Cargamos el atributo tvNombre del EquipoViewHolder
            tvNombre = (TextView) itemView; // Podemos asignarle el itemView porque se trata de un layout que sólo es un TextView

            // Le asignamos un Listener a la "casilla" para que detecte cuándo la pulsamos,
            // y muestre en un Toast el elemento que hemos pulsado
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Contexto en que se muestra, mensaje, duración
                    Toast.makeText(view.getContext(),
                            "Has pulsado el elemento en la posición: " + getAdapterPosition(),
                            Toast.LENGTH_SHORT).show();
                }
            });
        }


        // Método para enlazar (bind) la información a mostrar (String del array de equipos) con la vista
        public void bindEquipo(String nombreEquipo) {
            tvNombre.setText(nombreEquipo);
        }
    }

    // onCreate del ViewHolder asociado a esta clase de adaptador, crea un objeto del tipo
    // ViewHolder de esta clase. recibe como parámetro al parent(un objeto AdaptadorEquipos)
    // supongo que para "sacar" la subclase de su ViewHolder (EquiposViewHolder)
    @NonNull
    @Override
    public EquiposViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Creamos un objeto para inflar ("desplegar") la lista
        // Le especificamos el contexto desde el que obtenemos el inflador (nuestra clase AdaptadorEquipos)
        // Parámetros:
        // - El layout que usará (uno predefinido por android)
        // - El ViewGroup del que parte (nuestra clase AdaptadorEquipos)
        // - False: no se inflará en el instante de ejecutarlo (True: Se infla en el instante en que se ejecuta)
        View itemView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);

        // Creamos el objeto EquiposViewHolder que vamos a devolver.
        // Recibe como parámetro el inflador que se encargará de "desplegarlo"

        EquiposViewHolder equiposViewHolder_Creado = new EquiposViewHolder(itemView); // EJERCICIO 1

        return equiposViewHolder_Creado;
    }

    // Método que enlaza (adapta) el ViewHolder (los datos) con la "casilla" que mostrará la vista
    // Le pasamos un EquiposViewHolder que llevará la información del elemento a mostrar
    // Le pasamos la posición que le corresponde en la lista
    @Override
    public void onBindViewHolder(@NonNull EquiposViewHolder holder, int position) {
        // Sacamos el String a mostrar en la RecyclerView
        // (en este caso la lista sólo mostrará el nombre del equipo, podría mostrar más cosas)
        String equipo = listaEquipos[position];
        // Enlazamos la información a mostrar en la "casilla" de la lista usando el objeto holder
        holder.bindEquipo(equipo);
    }
}
