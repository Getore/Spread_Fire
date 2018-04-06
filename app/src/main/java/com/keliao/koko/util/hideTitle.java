package com.keliao.koko.util;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.keliao.koko.activitys.R;
/**
 * Created by Carzy on 2018/3/4.
 * 隐藏系统自带的标题栏
 */
public class hideTitle extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title);

        //隐藏系统自带的标题栏
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
    }
}
