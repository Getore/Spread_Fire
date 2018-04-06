package com.keliao.koko.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.keliao.koko.base.Movie;
import com.keliao.koko.adapter.MovieAdapter;
import com.keliao.koko.util.hideTitle;

import java.util.ArrayList;
import java.util.List;

import br.com.mauker.materialsearchview.MaterialSearchView;

public class SearchingActivity extends hideTitle {

    //private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private MaterialSearchView searchView;
    private Button bt_clearHistory;
    private List<Movie> movieList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searching_activity);

        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        bt_clearHistory = (Button) findViewById(R.id.bt_clearHistory);

        //点击返回
        ImageButton backImageButton = (ImageButton)findViewById(R.id.backImageButton);
        backImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //这个接口处理MaterialSearchView中的QueryTextChange或者QueryTextSubmit事件
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //todo 这里写死了，跳转到视频的界面（出现bug：不能存下历史记录）
                Intent intent = new Intent(SearchingActivity.this, WatchMovieActivity.class);
                startActivity(intent);
                return true;    //true就可以搜索
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        searchView.setSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewOpened() {
                // Do something once the view is open.
            }

            @Override
            public void onSearchViewClosed() {
                // Do something once the view is closed.
            }
        });
        searchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Do something when the suggestion list is clicked.
                String suggestion = searchView.getSuggestionAtPosition(position);

                searchView.setQuery(suggestion, false);
            }
        });

        //清除搜索历史
        bt_clearHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearHistory();
            }
        });
        //测试搜索
        Button search_button = (Button)findViewById(R.id.search_button);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //显示搜索
                searchView.openSearch();
            }
        });

        final Context context = this;
        searchView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(context, "Long clicked position: " + i, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        searchView.setOnVoiceClickedListener(new MaterialSearchView.OnVoiceClickedListener() {
            @Override
            public void onVoiceClicked() {
                Toast.makeText(context, "Voice clicked!", Toast.LENGTH_SHORT).show();
            }
        });

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
            Movie one000 = new Movie("应届菜鸟到架构师的一次完美蜕变", "费用:99元", "共有5章", R.drawable.movie000 );
            movieList.add(one000);
            Movie one001 = new Movie("职场零距离成长无极限", "费用:111元", "共有8章", R.drawable.movie001 );
            movieList.add(one001);
            Movie one002 = new Movie("深入学习(入门)", "费用:89元", "共有4章", R.drawable.movie002 );
            movieList.add(one002);
            Movie one003 = new Movie("技术类应届生，简历制作与面试技巧", "费用:47元", "共有2章", R.drawable.movie003 );
            movieList.add(one003);
            Movie one004 = new Movie("HTML入门基础", "费用:免费", "共有11章", R.drawable.movie004 );
            movieList.add(one004);
            Movie one005 = new Movie("机器学习特训营", "费用:免费", "共有6章", R.drawable.movie005 );
            movieList.add(one005);
            Movie one006 = new Movie("基础算法(传奇ACMer小岛)", "费用:免费", "共有10章", R.drawable.movie006 );
            movieList.add(one006);
        }
    }

    //为了关闭搜索view，这里有一个返回按钮
    @Override
    public void onBackPressed() {
        if (searchView.isOpen()) {
            // Close the search on the back button press.
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MaterialSearchView.REQUEST_VOICE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches != null && matches.size() > 0) {
                String searchWrd = matches.get(0);
                if (!TextUtils.isEmpty(searchWrd)) {
                    searchView.setQuery(searchWrd, false);
                }
            }

            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onPause() {
        super.onPause();
        searchView.clearSuggestions();
    }

    @Override
    protected void onResume() {
        super.onResume();
        searchView.activityResumed();
        String[] arr = getResources().getStringArray(R.array.suggestions);

        searchView.addSuggestions(arr);
    }
    //清除历史记录
    private void clearHistory() {
        searchView.clearHistory();
    }

}
