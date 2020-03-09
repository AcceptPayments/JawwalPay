package com.example.androidsdk;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebSettings;
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
        mywebview.getSettings().setLoadWithOverviewMode(true);
        mywebview.getSettings().setUseWideViewPort(true);
        mywebview.getSettings().setSupportZoom(true);
        mywebview.getSettings().setBuiltInZoomControls(false);
        mywebview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mywebview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        mywebview.getSettings().setDomStorageEnabled(true);
        mywebview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mywebview.setScrollbarFadingEnabled(true);
        if (Build.VERSION.SDK_INT < 18) {
            mywebview.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        }

        mywebview.loadUrl("https://accept.paymobsolutions.com/api/acceptance/iframes/" + IframeID + "?payment_token=" + PaymentKey);

    }
    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
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
