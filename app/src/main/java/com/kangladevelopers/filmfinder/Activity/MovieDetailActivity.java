package com.kangladevelopers.filmfinder.Activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.kangladevelopers.filmfinder.R;
import com.kangladevelopers.filmfinder.pogo.Movie;
import com.kangladevelopers.filmfinder.utils.Config;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MovieDetailActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private TextView tvDirector,tv_producer,tvSynopsis,tvRuntime,tvBudget,tvBoxoffice,tvStarring,tvType,tvCountry,tvYear;
    private TextView tvRatingImdb,tvRatingRottenTomato,tvRatingMetaCritic;
    private static final int RECOVERY_DIALOG_REQUEST = 1;
    private YouTubePlayerView playerView;
    private ImageView ivThumbnail;
    private FrameLayout flThumbnail;
    private Button btThumbnail;
    private boolean wasRestored;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_move_details);
        Movie movie = (Movie) getIntent().getSerializableExtra("object");
        mapWithXml();
        setData(movie);
        setListener();
    }

    private void setListener() {
        btThumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Toast.makeText(getApplicationContext(), "onclick", Toast.LENGTH_SHORT).show();
                playerView.setVisibility(View.VISIBLE);
                ivThumbnail.setVisibility(View.GONE);
                flThumbnail.setVisibility(View.GONE);
                playerView.initialize(Config.DEVELOPER_KEY, MovieDetailActivity.this);
            }
        });
    }

    private void setData(Movie moveInfo) {
        tvDirector.setText(" "+moveInfo.getDirector());
        tv_producer.setText(" NA");
        tvRuntime.setText(" "+moveInfo.getRuntime());
        tvBudget.setText(" "+moveInfo.getBudget());
        tvBoxoffice.setText(" "+moveInfo.getBoxOffice());
        tvSynopsis.setText(" "+moveInfo.getSynopsis());
        tvRatingImdb.setText(moveInfo.getImdb()+"/10");
        tvRatingRottenTomato.setText(moveInfo.getRottenTometo()+"%");
        tvYear.setText(" "+moveInfo.getYear());
        tvType.setText(" "+moveInfo.getType());
        tvStarring.setText(" "+moveInfo.getStarring());
        tvCountry.setText(" "+moveInfo.getCountry());

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
        imageLoader.displayImage("http://img.youtube.com/vi/"+ Config.YOUTUBE_VIDEO_CODE+"/0.jpg", ivThumbnail, options);

    }

    private void mapWithXml() {


        playerView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        ivThumbnail = (ImageView) findViewById(R.id.iv_thumbnail);
        flThumbnail = (FrameLayout) findViewById(R.id.fl_thumbnail);
        btThumbnail = (Button) findViewById(R.id.bt_thumbnail);

        tvDirector = (TextView) findViewById(R.id.tv_director);
        tv_producer = (TextView) findViewById(R.id.tv_producer);
        tvSynopsis = (TextView) findViewById(R.id.tv_synopsis);
        tvRuntime = (TextView) findViewById(R.id.tv_runtime);
        tvBudget = (TextView) findViewById(R.id.tv_budget);
        tvYear = (TextView) findViewById(R.id.tv_year);
        tvStarring = (TextView) findViewById(R.id.tv_stare);
        tvType = (TextView) findViewById(R.id.tv_type);
        tvBoxoffice = (TextView) findViewById(R.id.tv_boxoffice);
        tvRatingImdb = (TextView) findViewById(R.id.tv_rating_imdb);
        tvRatingRottenTomato = (TextView) findViewById(R.id.tv_rotten_tomato);
        tvRatingMetaCritic = (TextView) findViewById(R.id.tv_rating_meta_critic);
        tvCountry = (TextView) findViewById(R.id.tv_country);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (!wasRestored) {

            // loadVideo() will auto play video
            // Use cueVideo() method, if you don't want to play it automatically
            youTubePlayer.loadVideo(Config.YOUTUBE_VIDEO_CODE);

            // Hiding player controls
            //  player.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
    public void onBack(View view){
        finish();
    }
}
