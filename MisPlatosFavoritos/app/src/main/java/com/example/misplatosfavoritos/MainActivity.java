package com.example.misplatosfavoritos;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button_verPlatos;
    private Button button_preferencias;
    private Button button_acercaDe;
    private Button button_salir;
    private Button button_anhadirPlato;

    private View.OnClickListener onClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        // Enlazar atributos con views del XML
        button_acercaDe=findViewById(R.id.button_acercaDe);
        button_anhadirPlato=findViewById(R.id.button_anhadirPlato);
        button_preferencias=findViewById(R.id.button_preferencias);
        button_salir=findViewById(R.id.button_salir);
        button_verPlatos=findViewById(R.id.button_verPlatos);


        // Inicializamos el onClickListener
        onClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view==button_anhadirPlato) {
                    Intent intent=new Intent(MainActivity.this,EdicionPlatoActivity.class);
                    startActivity(intent);
                }
                if (view==button_acercaDe) {
                    Intent intent=new Intent(MainActivity.this,AcercaDeActivity.class);
                    startActivity(intent);
                }
                // Faltan los demás botones
            }
        };


        // Asignamos el onClickListener a los botones
        button_verPlatos.setOnClickListener(onClickListener);
        button_salir.setOnClickListener(onClickListener);
        button_preferencias.setOnClickListener(onClickListener);
        button_anhadirPlato.setOnClickListener(onClickListener);
        button_acercaDe.setOnClickListener(onClickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
