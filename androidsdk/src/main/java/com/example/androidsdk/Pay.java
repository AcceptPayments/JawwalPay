package com.example.androidsdk;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.net.URI;
import java.net.URL;

public class Pay  extends AppCompatActivity {
    public String PaymentKey;
    public int IframeID;


    public void StartPayment(String paymentKey, int iframeID) {

        WebView mywebview = (WebView) findViewById(R.id.webView);
        mywebview.getSettings().setJavaScriptEnabled(true);
        mywebview.setWebViewClient(new WebViewClient());


        mywebview.loadUrl("https://accept.paymobsolutions.com/api/acceptance/iframes/" + IframeID + "?payment_token=" + PaymentKey);

    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        Intent intent = getIntent();
        PaymentKey = intent.getStringExtra(IntentKeys.PAYMENT_KEY);
        IframeID = intent.getIntExtra(String.valueOf(IntentKeys.IFRAMEID),1);

          StartPayment(PaymentKey,IframeID);

    }

}
