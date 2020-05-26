package com.example.kahuja3project2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;

public class ViewDetailsActivity extends Activity {
    TextView movieRelase;
    TextView movieDuration;
    TextView movieDirector;
    TextView movieStars;
    TextView movieRatings;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moviedetails);
        Intent intent = this.getIntent();
        final Bundle bundle = getIntent().getExtras();
        movieRelase = (TextView) findViewById(R.id.release);
        movieDuration = (TextView) findViewById(R.id.duration);
        movieDirector = (TextView) findViewById(R.id.director);
        movieStars = (TextView) findViewById(R.id.stars);
        movieRatings = (TextView) findViewById(R.id.ratings);

        if(bundle!=null){
            movieRelase.setText(bundle.getString("release"));
            movieDuration.setText(bundle.getString("duration"));
            movieDirector.setText(bundle.getString("director"));
            movieStars.setText(bundle.getString("stars"));
            movieRatings.setText(bundle.getString("ratings"));

        }

    }


}
