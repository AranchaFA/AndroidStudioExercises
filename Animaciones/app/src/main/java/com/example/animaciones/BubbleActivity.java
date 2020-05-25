package com.example.animaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class BubbleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubble);
    }

    public void mostrarToast(View view) {
        Toast.makeText(this, "Holaaaa :)", Toast.LENGTH_SHORT).show();
    }
}
