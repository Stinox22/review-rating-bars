package com.stinox.librarytest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.stinox.reviewratingbars.ReviewRatingBars;

public class MainActivity extends AppCompatActivity {

    ReviewRatingBars ratedBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ratedBar = findViewById(R.id.rated_bar_main);
        ratedBar.showReviewsCount = true;
    }
}
