package com.example.recyclerview3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AdaptadorEquipos adaptador;
    private RecyclerView rvEquipos;
    public static ArrayList<Equipo> listaEquipos=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cargarListaEquipos();
        rvEquipos=findViewById(R.id.rvEquipos);
        adaptador=new AdaptadorEquipos(listaEquipos);
        adaptador.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                lanzarNuevaActivity(position);
            }
        });

        rvEquipos.setAdapter(adaptador);
        rvEquipos.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }

    public void lanzarNuevaActivity(int position){
        Intent intent=new Intent(this,VerEquipoActivity.class);
        intent.putExtra("nombre",listaEquipos.get(position).getNombre());
        intent.putExtra("puesto",position+1);
        startActivity(intent);
    }

    public void cargarListaEquipos() {
        // Sacamos los arrays de res/values
        int[] arrayPuntos = getResources().getIntArray(R.array.puntos);
        String[] arrayNombres = getResources().getStringArray(R.array.nombres);
        // Para el array de escudos, como Drawable no es un tipo de dato común,
        // sacamos un array de tipo TypedArray
        TypedArray arrayEscudosTipado=getResources().obtainTypedArray(R.array.escudos);
        // Creamos y cargamos un array normal con los valores del array tipado
        Drawable[] arrayEscudos=new Drawable[arrayEscudosTipado.length()];
        for (int i=0;i<arrayEscudosTipado.length();i++){
            arrayEscudos[i]=arrayEscudosTipado.getDrawable(i);
        }
        // Vamos creando y añadiendo los equipos a la listaEquipos
        for (int i=0;i<arrayEscudos.length;i++){
            Equipo equipo=new Equipo(arrayNombres[i],arrayEscudos[i],arrayPuntos[i]);
            this.listaEquipos.add(equipo);
        }
    }
}
