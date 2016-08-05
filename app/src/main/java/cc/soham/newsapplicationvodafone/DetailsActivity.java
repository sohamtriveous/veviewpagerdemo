package cc.soham.newsapplicationvodafone;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import cc.soham.newsapplicationvodafone.objects.Article;

/**
 * Created by sohammondal on 04/08/16.
 */

public class DetailsActivity extends AppCompatActivity {
    WebView webView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        webView = (WebView) findViewById(R.id.activity_details_webview);
        progressBar = (ProgressBar) findViewById(R.id.activity_details_progress);

        int position = getIntent().getIntExtra(KEY_POSITION, DEFAULT_POSITION);
        Article newsObjects = CommonStuff.getArticles().get(position);

        loadWebViewForNewsObject(newsObjects);
    }

    private void loadWebViewForNewsObject(Article newsObjects) {
        getSupportActionBar().setTitle(newsObjects.getTitle());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }
        });
        webView.loadUrl(newsObjects.getUrl());
    }

    private static final String KEY_POSITION = "position";
    private static final int DEFAULT_POSITION = 0;

    public static void start(Context context, int position) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(KEY_POSITION, position);
        context.startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
