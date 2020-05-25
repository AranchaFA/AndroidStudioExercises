package com.example.pmdm_banderaarcoiris;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    // ATRIBUTOS
    private TextView[] arrayTextViews;
    private int[] idTextViews={R.id.tv_1,R.id.tv_2,R.id.tv_3,R.id.tv_4,R.id.tv_5,R.id.tv_6,R.id.tv_7};
    private TextView textViewClickado;
    // Listener, como tenemos muchos elementos que usarán el mismo tipo de Listener
    // es más cómodo declararlo como atributo y sobreescribir el método para todos juntos
    private View.OnClickListener onClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Inicializamos el Listener (imprescindible, antes de hacer .setOnClickListener())
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Si ya habíamos clickado un elemento, intercambiamos sus datos y dejamos el
                // elemento clickado 'a cero' al final
                if (textViewClickado!=null){
                    Bundle bundle1= extraerDatosTextView(textViewClickado);
                    Bundle bundle2= extraerDatosTextView((TextView) view);
                    asignarDatosTextView(bundle2,textViewClickado);
                    asignarDatosTextView(bundle1, (TextView) view);
                    // Borramos ambos objetos de la lista para que quede vacía y controlar de nuevo
                    // si se clickan dos elementos
                    textViewClickado=null;
                } else {
                    // Si no habíamos clickado ninguno aún, guardamos el actual como clickado
                    textViewClickado = (TextView) view;
                }
            }
        };

        // Enlazamos los elementos con sus id del XML, los hacemos clickables y les asignamos el listener
        arrayTextViews=new TextView[7];
        for (int i=1;i<=arrayTextViews.length;i++) {
            arrayTextViews[i-1]=findViewById(idTextViews[i-1]);
            arrayTextViews[i-1].setClickable(true);
            arrayTextViews[i-1].setOnClickListener(onClickListener);
        }
    }

    public Bundle extraerDatosTextView(TextView textView){
        Bundle bundleDatos=new Bundle();

        bundleDatos.putString("texto",textView.getText().toString());
        bundleDatos.putInt("colorLetra",textView.getCurrentTextColor());
        // Para obtener el color de fondo
        ColorDrawable cd = (ColorDrawable) textView.getBackground();
        int colorFondo = cd.getColor();
        bundleDatos.putInt("colorFondo",colorFondo);

        return bundleDatos;
    }

    public void asignarDatosTextView(Bundle bundleDatos, TextView textView){
        textView.setText(bundleDatos.getString("texto"));
        textView.setTextColor(bundleDatos.getInt("colorLetra"));
        textView.setBackgroundColor(bundleDatos.getInt("colorFondo"));
    }


    // Cuando se vaya a destruir, debemos guardar la configuración de todas las franjas
    // y de la franja clickada
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // No funciona guardar todo el array tal cual, lo restaura con los valores iniciales
        // outState.putSerializable("arrayTV", (Serializable) arrayTextViews);
        for (int i=0;i<arrayTextViews.length;i++) {
            TextView textView=arrayTextViews[i];
            String key=String.valueOf(i+1);
            outState.putBundle(key, extraerDatosTextView(textView));
        }
        outState.putSerializable("clickado", (Serializable) textViewClickado);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // No funciona guardar todo el array tal cual, lo restaura con los valores iniciales
        // arrayTextViews= (TextView[]) savedInstanceState.getSerializable("arrayTV");
        for (int i=0;i<arrayTextViews.length;i++) {
            TextView textView=arrayTextViews[i];
            String key=String.valueOf(i+1);
            asignarDatosTextView(savedInstanceState.getBundle(key),textView);
        }
        textViewClickado=(TextView)savedInstanceState.getSerializable("clickado");
    }
}
