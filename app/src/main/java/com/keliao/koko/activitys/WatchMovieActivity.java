package com.keliao.koko.activitys;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.keliao.koko.adapter.MovieAdapter;
import com.keliao.koko.base.Movie;
import com.keliao.koko.util.hideTitle;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class WatchMovieActivity extends hideTitle {

    private VideoView videoView;
    private MediaController mediaController;
    private ImageButton backImageButton;
    private ImageButton inComments;
    private List<Movie> movieList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        setContentView(R.layout.watch_movie_activity);

        videoView = (VideoView)findViewById(R.id.video_view);
        backImageButton = (ImageButton)findViewById(R.id.backImageButton);
        inComments = (ImageButton)findViewById(R.id.right_ImageButton);

        //初始化mediaController
        mediaController=new MediaController(this);
        //将videoView与mediaController建立关联
        videoView.setMediaController(mediaController);
        //将mediaController与videoView建立关联
        mediaController.setMediaPlayer(videoView);

        if (ContextCompat.checkSelfPermission(WatchMovieActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(WatchMovieActivity.this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            initVideoPath();    //初始化MediaPlayer
        }
        //点击返回
        backImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //点击进入评论区
        inComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WatchMovieActivity.this, CommentActivity.class);
                startActivity(intent);
            }
        });
        //推荐视频区域
        initMovies();   //初始化课程数据
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.search_recycler_view);
        //实现瀑布流方式
        //StaggeredGridLayoutManager layoutManager = new
        //        StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);     //瀑布形式
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        MovieAdapter adapter = new MovieAdapter(movieList);
        recyclerView.setAdapter(adapter);
    }
    //初始化课程数据
    private void initMovies(){
        for (int i = 0; i < 100; i++){
            Movie one003 = new Movie("技术类应届生，简历制作与面试技巧", "费用:47元", "共有2章", R.drawable.movie003 );
            movieList.add(one003);
            Movie one004 = new Movie("HTML入门基础", "费用:免费", "共有11章", R.drawable.movie004 );
            movieList.add(one004);
            Movie one005 = new Movie("机器学习特训营", "费用:免费", "共有6章", R.drawable.movie005 );
            movieList.add(one005);
            Movie one006 = new Movie("基础算法(传奇ACMer小岛)", "费用:免费", "共有10章", R.drawable.movie006 );
            movieList.add(one006);
            Movie one000 = new Movie("应届菜鸟到架构师的一次完美蜕变", "费用:99元", "共有5章", R.drawable.movie000 );
            movieList.add(one000);
            Movie one001 = new Movie("职场零距离成长无极限", "费用:111元", "共有8章", R.drawable.movie001 );
            movieList.add(one001);
            Movie one002 = new Movie("深入学习(入门)", "费用:89元", "共有4章", R.drawable.movie002 );
            movieList.add(one002);
        }
    }

    //指定播放视频文件的路径(此处是播放本地根目录文件下的movie.mp4视频)
    private void initVideoPath(){
        File file = new File(Environment.getExternalStorageDirectory(), "movie.mp4");
        videoView.setVideoPath(file.getPath()); //指定视频文件的路径
        //让videoView获得焦点
        //videoView.requestFocus();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    initVideoPath();
                } else {
                    Toast.makeText(this, "拒绝权限将无法使用程序", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
                default:
        }
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        if (videoView != null){
            videoView.suspend();
        }
    }
}
