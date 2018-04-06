package com.keliao.koko.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.keliao.koko.util.hideTitle;

public class SettingActivity extends hideTitle {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity);

        //点击返回
        ImageButton backImageButton = (ImageButton)findViewById(R.id.backImageButton);
        backImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
