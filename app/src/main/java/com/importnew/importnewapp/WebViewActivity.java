package com.importnew.importnewapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.daimajia.numberprogressbar.NumberProgressBar;
import com.importnew.importnewapp.models.Post;


import org.json.JSONException;
import org.json.JSONObject;


public class WebViewActivity extends AppCompatActivity {
    private final static String TAG = "WebViewActivity";
    private WebView mWebView;
    private ProgressBar mProgressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        mWebView = (WebView) findViewById(R.id.webview);
        mProgressbar = (ProgressBar) findViewById(R.id.progressbar);
        mWebView.setWebViewClient(new WebClient());
        mWebView.setWebChromeClient(new WebChrome());
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);
        String url = Uri.parse(Config.SERVER_URL).buildUpon().appendPath("item").appendPath(id + "").toString();
        JsonObjectRequest request = new JsonObjectRequest(url.toString(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    final Post post = parse(response);
                    String text = "<center><h3>" + post.getTitle() + "</h3></center>" + post.getContent();
                    mWebView.loadDataWithBaseURL(null, text, "text/html", "utf-8", null);
                } catch (JSONException e) {
                    Log.e("WebViewActivity", e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());

            }
        });
        ApplicationController.getInstance().addToRequestQueue(request, TAG);

    }

    private Post parse(JSONObject jsonObject) throws JSONException {
        JSONObject postJson = jsonObject.getJSONObject("post");
        int id = postJson.getInt("id");
        String title = postJson.getString("title");
        String description = postJson.getString("description");
        String url = postJson.getString("url");
        String cover = postJson.getString("cover");
        String createAt = postJson.getString("create_at");
        String author = postJson.getString("author");
        String content = postJson.getString("content");
        return new Post(id, url, title, cover, author, createAt, description, content);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (mWebView.canGoBack()) {
                        mWebView.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.destroy();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mWebView != null) {
            mWebView.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mWebView != null) {
            mWebView.onResume();
        }
    }

    /**
     * 可以传递数据的方式启动activity
     *
     * @param context
     * @param url
     */
    public static void actionStart(Context context, String url) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);

    }

    public static void actionStart(Context context, int id) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);

    }


    private class WebClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
        }
    }

    private class WebChrome extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            Log.i(TAG, newProgress + "");
            mProgressbar.setProgress(newProgress);
            if (newProgress == 100) {
                mProgressbar.setVisibility(NumberProgressBar.GONE);
            }
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }
    }
}
