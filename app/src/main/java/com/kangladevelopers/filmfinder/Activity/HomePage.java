package com.kangladevelopers.filmfinder.Activity;

import android.graphics.Bitmap;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kangladevelopers.filmfinder.R;
import com.kangladevelopers.filmfinder.pogo.Actor;
import com.kangladevelopers.filmfinder.retrofit.adapter.ActorRestAdapter;
import com.kangladevelopers.filmfinder.utils.StringUtility;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
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
    ActorRestAdapter actorRestAdapter, directorRestAdapter;

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
    }

    private void initializeData() {
        actorRestAdapter = new ActorRestAdapter();
        directorRestAdapter = new ActorRestAdapter();
        String[] actors = StringUtility.getActorList();
        String[] directors = StringUtility.getDirectorList();
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
                // hideAndUnhide();

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
        tvDirectorCount.setText("" + viewActorList.size());
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
}
