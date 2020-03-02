package com.example.androidsdk;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class webviewClient extends WebViewClient
{
    @Override
    public boolean shouldOverrideUrlLoading(final WebView view, final String url)
    {
        new Thread(new Runnable()
        {

            public void run()
            {
                view.loadUrl(url);
            }



        }).start();



        return true;
    }
}