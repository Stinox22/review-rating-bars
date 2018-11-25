package com.stinox.reviewratingbars;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ReviewRatingBars extends RelativeLayout {

    /**
     * Core Items
     */
    private Context mContext;
    private AttributeSet attrs;
    private int styleAttr;
    private View bar1, bar2, bar3, bar4, bar5;
    private LinearLayout view;

    private int bar1Value;
    private int bar2Value;
    private int bar3Value;
    private int bar4Value;
    private int bar5Value;
    private int layoutWidth, totalRatings = 1;

    private TextView rcBar1, rcBar2, rcBar3, rcBar4, rcBar5;
    private boolean showReviewsCount;

    public boolean isShowReviewsCount() {
        return showReviewsCount;
    }

    public void setShowReviewsCount(boolean showReviewsCount) {
        this.showReviewsCount = showReviewsCount;
    }

    public ReviewRatingBars(Context context) {
        super(context, null);
        this.mContext = context;
        createView();
    }

    public ReviewRatingBars(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        this.attrs = attrs;
        createView();
    }

    public ReviewRatingBars(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        this.attrs = attrs;
        this.styleAttr = defStyleAttr;
        createView();
    }

    public ReviewRatingBars(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        this.attrs = attrs;
        this.styleAttr = defStyleAttr;
        createView();
    }

    private void initializeViews() {
        bar1 = findViewById(R.id.view_bar_1);
        bar2 = findViewById(R.id.view_bar_2);
        bar3 = findViewById(R.id.view_bar_3);
        bar4 = findViewById(R.id.view_bar_4);
        bar5 = findViewById(R.id.view_bar_5);
        view = findViewById(R.id.ll_rated_bar);
        rcBar1 = findViewById(R.id.tv_review_count_bar_1);
        rcBar2 = findViewById(R.id.tv_review_count_bar_2);
        rcBar3 = findViewById(R.id.tv_review_count_bar_3);
        rcBar4 = findViewById(R.id.tv_review_count_bar_4);
        rcBar5 = findViewById(R.id.tv_review_count_bar_5);
    }

    public void createView() {

        //Inflate our layout
        inflate(mContext, R.layout.review_rating_bars_layout, this);

        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.ReviewRatingBars, styleAttr, 0);

        initializeViews();

        bar1Value = typedArray.getInteger(R.styleable.ReviewRatingBars_bar_1, 0);
        bar2Value = typedArray.getInteger(R.styleable.ReviewRatingBars_bar_2, 0);
        bar3Value = typedArray.getInteger(R.styleable.ReviewRatingBars_bar_3, 0);
        bar4Value = typedArray.getInteger(R.styleable.ReviewRatingBars_bar_4, 0);
        bar5Value = typedArray.getInteger(R.styleable.ReviewRatingBars_bar_5, 0);
        showReviewsCount = typedArray.getBoolean(R.styleable.ReviewRatingBars_show_review_count, true);

        build();
        typedArray.recycle();
    }

    public void build(){

        totalRatings = sum(bar1Value, bar2Value, bar3Value, bar4Value, bar5Value);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
//        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
//        layoutWidth = displayMetrics.widthPixels;
                layoutWidth = view.getWidth();
                setBarLength(bar1, bar1Value);
                setBarLength(bar2, bar2Value);
                setBarLength(bar3, bar3Value);
                setBarLength(bar4, bar4Value);
                setBarLength(bar5, bar5Value);
                if (showReviewsCount) {
                    rcBar1.setText(convertString(bar1Value));
                    rcBar2.setText(convertString(bar2Value));
                    rcBar3.setText(convertString(bar3Value));
                    rcBar4.setText(convertString(bar4Value));
                    rcBar5.setText(convertString(bar5Value));
//                    rcBar1.setText(String.valueOf(bar1Value));
//                    rcBar2.setText(String.valueOf(bar2Value));
//                    rcBar3.setText(String.valueOf(bar3Value));
//                    rcBar4.setText(String.valueOf(bar4Value));
//                    rcBar5.setText(String.valueOf(bar5Value));
                }
                else {
                    rcBar1.setVisibility(GONE);
                    rcBar2.setVisibility(GONE);
                    rcBar3.setVisibility(GONE);
                    rcBar4.setVisibility(GONE);
                    rcBar5.setVisibility(GONE);
                }
            }
        }, 250);

    }

    private int getBarLength(int searchValue){
        if (rcBar1.getText().equals(String.valueOf(searchValue)))
            return rcBar1.getWidth();
        else
        if (rcBar2.getText().equals(String.valueOf(searchValue)))
            return rcBar2.getWidth();
        else
        if (rcBar3.getText().equals(String.valueOf(searchValue)))
            return rcBar3.getWidth();
        else
        if (rcBar4.getText().equals(String.valueOf(searchValue)))
            return rcBar4.getWidth();
        else
        if (rcBar5.getText().equals(String.valueOf(searchValue)))
            return rcBar5.getWidth();
        else
            return 0;
    }

    private String convertString(int value){
        return "(" + String.valueOf(value) + ")";
    }

    private int sum(int bar1Value, int bar2Value, int bar3Value, int bar4Value, int bar5Value) {
        return bar1Value + bar2Value + bar3Value + bar4Value + bar5Value;
    }

    private void setBarLength(View bar, int value) {
        try {
            int barLength = ((value * layoutWidth) / totalRatings);
            if (isShowReviewsCount()) {
                int textLength = dpToPx(getBarLength(value));
                barLength = ((value * layoutWidth) / totalRatings) - textLength; //Subtract review count length
            }
            LinearLayout.LayoutParams newParams = new LinearLayout.LayoutParams(barLength, ViewGroup.LayoutParams.MATCH_PARENT);
            bar.setLayoutParams(newParams);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }



    public int getBar1Value() {
        return bar1Value;
    }

    public void setBar1Value(int bar1Value) {
        this.bar1Value = bar1Value;
    }

    public int getBar2Value() {
        return bar2Value;
    }

    public void setBar2Value(int bar2Value) {
        this.bar2Value = bar2Value;
    }

    public int getBar3Value() {
        return bar3Value;
    }

    public void setBar3Value(int bar3Value) {
        this.bar3Value = bar3Value;
    }

    public int getBar4Value() {
        return bar4Value;
    }

    public void setBar4Value(int bar4Value) {
        this.bar4Value = bar4Value;
    }

    public int getBar5Value() {
        return bar5Value;
    }

    public void setBar5Value(int bar5Value) {
        this.bar5Value = bar5Value;
    }

}
