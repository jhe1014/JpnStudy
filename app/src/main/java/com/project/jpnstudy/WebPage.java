package com.project.jpnstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

public class WebPage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webpage);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("youtube");
        set.setJavaScriptEnabled(true);//javascript를 사용할 수 있도록 설정
        set.setBuiltInZoomControls(true);//안드로이드에서 제공하는 줌 아이콘을 사용할 수 있도록 설정


        Intent it = getIntent();

        WebView webview = (WebView)findViewById(R.id.y_url);
        webview.setWebViewClient(new WebViewClient());
        WebSettings set = webview.getSettings();
    }
}
