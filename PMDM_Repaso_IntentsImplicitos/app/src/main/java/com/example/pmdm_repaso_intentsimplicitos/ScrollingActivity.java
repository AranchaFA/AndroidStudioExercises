package com.example.pmdm_repaso_intentsimplicitos;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ScrollingActivity extends AppCompatActivity {

    private static final int SOLICITUD_PERMISO_LLAMADA = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menuItem_llamadaDirecta:
                lanzarLlamadaDirecta();
                return true;
            case R.id.menuItem_llamadaIndirecta:
                lanzarLlamadaIndirecta();
                return true;
            case R.id.menuItem_maps:
                lanzarMaps();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void lanzarMaps() {
        // No requiere permiso
        Intent verMapaIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("geo:" + 44 + "," + 33 + "?q=" + 44 + "," + 33));
        if (verMapaIntent.resolveActivity(getPackageManager()) != null)
            startActivity(verMapaIntent);
        else
            Toast.makeText(this, "No hay ninguna aplicación para abrir el mapa", Toast.LENGTH_SHORT).show();
    }

    private void lanzarLlamadaIndirecta() {
        // No requiere permiso
        Intent marcarIntent = new Intent(Intent.ACTION_DIAL,
                Uri.parse("tel:" + "686795608"));
        if (marcarIntent.resolveActivity(getPackageManager()) != null)
            startActivity(marcarIntent);
        else
            Toast.makeText(this, "No hay ninguna aplicación para llamar", Toast.LENGTH_SHORT).show();
    }

    private void lanzarLlamadaDirecta() {
        // Sí requiere permiso
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) { // Permiso NO
                pedirPermiso("Se necesita permiso para realizar la llamada");
            } else { // Permiso SÍ
                llamar();
            }
        } else { // Versión anterior de Android (sin permiso)
            llamar();
        }
    }


    public void llamar() {
        Intent llamarIntent = new Intent(Intent.ACTION_CALL,
                Uri.parse("tel:" + "686795608"));
        if (llamarIntent.resolveActivity(getPackageManager()) != null)
            startActivity(llamarIntent);
        else
            Toast.makeText(this, "No hay ninguna aplicación para llamar", Toast.LENGTH_SHORT).show();
    }

    public void pedirPermiso(String justificacion) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(ScrollingActivity.this, Manifest.permission.CALL_PHONE)) {
            new AlertDialog.Builder(ScrollingActivity.this)
                    .setTitle("Solicitud de permiso")
                    .setMessage(justificacion)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(ScrollingActivity.this,
                                    new String[]{Manifest.permission.CALL_PHONE},
                                    SOLICITUD_PERMISO_LLAMADA);
                        }
                    })
                    .show();
        } else {
            ActivityCompat.requestPermissions(ScrollingActivity.this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    SOLICITUD_PERMISO_LLAMADA);
        }
    }

    @Override // Se ejecuta al "volver" de pedir los permisos
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case SOLICITUD_PERMISO_LLAMADA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.v("MIAPLI", "Permisos concedidos");
                    llamar();
                } else {
                    Toast.makeText(this, "Sin el permiso no se puede realizar la llamada", Toast.LENGTH_SHORT).show();
                    Log.i("MIAPLI", "Permisos denegados");
                }
                break;
        }
        return;
    }
}
