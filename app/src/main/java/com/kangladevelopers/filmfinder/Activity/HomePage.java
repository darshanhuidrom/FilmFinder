package com.kangladevelopers.filmfinder.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kangladevelopers.filmfinder.Adapter.RvMovieAdapter;
import com.kangladevelopers.filmfinder.R;
import com.kangladevelopers.filmfinder.Utility.LogMessage;
import com.kangladevelopers.filmfinder.pogo.Actor;
import com.kangladevelopers.filmfinder.pogo.Movie;
import com.kangladevelopers.filmfinder.retrofit.adapter.ActorRestAdapter;
import com.kangladevelopers.filmfinder.retrofit.adapter.MovieInfoRestAdapter;
import com.kangladevelopers.filmfinder.utils.StringUtility;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePage extends BaseDrawerActivity {

    private RelativeLayout rlCast;
    private LinearLayout llCastCondition, llDirectorCondition, llTypeCondition, llYearCondition;
    private LinearLayout llActorParentLayout, llDirectorParentLayout;
    private RelativeLayout rlDirector, rlType, rlYear;
    AutoCompleteTextView actvActor, actvDirector;
    ArrayList<View> viewActorList = new ArrayList<>();
    ArrayList<View> viewDirectorList = new ArrayList<>();
    TextView tvActorsCount, tvDirectorCount;
    NumberPicker npStart, npStop;
    private CheckBox cbAction, cbBiopic, cbComedy, cbDrama, cbFantasy, cbThriller;
    ActorRestAdapter actorRestAdapter, directorRestAdapter;
    MovieInfoRestAdapter movieInfoRestAdapter;
    RvMovieAdapter movieAdapter;
    private TextView tvStart;
    private TextView tvStop;
    private Calendar calendar;
    private int MIN_YEAR,MAX_YEAR;
    RecyclerView rvMovie;
    List<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home_page);
        setWidget();
        initializeData();
        setListeners();
        setDrawer();
        getDelegate().getSupportActionBar().setTitle("Move Finder");
    }

    private void setListeners() {
        actvActor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Call<Actor> call = actorRestAdapter.getTestActorImageUrl();
                call.enqueue(new Callback<Actor>() {
                    @Override
                    public void onResponse(Call<Actor> call, Response<Actor> response) {
                        addActorView(actvActor.getText().toString(), response.body().getImageUrl());
                        actvActor.setText("");
                    }

                    @Override
                    public void onFailure(Call<Actor> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Not Found", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        actvDirector.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Call<Actor> call = directorRestAdapter.getTestActorImageUrl();
                call.enqueue(new Callback<Actor>() {
                    @Override
                    public void onResponse(Call<Actor> call, Response<Actor> response) {
                        if (viewDirectorList.size() == 0) {
                            addDirectorView(actvDirector.getText().toString(), response.body().getImageUrl());
                            actvDirector.setText("");
                            actvDirector.setVisibility(View.GONE);
                        }

                    }

                    @Override
                    public void onFailure(Call<Actor> call, Throwable t) {

                        Toast.makeText(getApplicationContext(), "Not Found", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        npStart.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                tvStart.setText("From: " + newVal);
            }
        });
        npStop.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                tvStop.setText("To: " + newVal);
            }
        });
    }

    private void initializeData() {
        actorRestAdapter = new ActorRestAdapter();
        directorRestAdapter = new ActorRestAdapter();
        movieInfoRestAdapter = new MovieInfoRestAdapter();
        String[] actors = StringUtility.getActorList();
        String[] directors = StringUtility.getDirectorList();
        calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        if (MIN_YEAR == 0 || MAX_YEAR == 0) {
            MIN_YEAR = 1950;
            MAX_YEAR = year;
        }
        npStart.setMinValue(MIN_YEAR);
        npStart.setMaxValue(MAX_YEAR);
        npStart.setWrapSelectorWheel(true);

        npStop.setMinValue(MIN_YEAR);
        npStop.setMaxValue(MAX_YEAR);
        npStop.setWrapSelectorWheel(true);


        npStart.setValue(year);
        npStop.setValue(year);
        tvStart.setText("From: " + year);
        tvStop.setText("To: " + year);

        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, actors);
        actvActor.setAdapter(adapter);
        ArrayAdapter adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, directors);
        actvDirector.setAdapter(adapter);
        hideAndUnhideActor();
        hideAndUnhideCondition();
        hideAndUnhideDirector();
        hideAndUnhideYear();
    }

    private void setWidget() {
        rlCast = (RelativeLayout) findViewById(R.id.rl_cast);
        rlType = (RelativeLayout) findViewById(R.id.rl_type);
        rlYear = (RelativeLayout) findViewById(R.id.rl_year);
        llCastCondition = (LinearLayout) findViewById(R.id.ll_castCondition);
        llDirectorCondition = (LinearLayout) findViewById(R.id.ll_directorCondition);
        llTypeCondition = (LinearLayout) findViewById(R.id.ll_typeCondition);
        llYearCondition = (LinearLayout) findViewById(R.id.ll_timeCondition);
        llDirectorParentLayout = (LinearLayout) findViewById(R.id.ll_director_parent_layout);
        llActorParentLayout = (LinearLayout) findViewById(R.id.ll_actor_parent_layout);
        rlDirector = (RelativeLayout) findViewById(R.id.rl_director);
        actvActor = (AutoCompleteTextView) findViewById(R.id.actv_actor);
        actvDirector = (AutoCompleteTextView) findViewById(R.id.actv_director);
        tvActorsCount = (TextView) findViewById(R.id.tv_actors_count);
        tvDirectorCount = (TextView) findViewById(R.id.tv_director_count);
        rvMovie= (RecyclerView) findViewById(R.id.rv_moveList);
        cbAction = (CheckBox) findViewById(R.id.checkBox1);
        cbBiopic = (CheckBox) findViewById(R.id.checkBox2);
        cbComedy = (CheckBox) findViewById(R.id.checkBox3);
        cbDrama = (CheckBox) findViewById(R.id.checkBox4);
        cbFantasy = (CheckBox) findViewById(R.id.checkBox5);
        cbThriller = (CheckBox) findViewById(R.id.checkBox6);
        npStart = (NumberPicker) findViewById(R.id.np_start);
        npStop = (NumberPicker) findViewById(R.id.np_stop);
        tvStart = (TextView) findViewById(R.id.tv_start);
        tvStop = (TextView) findViewById(R.id.tv_stop);
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

    public void filterConditionClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.rl_cast:
                Toast.makeText(getApplicationContext(), "filterConditionClick", Toast.LENGTH_LONG).show();
                hideAndUnhideActor();
                break;
            case R.id.rl_director:
                hideAndUnhideDirector();
                break;
            case R.id.rl_type:
                hideAndUnhideCondition();
                break;
            case R.id.rl_year:
                hideAndUnhideYear();
                break;
            default:
                Toast.makeText(getApplicationContext(), "default", Toast.LENGTH_LONG).show();
        }
    }


    public void onDeleteClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.iv_delete:
                View viewTobeDeleted = null;
                Toast.makeText(getApplicationContext(), "default", Toast.LENGTH_LONG).show();
                for (int i = 0; i < viewActorList.size(); i++) {
                    ImageView deleteButton = (ImageView) viewActorList.get(i).findViewById(R.id.iv_delete);
                    if (deleteButton.equals(view)) {
                        viewTobeDeleted = viewActorList.get(i);
                        llActorParentLayout.removeView(viewActorList.get(i));
                    }
                }
                viewActorList.remove(viewTobeDeleted);
                tvActorsCount.setText("" + viewActorList.size());
                break;
            case R.id.iv_delete_director:
                View viewTobeDeleted2 = null;
                Toast.makeText(getApplicationContext(), "default", Toast.LENGTH_LONG).show();
                for (int i = 0; i < viewDirectorList.size(); i++) {
                    ImageView deleteButton = (ImageView) viewDirectorList.get(i).findViewById(R.id.iv_delete_director);
                    if (deleteButton.equals(view)) {
                        viewTobeDeleted2 = viewDirectorList.get(i);
                        llDirectorParentLayout.removeView(viewDirectorList.get(i));
                    }
                }
                viewDirectorList.remove(viewTobeDeleted2);
                tvDirectorCount.setText("" + viewDirectorList.size());
                actvDirector.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void hideAndUnhideActor() {
        if (llCastCondition.getVisibility() == View.GONE)
            llCastCondition.setVisibility(View.VISIBLE);
        else {
            llCastCondition.setVisibility(View.GONE);
        }
    }

    private void hideAndUnhideDirector() {
        if (llDirectorCondition.getVisibility() == View.GONE)
            llDirectorCondition.setVisibility(View.VISIBLE);
        else {
            llDirectorCondition.setVisibility(View.GONE);
        }
    }

    private void hideAndUnhideYear() {
        if (llYearCondition.getVisibility() == View.GONE)
            llYearCondition.setVisibility(View.VISIBLE);
        else {
            llYearCondition.setVisibility(View.GONE);
        }
    }

    private void hideAndUnhideCondition() {
        if (llTypeCondition.getVisibility() == View.GONE)
            llTypeCondition.setVisibility(View.VISIBLE);
        else {
            llTypeCondition.setVisibility(View.GONE);
        }
    }


    private void addActorView(String actorNamee, String imageUrl) {
        final View view = LayoutInflater.from(this).inflate(R.layout.block_actor, null);
        TextView actorName = (TextView) view.findViewById(R.id.tv_actor);
        ImageView actorImage = (ImageView) view.findViewById(R.id.iv_actor);
        viewActorList.add(view);
        llActorParentLayout.addView(view);
        tvActorsCount.setText("" + viewActorList.size());
        actorName.setText(actorNamee);
        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        imageLoader.displayImage(imageUrl, actorImage, options);
    }

    private void addDirectorView(String DirectorNamee, String imageUrl) {
        final View view = LayoutInflater.from(this).inflate(R.layout.block_directer, null);
        TextView actorName = (TextView) view.findViewById(R.id.tv_director);
        ImageView actorImage = (ImageView) view.findViewById(R.id.iv_director);
        viewDirectorList.add(view);
        llDirectorParentLayout.addView(view);
        tvDirectorCount.setText("" + viewDirectorList.size());
        actorName.setText(DirectorNamee);
        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        imageLoader.displayImage(imageUrl, actorImage, options);
    }


    private void searchMovies() {
        String actorList = null;
        String directorList = null;
        String type = null;

        for (int i = 0; i < viewActorList.size(); i++) {
            View view = viewActorList.get(i);
            TextView tvActor = (TextView) view.findViewById(R.id.tv_actor);
            if(actorList==null){
                actorList=tvActor.getText().toString();
            }
            else{
                actorList = actorList + tvActor.getText().toString();
            }

            if (i < viewActorList.size() - 1)
                actorList = actorList + ",";
        }

        for (int i = 0; i < viewDirectorList.size(); i++) {
            View view = viewDirectorList.get(i);
            TextView tvDirector = (TextView) view.findViewById(R.id.tv_director);
            if(directorList==null){
                directorList=tvDirector.getText().toString();
            }
            else{
                directorList = directorList + tvDirector.getText().toString();
            }

            if (i < viewDirectorList.size() - 1)
                directorList = directorList + ",";
        }

        if (cbAction.isChecked()) {
            type = cbAction.getText().toString();
        }
        if (cbBiopic.isChecked()) {
            if (!type.isEmpty())
                type = type + ",";
            type = type + cbBiopic.getText().toString();
        }
        if (cbComedy.isChecked()) {
            if (!type.isEmpty())
                type = type + ",";
            type = type + cbComedy.getText().toString();
        }
        if (cbDrama.isChecked()) {
            if (!type.isEmpty())
                type = type + ",";
            type = type + cbDrama.getText().toString();
        }
        if (cbFantasy.isChecked()) {
            if (!type.isEmpty())
                type = type + ",";
            type = type + cbFantasy.getText().toString();
        }
        if (cbThriller.isChecked()) {
            if (!type.isEmpty())
                type = type + ",";
            type = type + cbThriller.getText().toString();
        }

        int startYear= npStart.getValue();
        int endYear= npStop.getValue();
        String query = actorList + "&" + directorList + "&" + type+"&"+startYear+"&"+endYear;
        Toast.makeText(getApplicationContext(), "query is\n" + query, Toast.LENGTH_LONG).show();
        LogMessage.printLog(TAG, query);

        Call<List<Movie>> call =movieInfoRestAdapter.getMovies(actorList,directorList,null,startYear,endYear);
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                movieList = response.body();
                movieAdapter = new RvMovieAdapter(movieList,getApplicationContext());
                rvMovie.setLayoutManager(new LinearLayoutManager(HomePage.this));
                rvMovie.setAdapter(movieAdapter);

                movieAdapter.setRvAdapterClickLIstener(new RvMovieAdapter.RvAdapterClickListener() {
                    @Override
                    public void onItemClick(int i, View v) {
                        Movie movie = movieAdapter.getData().get(i);

                        Intent intent = new Intent(HomePage.this, MovieDetailActivity.class);
                        intent.putExtra("object", movie);
                        startActivity(intent);

                    }
                });


            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "Not Found", Toast.LENGTH_LONG).show();
            }
        });


    }
    public void onSubmit(View view){
        searchMovies();
    }
}
