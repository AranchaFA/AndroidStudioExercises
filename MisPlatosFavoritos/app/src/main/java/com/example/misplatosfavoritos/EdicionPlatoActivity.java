package com.example.misplatosfavoritos;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class EdicionPlatoActivity extends AppCompatActivity {

    private Spinner spinnerTipoPlato;
    private Spinner spinnerCategoriaPlato;
    private TextInputEditText tiet_nombrePlato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicion_plato);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        montarSpinners();
    }

    public void montarSpinners() {
        // Enlazamos los atributos Spinner a los elementos XML
        spinnerTipoPlato = findViewById(R.id.spinner_tipoPlato);
        spinnerCategoriaPlato = findViewById(R.id.spinner_categoriaPlato);

        // Creamos el adaptador para los Spinner
        ArrayAdapter<CharSequence> adapterSpinnerTipoPlato = ArrayAdapter.createFromResource(this,
                R.array.tipoPlato, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterSpinnerCategoriaPlato = ArrayAdapter.createFromResource(this,
                R.array.categoriaPlato, android.R.layout.simple_spinner_item);
        // Asignamos a los adaptadores los layouts para la función desplegable
        adapterSpinnerTipoPlato.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        adapterSpinnerCategoriaPlato.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        // Asignamos los adaptadores a los Spinners
        spinnerTipoPlato.setAdapter(adapterSpinnerTipoPlato);
        spinnerCategoriaPlato.setAdapter(adapterSpinnerCategoriaPlato);

        // Implementamos los métodos de los OnItemSelectedItem que implementan los Adapter que hemos asignado
        spinnerTipoPlato.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(EdicionPlatoActivity.this, "Seleccionado " + adapterView.getSelectedItem(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // ESTO SÓLO FUNCIONA SI DEJAMOS EL SPINNER TOTALMENTE VACÍO, PARA ESO DEBERÍAMOS BORRAR LOS DATOS CARGADOS
                Toast.makeText(EdicionPlatoActivity.this, "No has seleccionado nada", Toast.LENGTH_SHORT).show();
            }
        });

        spinnerCategoriaPlato.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(EdicionPlatoActivity.this, "Seleccionado " + adapterView.getSelectedItem(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // ESTO SÓLO FUNCIONA SI DEJAMOS EL SPINNER TOTALMENTE VACÍO, PARA ESO DEBERÍAMOS BORRAR LOS DATOS CARGADOS
                Toast.makeText(EdicionPlatoActivity.this, "No has seleccionado nada", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
