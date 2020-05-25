package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

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

        // EJERCICIO 2: Usando nuestra propia interface Listener

        // Creamos un adaptador de nuestra clase
        final AdaptadorEquipos adaptadorEquipos = new AdaptadorEquipos(arrayEquipos);

        // Asignamos el listener al adaptador, y sobreescribimos el método onItemClick
        // con las acciones que queremos que haga: abrir la nueva activity para mostrar
        // el elemento seleccionado (para ello tenemos que pasarle la posición)
        adaptadorEquipos.setOnItemClickListener(new AdaptadorEquipos.OnItemClickListener() {
            @Override
            public void onItemClick(int itemPosition) {
                lanzarActivityVerEquipo(arrayEquipos[itemPosition]);
            }
        });

        // Asignamos el Adaptador (AdaptadorEquipos) a la RecyclerView
        rvEquipos.setAdapter(adaptadorEquipos);

        // Le asignamos a nuestra RecyclerView el tipo de distribución que preferimos (grid,list,...)
        // Le asignamos un objeto LayoutManager del tipo que queramos
        // Parámetros: contexto(esta actívity), orientación del LinearLayout (en este caso elegimos linear, puede ser otro), reverseLayout=false
        rvEquipos.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));



    }

    // Método para lanzar la nueva activity que muestre el elemento clickado
    public void lanzarActivityVerEquipo(String nombreEquipo){
        Intent intent=new Intent(this,VerEquipoActivity.class);
        // Pasamos en el intent el nombre (no la posición numérica) del equipo clickado
        intent.putExtra("nombreEquipo",nombreEquipo);
        startActivity(intent);
    }
}
