package com.example.pmdmrepasoservicios;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class ServicioPropio extends IntentService {

    public static final String ACTION_1 = "com.example.pmdmrepasoservicios.action.1";
    public static final String ACTION_2 = "com.example.pmdmrepasoservicios.action.2";

    public static final String EXTRA_FROM_SERVICE = "com.example.pmdmrepasoservicios.extra.FROM_SERVICE";

    public ServicioPropio() {
        super("ServicioPropio");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionFoo(param1, param2);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }
        }
    }

}
