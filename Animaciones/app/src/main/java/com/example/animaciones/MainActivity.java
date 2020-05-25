package com.example.animaciones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

import static com.example.animaciones.R.xml.interpolator;

public class MainActivity extends AppCompatActivity {

    private static final String ID_CANAL = "BubbleChannel";
    private static final int ID_NOTIFICACION_BUBBLE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void translate(View view) {
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
        overridePendingTransition(R.xml.translate_1, R.xml.translate_2);

        EditText editText_Busqueda = findViewById(R.id.et_busqueda);

    }


    public void interpolar(View view) {
        @SuppressLint("ResourceType") Animation animation = AnimationUtils.loadAnimation(this, interpolator);
        TextView textView = findViewById(R.id.tv_interpolar);
        ImageView imageView = findViewById(R.id.iv_interpolar);
        textView.startAnimation(animation);
        imageView.startAnimation(animation);
    }

    public void lanzarBubble(View view) {
        // NotificationManager
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // Canal (>= Oreo)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(ID_CANAL, "Notificaciones Bubble",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("Canal para probar el uso de Bubbles");
            notificationManager.createNotificationChannel(notificationChannel);
        }
        // PendingIntent (lanzará la Activity del bubble al pulsar la notificación)
        Intent intent = new Intent(getApplicationContext(), BubbleActivity.class);
        PendingIntent intentPendiente = PendingIntent.getActivity(this,
                0, intent, 0);  // flags=0 es sin flags
        // Objeto Person
        // Bubble metadata
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            Notification.BubbleMetadata bubbleMetadata = new Notification.BubbleMetadata.Builder()
                    .setDesiredHeight(500)
                    .setIcon(Icon.createWithResource(this, R.drawable.ic_launcher_foreground))
                    .setIntent(intentPendiente)
                    .build();

            // Notificación
            Notification.Builder notificacion = new Notification.Builder(getApplicationContext(), ID_CANAL)
                    .setSmallIcon(android.R.drawable.btn_plus)
                    .setContentTitle("Notificación Bubble")
                    .setWhen(Calendar.getInstance().getTimeInMillis())
                    .setAutoCancel(true)
                    .setContentText("Pulsa para abrir el Bubble! :)")
                    .setContentIntent(intentPendiente)
                    .setBubbleMetadata(bubbleMetadata);
            // REGISTRAR NOTIFICACIÓN (si no, no se ejecuta)
            notificationManager.notify(ID_NOTIFICACION_BUBBLE, notificacion.build());
        } else {
            // Notificación
            NotificationCompat.Builder notificacion = new NotificationCompat.Builder(getApplicationContext(), ID_CANAL)
                    .setSmallIcon(android.R.drawable.btn_plus)
                    .setContentTitle("Notificación Bubble")
                    .setWhen(Calendar.getInstance().getTimeInMillis())
                    .setAutoCancel(true)
                    .setContentText("Pulsa para abrir el Bubble! :)")
                    .setContentIntent(intentPendiente);
            // REGISTRAR NOTIFICACIÓN (si no, no se ejecuta)
            notificationManager.notify(ID_NOTIFICACION_BUBBLE, notificacion.build());
        }

    }

    public void abrirActivityWebView(View view) {
        Intent intent = new Intent(this, ActivityWebView.class);
        startActivity(intent);
    }

    public void ocultarTexto(View view) {

        TextToSpeech.OnInitListener onInitListener = new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {

            }
        };
        TextToSpeech textToSpeech=new TextToSpeech(this,onInitListener);
        textToSpeech.setLanguage(Locale.ENGLISH);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textToSpeech.speak("Hola soy yo",TextToSpeech.QUEUE_FLUSH,null,"1");
        } else {
            Toast.makeText(this, "Versión de Android muy antigua", Toast.LENGTH_SHORT).show();
        }

        EditText editText_copiado = findViewById(R.id.et_copiado);
        switch (editText_copiado.getVisibility()) {
            case View.GONE:
                editText_copiado.setVisibility(View.VISIBLE);
                break;
            case View.INVISIBLE:
                editText_copiado.setVisibility(View.VISIBLE);
                break;
            case View.VISIBLE:
                editText_copiado.setVisibility(View.INVISIBLE);
                break;
        }
    }
}
