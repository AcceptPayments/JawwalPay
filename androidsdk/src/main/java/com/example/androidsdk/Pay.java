package com.example.androidsdk;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.net.URI;
import java.net.URL;

public class Pay extends AppCompatActivity {
    public String PaymentKey;
    public int IframeID;
    public String URL = "";
    public String Endpoint = "";
    String success = "";
    String Id = "";
    String amount_cents = "";
    String integration_id = "";
    String has_parent_transaction = "";
    String txn_response_code = "";
    String acq_response_code = "";

    final Intent result = new Intent();

    public void StartPayment(String paymentKey, int iframeID) {

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
                    Uri uri = Uri.parse(URL);

                    if (uri != null) {
                        success = uri.getQueryParameter("success");
                        Id = uri.getQueryParameter("id");
                        amount_cents = uri.getQueryParameter("amount_cents");
                        integration_id = uri.getQueryParameter("integration_id");
                        has_parent_transaction = uri.getQueryParameter("has_parent_transaction");
                        txn_response_code = uri.getQueryParameter("txn_response_code");
                        acq_response_code = uri.getQueryParameter("acq_response_code");


                        mywebview.destroy();

                    }


                    result.putExtra("success", success);
                    result.putExtra("ID", Id);
                    result.putExtra("amount_cents", amount_cents);
                    result.putExtra("integration_id", integration_id);
                    result.putExtra("has_parent_transaction", has_parent_transaction);
                    result.putExtra("txn_response_code", txn_response_code);
                    result.putExtra("acq_response_code", acq_response_code);

                    setResult(RESULT_OK, result);

                    finish();


                }

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

        mywebview.loadUrl("https://accept.paymobsolutions.com/api/acceptance/iframes/" + IframeID + "?payment_token=" + PaymentKey);

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
    public void onDestroy() {
        super.onDestroy();
        try {
            Runtime.getRuntime().gc();
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
