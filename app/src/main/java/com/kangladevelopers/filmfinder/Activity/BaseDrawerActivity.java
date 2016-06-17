package com.kangladevelopers.filmfinder.Activity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.kangladevelopers.filmfinder.R;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

/**
 * Created by BURNI on 2/9/2016.
 */
public abstract class BaseDrawerActivity extends BaseActivity {

    private static final int REQ_CODE_PICK_IMAGE = 1;
    String TAG = "BaseDrawerActivity";
    protected String child_activity;
    protected DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mDrawerToggle;


    // slide menu items
    String[] navMenuTitles;
    TypedArray navMenuIcons;
    TypedArray navMenuIconsHighlight;

    //ArrayList<NavDrawerItem> navDrawerItems;
    //NavDrawerListAdapter adapter;
    protected String currentPage="";

    String respName,respUrl;

    //nav drawer header
    CircularImageView profilePic;
    TextView name;
    Button logout;
    Toolbar toolbar;

    ImageView imgIcon,previousImageIcon;
    int previousPosition;
    int flag=1;

    boolean byContactItem=false;

    @Override
    protected void onStart() {
        super.onStart();
        byContactItem=false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void setDrawer(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

        setupDrawerListenner();
    }


    protected void setupDrawerListenner() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.drawer_open, R.string.drawer_close) {
            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                //invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
                //Log.w(TAG, "Its CLOSE");
                if(byContactItem==false){
                    //Log.w(TAG,"It is false");
                    invalidateOptionsMenu();
                }else{
                    //startChildActivity();
                }
            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }


}
