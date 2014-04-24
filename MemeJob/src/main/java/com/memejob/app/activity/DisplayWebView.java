package com.memejob.app.activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.memejob.app.R;

/**
 * Created by graeme on 20/04/14.
 */
public class DisplayWebView extends Activity {
    private WebView localWebView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        Uri intentURL= getIntent().getData();

        if(intentURL==null)
            return;

        localWebView = (WebView) findViewById(R.id.webView);
        localWebView.getSettings().setJavaScriptEnabled(true);
        localWebView.setBackgroundColor(222);

        /**
         * Inserted this to prevent browser loading
         */
        localWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                 Toast.makeText(getApplicationContext(), "Done!", Toast.LENGTH_SHORT).show();
            }
        });

        //set to default browser, not mobile
        String newUA= "Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.4) Gecko/20100101 Firefox/4.0";
        localWebView.getSettings().setUserAgentString(newUA);

        localWebView.loadUrl(intentURL.toString());


    }


}
