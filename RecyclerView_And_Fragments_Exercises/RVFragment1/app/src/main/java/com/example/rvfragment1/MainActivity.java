package com.example.rvfragment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Toast;

import com.example.rvfragment1.Equipos.Equipos;

public class MainActivity extends AppCompatActivity implements FragmentEquipo.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cargarDatos();
    }

    private void cargarDatos() {
        String[] nombres=getResources().getStringArray(R.array.nombres);
        int[] puntos=getResources().getIntArray(R.array.puntos);
        TypedArray escudos=getResources().obtainTypedArray(R.array.escudos);
        Drawable[] escudosDrawable=new Drawable[escudos.length()];

        for (int i=0;i<escudos.length();i++){
            escudosDrawable[i]=escudos.getDrawable(i);
        }

        for (int i=0;i<nombres.length;i++){
            Equipos.Equipo equipo=new Equipos.Equipo(nombres[i],puntos[i],escudosDrawable[i]);
            Equipos.ITEMS.add(equipo);
        }
    }

    @Override
    public void onItemClick(Equipos.Equipo equipo) {
        Toast.makeText(this, equipo.getNombre(), Toast.LENGTH_SHORT).show();
    }
}
