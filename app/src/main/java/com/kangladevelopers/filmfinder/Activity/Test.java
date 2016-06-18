package com.kangladevelopers.filmfinder.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.kangladevelopers.filmfinder.Adapter.MovieInfoAdapter;
import com.kangladevelopers.filmfinder.Adapter.RvAdapterMoveInfo;
import com.kangladevelopers.filmfinder.DataModel.MovieInfo2;
import com.kangladevelopers.filmfinder.R;
import com.kangladevelopers.filmfinder.Utility.LogMessage;
import com.kangladevelopers.filmfinder.pogo.Actor;
import com.kangladevelopers.filmfinder.pogo.Director;
import com.kangladevelopers.filmfinder.retrofit.adapter.ActorRestAdapter;
import com.kangladevelopers.filmfinder.retrofit.adapter.DirectorRestAdapter;
import com.kangladevelopers.filmfinder.retrofit.adapter.MovieInfoRestAdapter;
import com.kangladevelopers.filmfinder.utils.StringUtility;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class Test extends AppCompatActivity implements ListView.OnItemClickListener {

    String TAG = "TEST";
    // SHA1: 3B:7C:A1:B1:31:18:1B:8E:8D:D7:37:8B:06:64:BF:B5:F5:7A:9C:9F
    LinearLayout castFilter, directorFilter;
    LinearLayout llCastCondition, llDirectorCondition, llTypeCondition, llTimeFilter;
    AutoCompleteTextView actvActor, actvDirector;
    NumberPicker npStart, npStop;
    ArrayAdapter<String> stringArrayAdapter;
    private Calendar calendar;
    private TextView tvStart, tvStop;
    private static int MAX_YEAR, MIN_YEAR;
    private LinearLayout searchLayoutButton;
    private ImageView ivActor, ivDirector;
    private ArrayList<View> actorViewList, directorViewList;
    private View CURRENT_ACTOR_VIEW, CURRENT_DIRECTOR_VIEW;
    private ArrayList<Integer> filtersIds = new ArrayList<>();
    private RvAdapterMoveInfo adapterMoveInfo;
    private RecyclerView rvMovieInfo;
    private ListView listView;
    private ArrayList<MovieInfo2> moveInfos;
    private MovieInfoAdapter adapter;
    private CheckBox cbAction, cbBiopic, cbComedy, cbDrama, cbFantasy, cbThriller;
    private MovieInfoRestAdapter movieInfoRestAdapter;
    private ActorRestAdapter actorRestAdapter;
    private DirectorRestAdapter directorRestAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_move_finder);
        mapWithXml();
        String[] actors = StringUtility.getActorList();
        String[] directors = StringUtility.getDirectorList();
        stringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, actors);
        ArrayAdapter<String> dirArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, directors);
        actvActor.setAdapter(stringArrayAdapter);
        actvDirector.setAdapter(dirArrayAdapter);
        initializeDefaultValue();
        setListeners();
        initializeData();
        movieInfoRestAdapter = new MovieInfoRestAdapter();
        actorRestAdapter = new ActorRestAdapter();
        directorRestAdapter = new DirectorRestAdapter();
    }

    private void initializeData() {
        llCastCondition.setVisibility(View.GONE);
        llDirectorCondition.setVisibility(View.GONE);
        llTypeCondition.setVisibility(View.GONE);
        llTimeFilter.setVisibility(View.GONE);
        if (filtersIds.isEmpty()) {
            filtersIds.add(R.id.ll_castCondition);
            filtersIds.add(R.id.ll_directorCondition);
            filtersIds.add(R.id.ll_typeCondition);
            filtersIds.add(R.id.ll_timeCondition);
        }
        if (actorViewList == null) {
            actorViewList = new ArrayList<>();
            actorViewList.add(llCastCondition.getChildAt(0));
        }
        if (directorViewList == null) {
            directorViewList = new ArrayList<>();
            directorViewList.add(llDirectorCondition.getChildAt(0));
        }

        /// These lines of codes are related to Actors


        final View view1 = actorViewList.get(0);
        AutoCompleteTextView completeTextView = (AutoCompleteTextView) view1.findViewById(R.id.actv_actor);
        completeTextView.setOnItemClickListener(new CustomAdapterListener(view1) {
            @Override
            public void onclick(View parentView, AdapterView<?> parent, View view, int position, long id) {
                CURRENT_ACTOR_VIEW = parentView;
                final ImageView imageView = (ImageView) view1.findViewById(R.id.iv_actor);
                Call<Actor> call = actorRestAdapter.getTestActorImageUrl();
                call.enqueue(new retrofit2.Callback<Actor>() {
                    @Override
                    public void onResponse(Call<Actor> call, Response<Actor> response) {
                        String imgageUrl = response.body().getImageUrl();
                     //  Picasso.with(Test.this).load("https://world-outreach.com/wp-content/uploads/2014/08/placeholder-profile-male.jpg").placeholder(ivActor.getDrawable()).resize(20, 20).into(imageView);
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
                        imageLoader.displayImage(imgageUrl, imageView, options);
                        if (CURRENT_ACTOR_VIEW.equals(actorViewList.get(actorViewList.size() - 1)))
                            addActorView();
                    }
                    @Override
                    public void onFailure(Call<Actor> call, Throwable t) {

                    }
                });
            }
        });

        /// These is lines of codes are related to directors


        final View view2 = directorViewList.get(0);
        AutoCompleteTextView completeTextView2 = (AutoCompleteTextView) view2.findViewById(R.id.actv_director);
        completeTextView2.setOnItemClickListener(new CustomAdapterListener(view2) {
            @Override
            public void onclick(View parentView, AdapterView<?> parent, View view, int position, long id) {
                CURRENT_DIRECTOR_VIEW = parentView;
                final ImageView imageView = (ImageView) view2.findViewById(R.id.iv_director);
                Call<Director> call = directorRestAdapter.getTestDirectorImageUrl();// for testing using actor made seperate for director afterwords
                call.enqueue(new retrofit2.Callback<Director>() {
                    @Override
                    public void onResponse(Call<Director> call, Response<Director> response) {
                        String imgageUrl = response.body().getImageUrl();
                        //  Picasso.with(Test.this).load("https://world-outreach.com/wp-content/uploads/2014/08/placeholder-profile-male.jpg").placeholder(ivActor.getDrawable()).resize(20, 20).into(imageView);
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
                        imageLoader.displayImage(imgageUrl, imageView, options);
                        if (CURRENT_DIRECTOR_VIEW.equals(directorViewList.get(directorViewList.size() - 1)))
                            addDirectorView();
                    }

                    @Override
                    public void onFailure(Call<Director> call, Throwable t) {


                    }
                });
            }
        });
    }

    private void setListeners() {
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
        searchLayoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  startActivity(new Intent(Test.this, MovieListActivity.class));
                hideRemaining(searchLayoutButton.getId());
                //   getData();
                searchMovies();

            }
        });
        listView.setOnItemClickListener(this);
    }

    private void showList() {
        if (adapter == null) {
            adapter = new MovieInfoAdapter(getApplicationContext(), moveInfos);
            listView.setAdapter(adapter);
        } else {
            adapter.setData(moveInfos);
        }
        setListViewHeightBasedOnChildren(listView);
    }

    private void mapWithXml() {

        castFilter = (LinearLayout) findViewById(R.id.cast_filter);
        directorFilter = (LinearLayout) findViewById(R.id.director_filter);
        actvActor = (AutoCompleteTextView) findViewById(R.id.actv_actor);
        actvDirector = (AutoCompleteTextView) findViewById(R.id.actv_director);
        ivDirector = (ImageView) findViewById(R.id.iv_director);
        npStart = (NumberPicker) findViewById(R.id.np_start);
        npStop = (NumberPicker) findViewById(R.id.np_stop);
        tvStart = (TextView) findViewById(R.id.tv_start);
        tvStop = (TextView) findViewById(R.id.tv_stop);
        searchLayoutButton = (LinearLayout) findViewById(R.id.search_layout_button);
        ivActor = (ImageView) findViewById(R.id.iv_actor);
        llCastCondition = (LinearLayout) findViewById(R.id.ll_castCondition);
        llDirectorCondition = (LinearLayout) findViewById(R.id.ll_directorCondition);
        llTypeCondition = (LinearLayout) findViewById(R.id.ll_typeCondition);
        llTimeFilter = (LinearLayout) findViewById(R.id.ll_timeCondition);
        rvMovieInfo = (RecyclerView) findViewById(R.id.rv_movie_info);
        listView = (ListView) findViewById(R.id.lv1);
        cbAction = (CheckBox) findViewById(R.id.checkBox1);
        cbBiopic = (CheckBox) findViewById(R.id.checkBox2);
        cbComedy = (CheckBox) findViewById(R.id.checkBox3);
        cbDrama = (CheckBox) findViewById(R.id.checkBox4);
        cbFantasy = (CheckBox) findViewById(R.id.checkBox5);
        cbThriller = (CheckBox) findViewById(R.id.checkBox6);
        listView.setFocusable(false);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MovieInfo2 moveInfo2 = (MovieInfo2) adapter.getItem(position);
        Intent intent = new Intent(Test.this, MovieDetailActivity.class);
        intent.putExtra("object", moveInfo2);
        startActivity(intent);
    }


    private abstract class CustomAdapterListener implements AdapterView.OnItemClickListener {
        private View parentView;

        public CustomAdapterListener(View parentView) {
            this.parentView = parentView;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            onclick(parentView, parent, view, position, id);
        }

        public abstract void onclick(View parentView, AdapterView<?> parent, View view, int position, long id);
    }

    private void initializeDefaultValue() {
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
    }


    public void filterConditionClick(View v) {
        Log.w(TAG, "Something is Click");
        switch (v.getId()) {
            case R.id.cast_filter:
                Log.w(TAG, "cast holder");
                hideRemaining(R.id.ll_castCondition);
                break;

            case R.id.director_filter:
                hideRemaining(R.id.ll_directorCondition);
                break;

            case R.id.type_filter:
                hideRemaining(R.id.ll_typeCondition);
                break;

            case R.id.time_filter:
                hideRemaining(R.id.ll_timeCondition);
                break;

            case R.id.extra_filter:
                break;
        }
    }


    private void addActorView() {
        final View view1 = LayoutInflater.from(this).inflate(R.layout.block_cast, null);
        AutoCompleteTextView completeTextView = (AutoCompleteTextView) view1.findViewById(R.id.actv_actor);
        completeTextView.setOnItemClickListener(new CustomAdapterListener(view1) {
            @Override
            public void onclick(View parentView, AdapterView<?> parent, View view, int position, long id) {
                CURRENT_ACTOR_VIEW = parentView;

                final ImageView imageView = (ImageView) view1.findViewById(R.id.iv_actor);
                Call<Actor> call = actorRestAdapter.getTestActorImageUrl();
                call.enqueue(new retrofit2.Callback<Actor>() {
                    @Override
                    public void onResponse(Call<Actor> call, Response<Actor> response) {
                        Call<Actor> mCall = call;
                        Response<Actor> mResponse = response;
                        String imgageUrl = response.body().getImageUrl();
                        //  Picasso.with(Test.this).load("https://world-outreach.com/wp-content/uploads/2014/08/placeholder-profile-male.jpg").placeholder(ivActor.getDrawable()).resize(20, 20).into(imageView);
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

                        imageLoader.displayImage(imgageUrl, imageView, options);

                        if (CURRENT_ACTOR_VIEW.equals(actorViewList.get(actorViewList.size() - 1)))
                            addActorView();
                    }

                    @Override
                    public void onFailure(Call<Actor> call, Throwable t) {
                        Call<Actor> mcall = call;
                        Throwable mt = t;
                    }
                });
            }
        });
        ArrayAdapter<String> Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, StringUtility.getActorList());
        completeTextView.setAdapter(Adapter);
        llCastCondition.addView(view1);
        actorViewList.add(view1);
    }

    private void addDirectorView() {
        final View view1 = LayoutInflater.from(this).inflate(R.layout.block_director, null);
        AutoCompleteTextView completeTextView = (AutoCompleteTextView) view1.findViewById(R.id.actv_director);
        completeTextView.setOnItemClickListener(new CustomAdapterListener(view1) {
            @Override
            public void onclick(View parentView, AdapterView<?> parent, View view, int position, long id) {
                CURRENT_DIRECTOR_VIEW = parentView;

                final ImageView imageView = (ImageView) view1.findViewById(R.id.iv_director);
                Call<Director> call = directorRestAdapter.getTestDirectorImageUrl();
                call.enqueue(new retrofit2.Callback<Director>() {
                    @Override
                    public void onResponse(Call<Director> call, Response<Director> response) {
                        Call<Director> mCall = call;
                        Response<Director> mResponse = response;
                        String imgageUrl = response.body().getImageUrl();
                        //  Picasso.with(Test.this).load("https://world-outreach.com/wp-content/uploads/2014/08/placeholder-profile-male.jpg").placeholder(ivActor.getDrawable()).resize(20, 20).into(imageView);
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

                        imageLoader.displayImage(imgageUrl, imageView, options);

                        if (CURRENT_DIRECTOR_VIEW.equals(directorViewList.get(directorViewList.size() - 1)))
                            addDirectorView();
                    }

                    @Override
                    public void onFailure(Call<Director> call, Throwable t) {
                        Call<Director> mcall = call;
                        Throwable mt = t;

                    }
                });

            }
        });
        ArrayAdapter<String> Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, StringUtility.getDirectorList());
        completeTextView.setAdapter(Adapter);
        llDirectorCondition.addView(view1);
        directorViewList.add(view1);
    }

    private void hideRemaining(int id) {

        for (int i = 0; i < filtersIds.size(); i++) {
            LinearLayout layout = (LinearLayout) findViewById(filtersIds.get(i));
            if (filtersIds.get(i) == id) {
                layout.setVisibility(View.VISIBLE);
            } else {
                layout.setVisibility(View.GONE);
            }
        }

    }


    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }


    private void searchMovies() {
        String actorList = "";
        String directorList = "";
        String type = "";

        for (int i = 0; i < actorViewList.size(); i++) {
            View view = actorViewList.get(i);
            AutoCompleteTextView completeTextView = (AutoCompleteTextView) view.findViewById(R.id.actv_actor);
            actorList = actorList + completeTextView.getText().toString();
            if (i < actorViewList.size() - 1)
                actorList = actorList + ",";
        }

        for (int i = 0; i < directorViewList.size(); i++) {
            View view = directorViewList.get(i);
            AutoCompleteTextView completeTextView = (AutoCompleteTextView) view.findViewById(R.id.actv_director);
            directorList = directorList + completeTextView.getText().toString();
            if (i < directorViewList.size() - 1)
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

        String query = actorList + "&" + directorList + "&" + type;
        Toast.makeText(getApplicationContext(), "query is\n" + query, Toast.LENGTH_LONG).show();
        LogMessage.printLog(TAG, query);
      /*  Call<List<MovieInfo2>> call = movieInfoRestAdapter.getMovies(actorList);
        call.enqueue(new retrofit2.Callback<List<MovieInfo2>>() {
            @Override
            public void onResponse(Call<List<MovieInfo2>> call, Response<List<MovieInfo2>> response) {

            }

            @Override
            public void onFailure(Call<List<MovieInfo2>> call, Throwable t) {

            }
        });*/
    }


}
