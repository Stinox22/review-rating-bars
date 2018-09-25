package com.stinox.reviewratingbars;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
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

    public int bar1Value, bar2Value, bar3Value, bar4Value, bar5Value;
    private int layoutWidth, totalRatings;

    private TextView rcBar1, rcBar2, rcBar3, rcBar4, rcBar5;
    public boolean showReviewsCount;


    public ReviewRatingBars(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        this.attrs = attrs;
        initView();
    }

    public ReviewRatingBars(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        this.attrs = attrs;
        this.styleAttr = defStyleAttr;
        initView();
    }

    public ReviewRatingBars(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        this.attrs = attrs;
        this.styleAttr = defStyleAttr;
        initView();
    }

    private void initView() {

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


        totalRatings = sum(bar1Value, bar2Value, bar3Value, bar4Value, bar5Value);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                layoutWidth = view.getWidth();
                setBarLength(bar1, bar1Value);
                setBarLength(bar2, bar2Value);
                setBarLength(bar3, bar3Value);
                setBarLength(bar4, bar4Value);
                setBarLength(bar5, bar5Value);
                if (showReviewsCount) {
                    rcBar1.setText("(" + String.valueOf(bar1Value) + ")");
                    rcBar2.setText("(" + String.valueOf(bar2Value) + ")");
                    rcBar3.setText("(" + String.valueOf(bar3Value) + ")");
                    rcBar4.setText("(" + String.valueOf(bar4Value) + ")");
                    rcBar5.setText("(" + String.valueOf(bar5Value) + ")");
                }
                else {
                    rcBar1.setVisibility(GONE);
                    rcBar2.setVisibility(GONE);
                    rcBar3.setVisibility(GONE);
                    rcBar4.setVisibility(GONE);
                    rcBar5.setVisibility(GONE);
                }
            }
        }, 200);

        typedArray.recycle();
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

    private int sum(int bar1Value, int bar2Value, int bar3Value, int bar4Value, int bar5Value) {
        return bar1Value + bar2Value + bar3Value + bar4Value + bar5Value;
    }

    private void setBarLength(View bar, int value) {
        int barLength = (value * layoutWidth) / totalRatings;
        LinearLayout.LayoutParams newParams = new LinearLayout.LayoutParams(barLength, ViewGroup.LayoutParams.MATCH_PARENT);
        bar.setLayoutParams(newParams);
    }
}
