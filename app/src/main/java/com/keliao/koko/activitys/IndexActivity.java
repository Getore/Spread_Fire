package com.keliao.koko.activitys;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import com.keliao.koko.util.hideTitle;
import java.util.Timer;
import java.util.TimerTask;

public class IndexActivity extends hideTitle {

    private Button skipAd;
    private ImageButton adImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index_activity);

        skipAd = (Button)findViewById(R.id.skip_ad);
        adImage = (ImageButton)findViewById(R.id.ad_image);

        skipAd.setOnClickListener(mListener);
        adImage.setOnClickListener(mListener);

        Timer timer=new Timer();
        TimerTask task=new TimerTask() {
            @Override
            public void run(){
                //3秒  跳过广告
                Intent intent = new Intent(IndexActivity.this, IndexOtherActivity.class);
                startActivity(intent);
                finish();
            }
        };
        timer.schedule(task,3000);  //此处的Delay可以是3*1000，代表三秒
    }

    View.OnClickListener mListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.skip_ad:
                    Intent intent_skip_ad = new Intent(IndexActivity.this,IndexOtherActivity.class);
                    //启动
                    startActivity(intent_skip_ad);
                    break;
                case R.id.ad_image:
                    Intent intent_ad_image = new Intent(Intent.ACTION_VIEW);
                    intent_ad_image.setData(Uri.parse("http://www.hnucm.edu.cn/"));
                    //启动
                    startActivity(intent_ad_image);
                    break;
                default:
                    break;
            }
            finish();
        }
    };

}