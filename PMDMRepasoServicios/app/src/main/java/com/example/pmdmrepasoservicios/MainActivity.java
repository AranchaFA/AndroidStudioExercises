package com.example.pmdmrepasoservicios;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_FROM_ACTIVITY="email"; // CTES. PUBLIC O PROTECTED!!
    private ReceptorAvisosServicio receptorAvisosServicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Registrar BroadcastReceiver para que la activity escuche los eventos que le envía nuestro servicio
        receptorAvisosServicio = new ReceptorAvisosServicio();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ServicioPropio.ACTION_1);
        intentFilter.addAction(ServicioPropio.ACTION_2);
        registerReceiver(receptorAvisosServicio, intentFilter);
    }

    @Override // CUANDO SE PAUSA LA ACTIVITY
    protected void onPause() {
        super.onPause();
        //desregistro el BroadcastReceiver
        unregisterReceiver(receptorAvisosServicio);
    }


    //region RECEPTOR DE AVISOS
    public class ReceptorAvisosServicio extends BroadcastReceiver {
        private NotificationManager notificationManager;
        protected static final String ID_CANAL = "canalPropio";
        protected static final int ID_NOTIFICACION = 1;
        protected static final int ID_PENDING_INTENT = 0;
        public static final String EXTRA_DATOS_TO_NOTIFICACION = "extraDatosServicio";


        public ReceptorAvisosServicio() {
        }


        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case ServicioPropio.ACTION_1:
                    String datosRecibidosServicio = intent.getStringExtra(ServicioPropio.EXTRA_FROM_SERVICE);
                    Log.v("Se recibieron los datos",datosRecibidosServicio);
                    Toast.makeText(context, "Email enviado!! :)", Toast.LENGTH_SHORT).show();
                    lanzarNotificacion(datosRecibidosServicio);
                    return;
                case ServicioPropio.ACTION_2:
                    Toast.makeText(context, "No se pudo enviar el email :(", Toast.LENGTH_SHORT).show();
                    return;
            }
        }

        private void lanzarNotificacion(String datosRecibidos) {
            /** CAMBIAR :
             * PARÁMETRO DE ENTRADA (datosRecibidos)
             * NOMBRE Y DESCRIPCIÓN DEL CANAL
             * NOMBRE DE LA ACTIVITY.class QUE ABRIRÁ EN INTENT DEL PENDING INTENT
             * NOMBRE DE LA CTE. EXTRA_DATOS_DEL_SERVICIO, Y DATOS A PASAR
             * SMALL ICON, CONTENT TITLE Y CONTENT TEXT DE LA NOTIFICACIÓN
             */

            // NotificationManager
            notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            // Canal (>= Oreo)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel notificationChannel = new NotificationChannel(ID_CANAL, "Mis notificaciones",
                        NotificationManager.IMPORTANCE_DEFAULT);
                notificationChannel.setDescription("Canal para notificar nuestro servicio propio");
                notificationManager.createNotificationChannel(notificationChannel);
            }
            // PendingIntent (lanzará la Activity2 al pulsar la notificación)
            Intent intent = new Intent(getApplicationContext(), MostrarDatosActivity.class);
            intent.putExtra(EXTRA_DATOS_TO_NOTIFICACION, datosRecibidos);
            PendingIntent intentPendiente = PendingIntent.getActivity(MainActivity.this,
                    ID_PENDING_INTENT, intent, 0);  // flags=0 es sin flags
            // Notificación
            NotificationCompat.Builder notificacion =
                    new NotificationCompat.Builder(getApplicationContext(), ID_CANAL)
                            .setSmallIcon(android.R.drawable.btn_plus)
                            .setContentTitle("Email enviado")
                            .setWhen(Calendar.getInstance().getTimeInMillis())
                            .setAutoCancel(false)
                            .setContentText("El email se envió con éxito! :) Pulsa para verlo.")
                            .setContentIntent(intentPendiente);
            // REGISTRAR NOTIFICACIÓN (si no, no se ejecuta)
            notificationManager.notify(ID_NOTIFICACION, notificacion.build());
        }
    }

    //endregion
}
