package com.satyam.post_finder;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.lang.reflect.Method;

/**
 * Created by satyam on 9/1/2017.
 */

public class SearchDemoActivity extends ActionBarActivity implements View.OnClickListener
{
    WebView mWebView;
    private RelativeLayout container;
    private Button nextButton, closeButton;
    private EditText findBox;
    private EditText editTextInput;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //getActionBar().setTitle(R.string.information_display);
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl("http://www.facebook.com/");
        nextButton = (Button) findViewById(R.id.nextButton);
        closeButton = (Button) findViewById(R.id.closeButton);
        findBox = (EditText) findViewById(R.id.findBox);
        findBox.setSingleLine(true);
        findBox.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && ((keyCode == KeyEvent.KEYCODE_ENTER)))
                {
                    mWebView.findAll(findBox.getText().toString());

                    try
                    {
                        // Can't use getMethod() as it's a private method
                        for (Method m : WebView.class.getDeclaredMethods())
                        {
                            if (m.getName().equals("setFindIsUp"))
                            {
                                m.setAccessible(true);
                                m.invoke(mWebView, true);
                                break;
                            }
                        }
                    }
                    catch (Exception ignored)
                    {
                    }
                    finally
                    {
                        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        // check if no view has focus:
                        View vv = getCurrentFocus();
                        if (vv != null)
                        {
                            inputManager.hideSoftInputFromWindow(v.getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                        }
                    }
                }
                return false;
            }
        });
        nextButton.setOnClickListener(this);
        closeButton.setOnClickListener(this);

    }
    public void onSearch(View v)
    {

        String term ="https://www.facebook.com/";
        term+=editTextInput.getText().toString();
        mWebView.loadUrl(term);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.searchview_in_menu, menu);
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu)
    {
        super.onPrepareOptionsMenu(menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_search:
                search();
                return true;
        }
        return true;
    }

    public void search()
    {
        container = (RelativeLayout) findViewById(R.id.layoutId);
        if (container.getVisibility() == RelativeLayout.GONE)
        {
            container.setVisibility(RelativeLayout.VISIBLE);
        }
        else if (container.getVisibility() == RelativeLayout.VISIBLE)
        {
            container.setVisibility(RelativeLayout.GONE);
        }
    }

    @Override
    public void onClick(View v)
    {
        if (v == nextButton)
        {
            mWebView.findNext(true);
        }
        else if (v == closeButton)
        {
            container.setVisibility(RelativeLayout.GONE);
        }
    }
}
