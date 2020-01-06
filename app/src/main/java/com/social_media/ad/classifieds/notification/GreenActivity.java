package com.social_media.ad.classifieds.notification;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.social_media.ad.classifieds.R;
import com.social_media.ad.classifieds.activity.HomeActivity;

public class GreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green);

        String openURL = getIntent().getStringExtra("openURL");

        final TextView textView = findViewById(R.id.debug_view);
        textView.setText("URL from additionalData: " + openURL);


        Button onBackButton = findViewById(R.id.back_button);
        onBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });

        WebView webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        if (openURL == null)
            webView.loadUrl("https://google.com");
        else
            webView.loadUrl(openURL);

    }
}
