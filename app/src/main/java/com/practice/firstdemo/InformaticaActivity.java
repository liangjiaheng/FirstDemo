package com.practice.firstdemo;

import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.practice.firstdemo.adapter.MyPagerAdapter;
import com.practice.firstdemo.fragment.FragInformation;
import com.practice.firstdemo.fragment.FragNewFlash;
import com.practice.firstdemo.utils.ViewFindUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class InformaticaActivity extends AppCompatActivity implements View.OnClickListener {

    private View view;
    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<Fragment> fragments;
    private FragNewFlash newFlash;
    private FragInformation information;
    private List<String> title;
    private ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informatica);
        //初始化控件
        initView();
        //初始化Fragment集合
        initFragment();
        //设置TabLayout & ViewPager联动
        setTabConnPager();


    }

    @Override
    protected void onStart() {
        super.onStart();
        mTabLayout.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(mTabLayout, 40, 40);
            }
        });
    }

    /**
     * 设置TabLayout & ViewPager联动
     */
    private void setTabConnPager() {
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), title, fragments);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(adapter);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    /**
     * 初始化Fragment集合
     */
    private void initFragment() {
        fragments = new ArrayList<>();
        newFlash = FragNewFlash.getInstance(getApplicationContext());
        information = FragInformation.getInstance(getApplicationContext());
        fragments.add(newFlash);
        fragments.add(information);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        view = getWindow().getDecorView();
        mTabLayout = ViewFindUtils.find(view, R.id.tabLayout_history);
        mViewPager = ViewFindUtils.find(view, R.id.vp_pager);
        mToolbar = ViewFindUtils.find(view, R.id.toolbar_title);
        imgBack = ViewFindUtils.find(view, R.id.back);
        imgBack.setOnClickListener(this);

        LinearLayout linearLayout = (LinearLayout) mTabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(this, R.drawable.layout_divider_vertical));
        linearLayout.setDividerPadding(15);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);


        //初始化TabLayout信息
        title = new ArrayList<>();
        title.add("十大快讯");
        title.add("公司新闻");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                Toast.makeText(this, "回退", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }

    }

}
