package com.example.drizzle.smsapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import april.yun.JPagerSlidingTabStrip;
import april.yun.tabstyle.JTabStyle;

import static android.graphics.Typeface.BOLD;

public class HomeActivity extends AppCompatActivity {

    private SMSPagerAdapter adapter;
    private JPagerSlidingTabStrip pagerSlidingTabStrip;
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pagerSlidingTabStrip = findViewById(R.id.psts_sms);
        viewPager = findViewById(R.id.vp_sms);

        adapter = new SMSPagerAdapter(getSupportFragmentManager(),
                new String[]{"CONTACT LIST", "SMS DETAILS"}
                );
        viewPager.setAdapter(adapter);
        pagerSlidingTabStrip.getTabStyleDelegate()
                .setJTabStyle(JTabStyle.MOVESTYLE_DEFAULT)
                .setShouldExpand(true)
                .setFrameColor(getColor(R.color.pagerUnderline))
                .setTextColor(R.color.text_black)
                .setTabTextColor(R.color.text_black)
                .setTabTextSize(30)
                .setTabTypeface(FontCache.getTypeface("sans_pro_bold", this))
                .setTabTypefaceStyle(BOLD)
                .setDividerWidth(0)
                .setDividerColor(getColor(R.color.transparent))
                .setDividerPadding(0)
                .setUnderlineColor(getColor(R.color.pagerUnderline))
                .setUnderlineHeight(1)
                .setIndicatorColor(getColor(R.color.colorPrimary))
                .setIndicatorHeight(1);
        pagerSlidingTabStrip.bindViewPager(viewPager);
    }


}
