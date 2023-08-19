package com.example.homeservices;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DragActivity extends AppCompatActivity {

    private ViewPager mSliderViewPager;
    private LinearLayout mDotLayout;

    private TextView[] mDots;

    private SliderAdapter sliderAdapter;

    private Button mNextBtn;
    private int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag);

        mSliderViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout = (LinearLayout) findViewById(R.id.dotsLayout);

        mNextBtn = (Button) findViewById(R.id.next);

        sliderAdapter = new SliderAdapter(this);

        mSliderViewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);

        mSliderViewPager.addOnPageChangeListener(viewListener);

        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSliderViewPager.setCurrentItem(mCurrentPage + 1);

                if (mCurrentPage == 3){
                    Intent nextIntent = new Intent(DragActivity.this,login.class);
                    startActivity(nextIntent);
                }
            }
        });

    }

    public void addDotsIndicator(int position){

        mDots = new TextView[4];
        mDotLayout.removeAllViews();

        for (int i=0; i<mDots.length; i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.text1));


            mDotLayout.addView(mDots[i]);
        }


        if (mDots.length > 0){

            mDots[position].setTextColor(getResources().getColor(R.color.text));

        }

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {

            addDotsIndicator(i);
            mCurrentPage = i;

            if (i == 0){
                mNextBtn.setEnabled(true);
                mNextBtn.setText("Next");
            }
            else if (i == mDots.length - 1){
                mNextBtn.setEnabled(true);
                mNextBtn.setText("Skip");
            }
            else{
                mNextBtn.setEnabled(true);
                mNextBtn.setText("Next");
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}