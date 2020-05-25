package com.example.pmdmmovercositasenpantalla;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.ContentProvider;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView etiqueta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                TextView textView=new TextView(this);
                textView.setText(":)");
                textView.setBackgroundColor(Color.MAGENTA);
                textView.setHeight(2);
                textView.setWidth(2);

                // View textView = LayoutInflater.from(this).inflate(R.layout.etiqueta, (ViewGroup) findViewById(R.id.layout_etiqueta));

                textView.setX(event.getX());
                textView.setY(event.getY());
                setContentView(textView);


                Log.v("MIAPLI","++++++++++++  ACTION_DOWN  ++++++++++++");
                return true;
            case MotionEvent.ACTION_UP:
                Log.v("MIAPLI","++++++++++++  ACTION_UP  ++++++++++++");
                return true;
            case MotionEvent.ACTION_MOVE:
                Log.v("MIAPLI","++++++++++++  ACTION_MOVE  ++++++++++++");
                return true;
        }

        return super.onTouchEvent(event);
    }
}
