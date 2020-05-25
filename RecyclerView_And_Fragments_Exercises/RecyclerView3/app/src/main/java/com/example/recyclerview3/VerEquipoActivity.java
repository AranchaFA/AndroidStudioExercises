package com.example.recyclerview3;

import android.os.Bundle;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

public class VerEquipoActivity extends AppCompatActivity {

    private TextView tvNombreEquipo;
    private TextView tvPuestoClasificacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_equipo);

        tvNombreEquipo = findViewById(R.id.tvNombreEquipo);
        tvPuestoClasificacion = findViewById(R.id.tvClasificacion);

        tvNombreEquipo.setText(getIntent().getExtras().getString("nombre"));
        tvPuestoClasificacion.setText(String.valueOf(getIntent().getExtras().getInt("puesto")));
    }

}
