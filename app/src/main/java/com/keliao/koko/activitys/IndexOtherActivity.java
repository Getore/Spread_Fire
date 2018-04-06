package com.keliao.koko.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.keliao.koko.util.hideTitle;

public class IndexOtherActivity extends hideTitle {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index_other_activity);

        Button login_button = (Button)findViewById(R.id.login_button);
        Button register_button = (Button)findViewById(R.id.register_button);
        Button index_button = (Button)findViewById(R.id.index_button);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_login_button = new Intent(IndexOtherActivity.this,LoginActivity.class);
                //启动
                startActivity(intent_login_button);
            }
        });
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_register_button = new Intent(IndexOtherActivity.this,RegisterActivity.class);
                //启动
                startActivity(intent_register_button);
            }
        });
        index_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_index_button = new Intent(IndexOtherActivity.this,MainActivity.class);
                //启动
                startActivity(intent_index_button);
            }
        });
    }
}
