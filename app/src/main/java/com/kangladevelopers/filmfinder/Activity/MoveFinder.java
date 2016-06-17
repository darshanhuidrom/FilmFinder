package com.kangladevelopers.filmfinder.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kangladevelopers.filmfinder.R;

/**
 * Created by BURNI on 2/9/2016.
 */
public class MoveFinder extends AppCompatActivity implements View.OnClickListener{

    LinearLayout castHolder,directorHolder,typeHolder,timeHolder,extraHolder;
    LinearLayout castFilter,directorFilter,typeFilter,timeFilter,extraFilter;
    TextView castFilterText,directorFilterText,typeFilterText,timeFilterText,extraFilterText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_move_finder);

        setWidget();
    }

    private void setWidget() {
        castHolder = (LinearLayout) findViewById(R.id.cast_holder);
        directorHolder = (LinearLayout) findViewById(R.id.director_holder);
        typeHolder = (LinearLayout) findViewById(R.id.type_holder);
        timeHolder = (LinearLayout) findViewById(R.id.time_holder);
        extraHolder = (LinearLayout) findViewById(R.id.extra_holder);

        castFilter = (LinearLayout) findViewById(R.id.cast_filter);
        directorFilter = (LinearLayout) findViewById(R.id.director_filter);
        typeFilter = (LinearLayout) findViewById(R.id.type_filter);
        timeFilter = (LinearLayout) findViewById(R.id.time_filter);
        extraFilter = (LinearLayout) findViewById(R.id.extra_filter);

        castFilter.setOnClickListener(this);
        directorFilter.setOnClickListener(this);
        typeFilter.setOnClickListener(this);
        timeFilter.setOnClickListener(this);
        extraFilter.setOnClickListener(this);

        castFilterText = (TextView) findViewById(R.id.cast_filter_text);
        directorFilterText = (TextView) findViewById(R.id.director_filter_text);
        typeFilterText = (TextView) findViewById(R.id.type_filter_text);
        timeFilterText = (TextView) findViewById(R.id.time_filter_text);
        extraFilterText = (TextView) findViewById(R.id.extra_filter_text);



    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.cast_filter:
                break;

            case R.id.director_filter:
                break;

            case R.id.type_filter:
                break;

            case R.id.time_filter:
                break;

            case R.id.extra_filter:
                break;
        }

    }
}
