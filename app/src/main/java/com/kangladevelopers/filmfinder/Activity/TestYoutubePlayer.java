package com.kangladevelopers.filmfinder.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.kangladevelopers.filmfinder.R;
import com.kangladevelopers.filmfinder.utils.Config;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class TestYoutubePlayer extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {


    private static final int RECOVERY_DIALOG_REQUEST = 1;
    private YouTubePlayerView playerView;
    private ImageView ivThumbnail;
    private FrameLayout flThumbnail;
    private Button btThumbnail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_youtube_player);
        playerView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        ivThumbnail = (ImageView) findViewById(R.id.iv_thumbnail);
        flThumbnail = (FrameLayout) findViewById(R.id.fl_thumbnail);
        btThumbnail = (Button) findViewById(R.id.bt_thumbnail);
        Picasso.with(this).load("http://img.youtube.com/vi/"+Config.YOUTUBE_VIDEO_CODE+"/0.jpg").into(ivThumbnail, new Callback() {
            @Override
            public void onSuccess() {
                Picasso.with(TestYoutubePlayer.this).load("http://img.youtube.com/vi/"+Config.YOUTUBE_VIDEO_CODE+"/0.jpg").placeholder(ivThumbnail.getDrawable()).fit().centerCrop().into(ivThumbnail);
            }

            @Override
            public void onError() {

            }
        });
        btThumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(getApplicationContext(), "onclick", Toast.LENGTH_SHORT).show();
                playerView.setVisibility(View.VISIBLE);
                ivThumbnail.setVisibility(View.GONE);
                flThumbnail.setVisibility(View.GONE);
                playerView.initialize(Config.DEVELOPER_KEY, TestYoutubePlayer.this);
            }
        });



    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {

        if (!wasRestored) {

            // loadVideo() will auto play video
            // Use cueVideo() method, if you don't want to play it automatically
            player.loadVideo(Config.YOUTUBE_VIDEO_CODE);

            // Hiding player controls
          //  player.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Config.DEVELOPER_KEY, this);
        }
    }

    private YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.youtube_view);
    }
}
