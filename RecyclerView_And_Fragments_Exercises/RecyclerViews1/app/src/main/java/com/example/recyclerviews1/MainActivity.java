package com.example.recyclerviews1;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    // Declaramos el objeto atributo RECYCLER VIEW
    // // y un array con los String que cargará la lista
    private RecyclerView rvEquipos;
    private String[] arrayEquipos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enlazamos  el objeto RecyclerView con su equivalente en XML
        rvEquipos=findViewById(R.id.rvEquipos);

        // Cargamos el array con los nombres de los equipos
        arrayEquipos= new String[]{"Barcelona", "Real Madrid", "Atlético", "Valencia", "Sevilla",
                "Málaga","Celta","Villarreal","Athletic","Getafe","Rayo","Éibar","Levante",
                "Espanyol","Granada","Real Sociedad","Almería","Deportivo","Elche","Córdoba"};

        // Creamos un adaptador de nuestra clase
        final AdaptadorEquipos adaptadorEquipos = new AdaptadorEquipos(arrayEquipos);

        // Asignamos el Adaptador (AdaptadorEquipos) a la RecyclerView
        rvEquipos.setAdapter(adaptadorEquipos);

        // Le asignamos a nuestra RecyclerView el tipo de distribución que preferimos (grid,list,...)
        // Le asignamos un objeto LayoutManager del tipo que queramos
        // Parámetros: contexto(esta actívity), orientación del LinearLayout (en este caso elegimos linear, puede ser otro), reverseLayout=false
        rvEquipos.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }
}
