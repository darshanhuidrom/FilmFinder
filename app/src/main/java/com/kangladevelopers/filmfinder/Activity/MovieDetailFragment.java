package com.kangladevelopers.filmfinder.Activity;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerView;
import com.kangladevelopers.filmfinder.R;
import com.kangladevelopers.filmfinder.pogo.Movie;
import com.kangladevelopers.filmfinder.utils.Config;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailFragment extends YouTubePlayerFragment implements YouTubePlayer.OnInitializedListener {


    private TextView tvDirector, tv_producer, tvSynopsis, tvRuntime, tvBudget, tvBoxoffice, tvStarring, tvType, tvCountry, tvYear;
    private TextView tvRatingImdb, tvRatingRottenTomato, tvRatingMetaCritic;
    private static final int RECOVERY_DIALOG_REQUEST = 1;
    private YouTubePlayerView playerView;
    private ImageView ivThumbnail;
    private FrameLayout flThumbnail;
    private Button btThumbnail;
    private boolean wasRestored;

    public MovieDetailFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        mapWithXml(view);
        //setData();
        setListener();
        return view;
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }

    private void mapWithXml(View view) {
        playerView = (YouTubePlayerView) view.findViewById(R.id.youtube_view);
        ivThumbnail = (ImageView) view.findViewById(R.id.iv_thumbnail);
        flThumbnail = (FrameLayout) view.findViewById(R.id.fl_thumbnail);
        btThumbnail = (Button) view.findViewById(R.id.bt_thumbnail);
        tvDirector = (TextView) view.findViewById(R.id.tv_director);
        tv_producer = (TextView) view.findViewById(R.id.tv_producer);
        tvSynopsis = (TextView) view.findViewById(R.id.tv_synopsis);
        tvRuntime = (TextView) view.findViewById(R.id.tv_runtime);
        tvBudget = (TextView) view.findViewById(R.id.tv_budget);
        tvYear = (TextView) view.findViewById(R.id.tv_year);
        tvStarring = (TextView) view.findViewById(R.id.tv_stare);
        tvType = (TextView) view.findViewById(R.id.tv_type);
        tvBoxoffice = (TextView) view.findViewById(R.id.tv_boxoffice);
        tvRatingImdb = (TextView) view.findViewById(R.id.tv_rating_imdb);
        tvRatingRottenTomato = (TextView) view.findViewById(R.id.tv_rotten_tomato);
        tvRatingMetaCritic = (TextView) view.findViewById(R.id.tv_rating_meta_critic);
        tvCountry = (TextView) view.findViewById(R.id.tv_country);

    }

    private void setData(Movie moveInfo) {
        tvDirector.setText("Directed by: " + moveInfo.getDirector());
        tv_producer.setText("Produced by: NA");
        tvRuntime.setText("Duration: " + moveInfo.getRuntime());
        tvBudget.setText("Budget: " + moveInfo.getBudget());
        tvBoxoffice.setText("Box Office: " + moveInfo.getBoxOffice());
        tvSynopsis.setText("Sypnosis: " + moveInfo.getSynopsis());
        tvRatingImdb.setText(moveInfo.getImdb() + "/10");
        tvRatingRottenTomato.setText(moveInfo.getRottenTometo() + "%");
        tvYear.setText("Year: " + moveInfo.getYear());
        tvType.setText("Generie: " + moveInfo.getType());
        tvStarring.setText("Starred by: " + moveInfo.getStarring());
        tvCountry.setText("Country: " + moveInfo.getCountry());

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
        imageLoader.displayImage("http://img.youtube.com/vi/" + Config.YOUTUBE_VIDEO_CODE + "/0.jpg", ivThumbnail, options);
    }

    private void setListener() {
        btThumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Toast.makeText(getApplicationContext(), "onclick", Toast.LENGTH_SHORT).show();
                playerView.setVisibility(View.VISIBLE);
                ivThumbnail.setVisibility(View.GONE);
                flThumbnail.setVisibility(View.GONE);
                playerView.initialize(Config.DEVELOPER_KEY, MovieDetailFragment.this);
            }
        });
    }
}
