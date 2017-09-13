package com.satyam.post_finder;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.appevents.internal.Constants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

class UrlCache {

    public static final long ONE_SECOND = 1000L;
    public static final long ONE_MINUTE = 60L * ONE_SECOND;
    public static final long ONE_HOUR   = 60L * ONE_MINUTE;
    public static final long ONE_DAY    = 24 * ONE_HOUR;

    private static class CacheEntry {
        public String url;
        public String fileName;
        public String mimeType;
        public String encoding;
        public long   maxAgeMillis;

        private CacheEntry(String url, String fileName,
                           String mimeType, String encoding, long maxAgeMillis) {

            this.url = url;
            this.fileName = fileName;
            this.mimeType = mimeType;
            this.encoding = encoding;
            this.maxAgeMillis = maxAgeMillis;
        }
    }


    protected Map<String, CacheEntry> cacheEntries = new HashMap<String, CacheEntry>();
    protected Activity activity = null;
    protected File rootDir = null;


    public UrlCache(Activity activity) {
        this.activity = activity;
        this.rootDir  = this.activity.getFilesDir();
    }

    public UrlCache(Activity activity, File rootDir) {
        this.activity = activity;
        this.rootDir  = rootDir;
    }



    public void register(String url, String cacheFileName,
                         String mimeType, String encoding,
                         long maxAgeMillis) {

        CacheEntry entry = new CacheEntry(url, cacheFileName, mimeType, encoding, maxAgeMillis);

        this.cacheEntries.put(url, entry);
    }



    public WebResourceResponse load(String url){
        CacheEntry cacheEntry = this.cacheEntries.get(url);

        if(cacheEntry == null) return null;

        File cachedFile = new File(this.rootDir.getPath() + File.separator + cacheEntry.fileName);

        if(cachedFile.exists()){
            long cacheEntryAge = System.currentTimeMillis() - cachedFile.lastModified();
            if(cacheEntryAge > cacheEntry.maxAgeMillis){
                cachedFile.delete();

                //cached file deleted, call load() again.
                Log.d(Constants.LOG_TIME_APP_EVENT_KEY, "Deleting from cache: " + url);
                return load(url);
            }

            //cached file exists and is not too old. Return file.
            Log.d(Constants.LOG_TIME_APP_EVENT_KEY, "Loading from cache: " + url);
            try {
                return new WebResourceResponse(
                        cacheEntry.mimeType, cacheEntry.encoding, new FileInputStream(cachedFile));
            } catch (FileNotFoundException e) {
                Log.d(Constants.LOG_TIME_APP_EVENT_KEY, "Error loading cached file: " + cachedFile.getPath() + " : "
                        + e.getMessage(), e);
            }

        } else {
            try{
                downloadAndStore(url, cacheEntry, cachedFile);

                //now the file exists in the cache, so we can just call this method again to read it.
                return load(url);
            } catch(Exception e){
                Log.d(Constants.LOG_TIME_APP_EVENT_KEY, "Error reading file over network: " + cachedFile.getPath(), e);
            }
        }

        return null;
    }



    private void downloadAndStore(String url, CacheEntry cacheEntry, File cachedFile)
            throws IOException {

        URL urlObj = new URL(url);
        URLConnection urlConnection = urlObj.openConnection();
        InputStream urlInput = urlConnection.getInputStream();

        FileOutputStream fileOutputStream =
                this.activity.openFileOutput(cacheEntry.fileName, Context.MODE_PRIVATE);

        int data = urlInput.read();
        while( data != -1 ){
            fileOutputStream.write(data);

            data = urlInput.read();
        }

        urlInput.close();
        fileOutputStream.close();
        Log.d(Constants.LOG_TIME_APP_EVENT_KEY, "Cache file: " + cacheEntry.fileName + " stored. ");
    }


}

class WebViewClientImpl extends WebViewClient {

    private Activity activity = null;
    private UrlCache urlCache = null;

    public WebViewClientImpl(Activity activity) {
        this.activity = activity;
        this.urlCache = new UrlCache(activity);

        this.urlCache.register("http:/www.facebook.com/", "facebook.com",
                "text/html", "UTF-8", 5 * UrlCache.ONE_MINUTE);
}

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if(url.indexOf("facebook.com") > -1 ) return false;

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        activity.startActivity(intent);
        return true;
    }


    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);

        if("http://www.facebook.com/".equals(url)){
            this.urlCache.load(url);
        }
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {

        if(url.startsWith("http://mydomain.com/article/")){
            String cacheFileName = url.substring(url.lastIndexOf("/"), url.length());
            this.urlCache.register(url, cacheFileName,
                    "text/html", "UTF-8", 60 * UrlCache.ONE_MINUTE);

        }

        return this.urlCache.load(url);
    }
}

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="MainActivity Check" ;
    private EditText editTextInput;
    WebView webView;
    String term;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"entry 1");
        setContentView(R.layout.activity_main);


        editTextInput = (EditText) findViewById(R.id.editText);
        webView = (WebView)
                findViewById(R.id.webview);

        Log.d(TAG," URL : "+term);
        Log.d(TAG,"entry 2");
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new AppJavaScriptProxy(this), "androidAppProxy");
        WebViewClientImpl webViewClient = new WebViewClientImpl(this);
        webView.setWebViewClient(webViewClient);
        //webView.loadUrl(term);
  }

    public void onSearchClick(View v)
    {
        term ="https://www.facebook.com/";
        term+=editTextInput.getText().toString();
        webView.loadUrl(term);
    }
    public class AppJavaScriptProxy {

        private WebView  webView  = null;
        private Activity activity = null;

        public AppJavaScriptProxy(Activity activity, WebView webview) {

            this.activity = activity;
            this.webView  = webview;
        }

        public AppJavaScriptProxy(MainActivity mainActivity) {
        }

        @JavascriptInterface
        public void showMessage(final String message) {

            Toast toast = Toast.makeText(this.activity.getApplicationContext(),
                    message,
                    Toast.LENGTH_SHORT);

            toast.show();
            final Activity theActivity = this.activity;
            final WebView theWebView = webView;

            this.activity.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if(!theWebView.getUrl().startsWith(term)){
                        return ;
                    }

                    Toast toast = Toast.makeText(
                            theActivity.getApplicationContext(),message,
                            Toast.LENGTH_SHORT);

                    toast.show();
                }
            });

        }

    }

}