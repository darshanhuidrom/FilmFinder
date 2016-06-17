package com.kangladevelopers.filmfinder.Activity;

import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.kangladevelopers.filmfinder.R;

public class HomePage extends BaseDrawerActivity {

    private RelativeLayout rlCast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home_page);
        setWidget();
        setDrawer();
        getDelegate().getSupportActionBar().setTitle("Move Finder");

    }

    private void setWidget() {
        rlCast = (RelativeLayout) findViewById(R.id.rl_cast);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        switch (item.getItemId()) {
            // THIS IS YOUR DRAWER/HAMBURGER BUTTON
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);  // OPEN DRAWER
                Log.d("NICK", "OPEN NAVI");
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
