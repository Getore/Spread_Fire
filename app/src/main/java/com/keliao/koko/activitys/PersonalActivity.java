package com.keliao.koko.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.keliao.koko.util.hideTitle;

public class PersonalActivity extends hideTitle {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_activity);

        //进入登录界面
        Button login_button = (Button)findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_login_button = new Intent(PersonalActivity.this,LoginActivity.class);
                //启动
                startActivity(intent_login_button);
            }
        });

        //进入设置界面
        Button setting_button = (Button)findViewById(R.id.setting_button);
        setting_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_setting_button = new Intent(PersonalActivity.this,SettingActivity.class);
                //启动
                startActivity(intent_setting_button);
            }
        });

    }
}
