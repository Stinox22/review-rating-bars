package com.stinox.librarytest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.stinox.reviewratingbars.ReviewRatingBars;

public class MainActivity extends AppCompatActivity {

    ReviewRatingBars ratedBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
        ratedBar = new ReviewRatingBars(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        ratedBar.setBar5Value(60);
        ratedBar.setBar4Value(44);
        ratedBar.setBar3Value(32);
        ratedBar.setBar2Value(21);
        ratedBar.setBar1Value(12);
        ratedBar.setShowReviewsCount(true);
        ratedBar.build();
        this.addContentView(ratedBar, params);

    }
}
