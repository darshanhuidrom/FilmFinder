package com.kangladevelopers.filmfinder.retrofit.adapter;

import com.kangladevelopers.filmfinder.DataModel.MovieInfo2;
import com.kangladevelopers.filmfinder.Utility.Constants;
import com.kangladevelopers.filmfinder.pogo.Movie;
import com.kangladevelopers.filmfinder.retrofit.interfaces.MovieInfoAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieInfoRestAdapter {
    protected final String TAG = getClass().getSimpleName();
    protected Retrofit retrofit;
    private MovieInfoAPI movieInfoAPI;

    public MovieInfoRestAdapter() {
        retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL2).addConverterFactory(GsonConverterFactory.create()).build();
        movieInfoAPI = retrofit.create(MovieInfoAPI.class);
    }

    public Call<List<Movie>> getMovies(String actors) {
        return movieInfoAPI.getMovies(actors);
    }
    public Call<List<Movie>> getMovies(String actor,String director,String type,int startYear,int endYear) {
        return movieInfoAPI.getMovies(actor,director,type,startYear,endYear);
    }

}
