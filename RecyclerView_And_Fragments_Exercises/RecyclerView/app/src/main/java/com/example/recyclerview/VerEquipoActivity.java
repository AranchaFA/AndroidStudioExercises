package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class VerEquipoActivity extends AppCompatActivity {

    private TextView tvNombreEquipo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_equipo);

        tvNombreEquipo=findViewById(R.id.tvNombreEquipo);

        // Esto peta
       tvNombreEquipo.setText(getIntent().getExtras().getString("nombreEquipo"));
    }

}
