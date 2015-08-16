package com.importnew.importnewapp;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class AboutActivity extends AppCompatActivity {

    private Toolbar toolbar;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        toolbar = (Toolbar)findViewById(R.id.toolbar_about);
        toolbar.setTitle("关 于");
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        getSupportActionBar().setTitle("关于我");



    }

}
