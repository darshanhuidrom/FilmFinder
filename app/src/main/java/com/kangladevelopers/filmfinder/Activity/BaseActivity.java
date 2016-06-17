package com.kangladevelopers.filmfinder.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by BURNI on 2/9/2016.
 */
public abstract class BaseActivity extends AppCompatActivity{

    private String TAG = "BaseActivity";
    public Toolbar toolbar;
    Activity activity;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
