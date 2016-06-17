package com.kangladevelopers.filmfinder.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.kangladevelopers.filmfinder.DataModel.MoveInfo;
import com.kangladevelopers.filmfinder.DataModel.MovieInfo2;
import com.kangladevelopers.filmfinder.R;
import com.kangladevelopers.filmfinder.Utility.LogMessage;

public class MovieDetailActivity extends AppCompatActivity {

    private TextView tvDirector,tv_producer,tvSynopsis,tvRuntime,tvBudget,tvBoxoffice,tvStarring,tvType,tvCountry,tvYear;
    private TextView tvRatingImdb,tvRatingRottenTomato,tvRatingMetaCritic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_move_details);
        MovieInfo2 moveInfo = (MovieInfo2) getIntent().getSerializableExtra("object");
        LogMessage.showToast(""+moveInfo.getName()+"\n"+moveInfo.getDirector()+"\n"+moveInfo.getYear());
        mapWithXml();
        setData(moveInfo);
    }

    private void setData(MovieInfo2 moveInfo) {
        tvDirector.setText("Directed by: "+moveInfo.getDirector());
        tv_producer.setText("Produced by: NA");
        tvRuntime.setText("Duration: "+moveInfo.getRuntime());
        tvBudget.setText("Budget: "+moveInfo.getBudget());
        tvBoxoffice.setText("Box Office: "+moveInfo.getBox_office());
        tvSynopsis.setText("Sypnosis: "+moveInfo.getSynopsis());
        tvRatingImdb.setText(moveInfo.getImdb()+"/10");
        tvRatingRottenTomato.setText(moveInfo.getRotten_tometo()+"%");
        tvYear.setText("Year: "+moveInfo.getYear());
        tvType.setText("Generie: "+moveInfo.getType());
        tvStarring.setText("Starred by: "+moveInfo.getStarring());
        tvCountry.setText("Country: "+moveInfo.getCountry());

    }

    private void mapWithXml() {
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
}
