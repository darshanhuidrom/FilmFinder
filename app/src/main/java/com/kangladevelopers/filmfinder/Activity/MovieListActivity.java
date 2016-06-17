package com.kangladevelopers.filmfinder.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.kangladevelopers.filmfinder.Adapter.RvAdapterMoveInfo;
import com.kangladevelopers.filmfinder.DataModel.MoveInfo;
import com.kangladevelopers.filmfinder.R;

import java.util.ArrayList;
import java.util.List;

public class MovieListActivity extends AppCompatActivity {

    private RvAdapterMoveInfo adapterMoveInfo;
    private RecyclerView rvMovieInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched_list);
        rvMovieInfo = (RecyclerView) findViewById(R.id.rv_movie_info);
        adapterMoveInfo = new RvAdapterMoveInfo(this, getDummyData());
        rvMovieInfo.setLayoutManager(new LinearLayoutManager(this));
        rvMovieInfo.setAdapter(adapterMoveInfo);
        adapterMoveInfo.setRvAdapterClickLIstener(new RvAdapterMoveInfo.RvAdapterClickListener() {
            @Override
            public void onItemClick(int i, View v) {

                MoveInfo moveInfo = adapterMoveInfo.getData().get(i);

                Intent intent = new Intent(MovieListActivity.this,MovieDetailActivity.class);
                intent.putExtra("object", moveInfo);
                startActivity(intent);

            }
        });

    }

    private List<MoveInfo> getDummyData() {
        List<MoveInfo> moveInfos = new ArrayList<MoveInfo>();
        ArrayList<String> actors = new ArrayList<>();
        actors.add("Amitab Bachan");
        actors.add("Dharmender");
        actors.add("Hemamalini");
        actors.add("Jaya Bhachan");
        moveInfos.add(new MoveInfo().setName("Sholay").setCast(actors).setBoxOffice("Super Hit").setDirector("Salim Khan")
                .setYear("1972").setImageUrl("https://lh6.ggpht.com/8oegfFzTenHGQUGxJAU_b62rNYIu0t8hVl-bJ1YmZzNgOWAcP5KtYyJKQ2rjhnc9_lM=w300"));
        moveInfos.add(new MoveInfo().setName("Sholay").setCast(actors).setBoxOffice("Super Hit").setDirector("Salim Khan")
                .setYear("1972").setImageUrl("https://lh6.ggpht.com/8oegfFzTenHGQUGxJAU_b62rNYIu0t8hVl-bJ1YmZzNgOWAcP5KtYyJKQ2rjhnc9_lM=w300"));
        moveInfos.add(new MoveInfo().setName("Sholay").setCast(actors).setBoxOffice("Super Hit").setDirector("Salim Khan")
                .setYear("1972").setImageUrl("https://lh6.ggpht.com/8oegfFzTenHGQUGxJAU_b62rNYIu0t8hVl-bJ1YmZzNgOWAcP5KtYyJKQ2rjhnc9_lM=w300"));
        moveInfos.add(new MoveInfo().setName("Sholay").setCast(actors).setBoxOffice("Super Hit").setDirector("Salim Khan")
                .setYear("1972").setImageUrl("https://lh6.ggpht.com/8oegfFzTenHGQUGxJAU_b62rNYIu0t8hVl-bJ1YmZzNgOWAcP5KtYyJKQ2rjhnc9_lM=w300"));
        moveInfos.add(new MoveInfo().setName("Sholay").setCast(actors).setBoxOffice("Super Hit").setDirector("Salim Khan")
                .setYear("1972").setImageUrl("https://lh6.ggpht.com/8oegfFzTenHGQUGxJAU_b62rNYIu0t8hVl-bJ1YmZzNgOWAcP5KtYyJKQ2rjhnc9_lM=w300"));
        moveInfos.add(new MoveInfo().setName("Sholay").setCast(actors).setBoxOffice("Super Hit").setDirector("Salim Khan")
                .setYear("1972").setImageUrl("https://lh6.ggpht.com/8oegfFzTenHGQUGxJAU_b62rNYIu0t8hVl-bJ1YmZzNgOWAcP5KtYyJKQ2rjhnc9_lM=w300"));

        moveInfos.add(new MoveInfo().setName("Sholay").setCast(actors).setBoxOffice("Super Hit").setDirector("Salim Khan")
                .setYear("1972").setImageUrl("https://lh6.ggpht.com/8oegfFzTenHGQUGxJAU_b62rNYIu0t8hVl-bJ1YmZzNgOWAcP5KtYyJKQ2rjhnc9_lM=w300"));
        moveInfos.add(new MoveInfo().setName("Sholay").setCast(actors).setBoxOffice("Super Hit").setDirector("Salim Khan")
                .setYear("1972").setImageUrl("https://lh6.ggpht.com/8oegfFzTenHGQUGxJAU_b62rNYIu0t8hVl-bJ1YmZzNgOWAcP5KtYyJKQ2rjhnc9_lM=w300"));
        moveInfos.add(new MoveInfo().setName("Sholay").setCast(actors).setBoxOffice("Super Hit").setDirector("Salim Khan")
                .setYear("1972").setImageUrl("https://lh6.ggpht.com/8oegfFzTenHGQUGxJAU_b62rNYIu0t8hVl-bJ1YmZzNgOWAcP5KtYyJKQ2rjhnc9_lM=w300"));
        moveInfos.add(new MoveInfo().setName("Sholay").setCast(actors).setBoxOffice("Super Hit").setDirector("Salim Khan")
                .setYear("1972").setImageUrl("https://lh6.ggpht.com/8oegfFzTenHGQUGxJAU_b62rNYIu0t8hVl-bJ1YmZzNgOWAcP5KtYyJKQ2rjhnc9_lM=w300"));
        moveInfos.add(new MoveInfo().setName("Sholay").setCast(actors).setBoxOffice("Super Hit").setDirector("Salim Khan")
                .setYear("1972").setImageUrl("https://lh6.ggpht.com/8oegfFzTenHGQUGxJAU_b62rNYIu0t8hVl-bJ1YmZzNgOWAcP5KtYyJKQ2rjhnc9_lM=w300"));

        return moveInfos;

    }
}
