package com.kangladevelopers.filmfinder.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kangladevelopers.filmfinder.R;
import com.kangladevelopers.filmfinder.pogo.Movie;

/**
 * Created by HUIDROM on 6/19/2016.
 */
public class MovieDetailActivity2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_movie_detail);
        Movie movie = (Movie) getIntent().getSerializableExtra("object");

    }
}
