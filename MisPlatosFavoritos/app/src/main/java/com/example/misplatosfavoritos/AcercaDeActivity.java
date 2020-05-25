package com.example.misplatosfavoritos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AcercaDeActivity extends AppCompatActivity {

    private Button button_aceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);

        button_aceptar=findViewById(R.id.b_acercaDeAceptar);

        button_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AcercaDeActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
