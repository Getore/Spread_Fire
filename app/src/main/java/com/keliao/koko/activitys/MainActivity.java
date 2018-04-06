package com.keliao.koko.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.keliao.koko.util.hideTitle;
import com.keliao.koko.base.photo;
import com.keliao.koko.adapter.photoAdapter;

import java.util.ArrayList;
import java.util.List;

import sbingo.freeradiogroup.FreeRadioGroup;

/**
 * Created by Carzy on 2018/3/17.
 * 主界面的功能
 */

public class MainActivity extends hideTitle {

    private DrawerLayout mDrawerLayout;
    private FreeRadioGroup mGroup;
    private List<photo> photoList = new ArrayList<>();  //在第一个界面的广告左右滑动
    private LinearLayout first_linearLayout;
    private RecyclerView ad_recycler_view;  //在第一个界面的一个滑动窗口
    private LinearLayout second_linearLayout;
    private LinearLayout three_linearLayout;
    private RadioButton rb_1;
    private RadioButton rb_2;
    private RadioButton rb_3;
    private ImageButton specializedTraining;
    private ImageButton classLearning;
    private ImageButton errorPractice;
    private NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mGroup = (FreeRadioGroup) findViewById(R.id.group);
        first_linearLayout = (LinearLayout) findViewById(R.id.first_linearLayout);
        second_linearLayout = (LinearLayout)findViewById(R.id.second_linearLayout);
        three_linearLayout = (LinearLayout)findViewById(R.id.three_linearLayout);
        rb_1 = (RadioButton)findViewById(R.id.rb_1);
        rb_2 = (RadioButton)findViewById(R.id.rb_2);
        rb_3 = (RadioButton)findViewById(R.id.rb_3);
        specializedTraining = (ImageButton) findViewById(R.id.specialized_trainingImageButton);
        classLearning = (ImageButton) findViewById(R.id.class_learningImageButton);
        errorPractice = (ImageButton) findViewById(R.id.error_practiceImageButton);
        //主界面左侧的滑动页面找到id
        navView = (NavigationView)findViewById(R.id.nav_vuew);

        //进入界面时首先显示第一个界面，将其他界面隐藏
        second_linearLayout.setVisibility(View.GONE);
        three_linearLayout.setVisibility(View.GONE);
        first_linearLayout.setVisibility(View.VISIBLE);

        //可以全屏浮动的4个按钮
        mGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_1:
                        second_linearLayout.setVisibility(View.GONE);
                        three_linearLayout.setVisibility(View.GONE);
                        first_linearLayout.setVisibility(View.VISIBLE);
                        break;
                    case R.id.rb_2:
                        first_linearLayout.setVisibility(View.GONE);
                        three_linearLayout.setVisibility(View.GONE);
                        second_linearLayout.setVisibility(View.VISIBLE);
                        break;
                    case R.id.rb_3:
                        second_linearLayout.setVisibility(View.GONE);
                        first_linearLayout.setVisibility(View.GONE);
                        three_linearLayout.setVisibility(View.VISIBLE);
                        break;
                    default:
                        break;
                }
            }
        });

        //主界面左侧的滑动页面
        navView.setCheckedItem(R.id.my_get_card);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();   //关闭滑动菜单
                return true;
            }
        });

        //TODO 1.点击实现功能
        specializedTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "即将进入专项练习", Toast.LENGTH_LONG).show();
            }
        });
        classLearning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_classLearning = new Intent(MainActivity.this, SearchingActivity.class);
                startActivity(intent_classLearning);
                //Toast.makeText(MainActivity.this, "即将进入课程学习", Toast.LENGTH_SHORT).show();
            }
        });
        errorPractice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "即将进入错题练习", Toast.LENGTH_SHORT).show();
            }
        });

        initphoto();    //初始化图片的数据
        ad_recycler_view = (RecyclerView)findViewById(R.id.ad_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        ad_recycler_view.setLayoutManager(layoutManager);
        photoAdapter adapter = new photoAdapter(photoList);
        ad_recycler_view.setAdapter(adapter);
    }

    //初始化图片的数据
    private void initphoto(){
        photo adone = new photo(R.drawable.ad_one);
        photoList.add(adone);
        photo adtwo = new photo(R.drawable.adtwo);
        photoList.add(adtwo);
    }
}