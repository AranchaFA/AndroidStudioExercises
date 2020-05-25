package com.example.animaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class ActivityWebView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        WebView webView=(WebView)findViewById(R.id.webView_prueba);
        webView.loadUrl("https://support.google.com/gboard/answer/6380730?co=GENIE.Platform%3DAndroid&hl=es-419");
        // https://translate.google.es/?hl=es&tab=wT#view=home&op=translate&sl=auto&tl=es&text=hello
    }
}
