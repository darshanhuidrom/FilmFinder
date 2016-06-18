package com.kangladevelopers.filmfinder.retrofit.interfaces;

import com.kangladevelopers.filmfinder.DataModel.MovieInfo2;
import com.kangladevelopers.filmfinder.pogo.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
// https://futurestud.io/blog/retrofit-multiple-query-parameters-of-same-name


/**
 * Created by HUIDROM on 6/12/2016.
 */
public interface MovieInfoAPI {
    @GET("movieV2")
    Call<List<Movie>> getMovies(
            @Query("actor") String actors
    );

    @GET("movieV2")
    Call<List<Movie>> getMovies(
            @Query("actors") String actors,
            @Query("directors") String directors
    );
    @GET("movieV2")
    Call<List<Movie>> getMovies(
            @Query("actor") String actors,
            @Query("director") String directors,
            @Query(("type")) String types,
            @Query("start_year") int startYear,
            @Query("end_year") int endYear
    );


}
