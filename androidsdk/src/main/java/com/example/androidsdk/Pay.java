package com.example.androidsdk;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class Pay extends AppCompatActivity {
    public String PaymentKey;
    public int IframeID;
    public String URL = "";
    public String Endpoint = "";
    String  Data="";

    final Intent result = new Intent();

    public void StartPayment(String paymentKey, int iframeID) {

       final LinearLayout linearLayout = findViewById(R.id.layout);
       final WebView mywebview = (WebView) findViewById(R.id.webView);

        mywebview.setWebViewClient(new WebViewClient() {

            @SuppressWarnings("deprecation")
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);

            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

            }


            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (view.getOriginalUrl().contains(Endpoint)) {

                    URL = view.getOriginalUrl();
                  //  Uri uri = Uri.parse(URL);
                    Data = getQueryParams(URL);
                    result.putExtra("data",Data);
                    setResult(RESULT_OK, result);
                    linearLayout.removeAllViews();
                    mywebview.destroy();

                    finish();

                }
//                else if (view.getOriginalUrl()=="https://migs.mastercard.com.au/ssl") {
//
//                    notifyErrorTransaction("transaction couldn't be completed");
//                    mywebview.destroy();
//
//                }

            }


        });

        mywebview.getSettings().setJavaScriptEnabled(true);
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

        mywebview.loadUrl("https://checkout.jawwalpay.ps/api/acceptance/iframes/" + IframeID + "?payment_token=" + PaymentKey);



    }

    public String getQueryParams(String url){
        try {
            JSONObject JSON = new JSONObject();
            String[] urlParts = url.split("\\?");
            if (urlParts.length > 1) {
                String query = urlParts[1];
                for (String param : query.split("&")) {
                    String[] pair = param.split("=");
                    String key = URLDecoder.decode(pair[0], "UTF-8");
                    String value = "";
                    if (pair.length > 1) {
                        value = URLDecoder.decode(pair[1], "UTF-8");
                        JSON.put(key, value);
                    }
                }
            }
            return JSON.toString();
        } catch (Exception ex) {
            String message = String.valueOf(Log.d("parsing error", "getQueryParams: "+ex.getMessage()));

            return message;


        }


    }
    public void notifyErrorTransaction(String reason) {
        result.putExtra("notifyError", reason);
        setResult(RESULT_OK, result);


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);


        Intent intent = getIntent();
        PaymentKey = intent.getStringExtra(IntentKeys.PAYMENT_KEY);
        IframeID = intent.getIntExtra(String.valueOf(IntentKeys.IFRAMEID), 1);
        Endpoint = intent.getStringExtra(IntentKeys.ENDPOINT_URL);

        StartPayment(PaymentKey, IframeID);

    }
    @Override
    public void onBackPressed() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Boolean userCancelled;
        if (id == R.id.close) {

            userCancelled = true;

            result.putExtra("userCancelled", userCancelled);
            setResult(RESULT_OK,result);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
}
