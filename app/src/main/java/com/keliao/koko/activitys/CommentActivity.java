package com.keliao.koko.activitys;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.keliao.koko.adapter.MyDetailSoicalAdapter;
import com.keliao.koko.base.Comment;
import com.keliao.koko.util.hideTitle;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommentActivity extends hideTitle {

    @Bind(R.id.am_lv_comments)
    ListView mAmLvComments;
    @Bind(R.id.am_tv_zan)
    TextView mAmTvZan;
    @Bind(R.id.am_tv_comment)
    TextView mAmTvComment;
    @Bind(R.id.am_srl_reflush)
    SwipeRefreshLayout mAmSrlReflush;
    //添加listview头
    View mView;
    //列表数据
    List<Comment> mCommentList;
    //adapter
    BaseAdapter mBaseAdapter;
    @Bind(R.id.am_et_msg)
    EditText mAmEtMsg;
    @Bind(R.id.am_b_save)
    Button mAmBSave;
    @Bind(R.id.am_ll_liuyan)
    LinearLayout mAmLlLiuyan;
    @Bind(R.id.am_ll_info)
    LinearLayout mAmLlInfo;

    //回复的内容
    String info = "";
    //标记位，是评论还是回复。默true认评论
    boolean isComment=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //以下是Fresco相关配置
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initData();
        initAdapter();
        initListener();
    }

    private void initView() {
        mView = LayoutInflater.from(this).inflate(R.layout.head_topic, null);
    }

    private void initData() {
        mCommentList = new ArrayList<>();
        Comment comment = null;
        for (int i = 0; i < 15; i++) {
            if (i % 2 == 0) {
                comment = new Comment(i + "", "学生" + i, R.drawable.head_photo, 1, "湖南中医药大学", "今天真开心，敲了一天代码。", "2018-02-20 20:02:06", null);
            }
            if (i % 2 == 1) {
                comment = new Comment(i + "", "学生" + i, R.drawable.head_photo, 1, "湖南中医药大学", "今天真开心，天气很好，她也很好！！", "2018-03-31 06:12:36", "内测人员00" + i);
            }
            mCommentList.add(comment);
        }
    }

    private void initAdapter() {
        mBaseAdapter = new MyDetailSoicalAdapter(this, mCommentList);
        mAmLvComments.addHeaderView(mView);//添加头
        mAmLvComments.setAdapter(mBaseAdapter);
    }

    private void initListener() {
        mAmLvComments.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int post=position-1;//除去头
                Toast.makeText(CommentActivity.this, "点击了第"+post+"个", Toast.LENGTH_SHORT).show();
                isComment=false;//
                comment(true);//调出评论框
                mAmEtMsg.setText("");//清空
                mAmEtMsg.setHint("回复"+mCommentList.get(post).getNickName());
            }
        });

    }

    @OnClick({R.id.am_tv_zan, R.id.am_tv_comment, R.id.am_et_msg, R.id.am_b_save, R.id.am_ll_liuyan})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.am_tv_zan:
                break;
            case R.id.am_tv_comment:
                //点击评论按钮
                comment(true);
                break;
            case R.id.am_et_msg:
                break;
            case R.id.am_b_save:
                saveComment();
                break;
            case R.id.am_ll_liuyan:
                break;
        }
    }

    private void saveComment() {
        if (!TextUtils.isEmpty(mAmEtMsg.getText())) {
            info = mAmEtMsg.getText().toString();
            updateComment();
        } else {
            //当留言内容为空时，提示
            Toast.makeText(CommentActivity.this, "请输入内容后在留言", Toast.LENGTH_SHORT).show();
        }
    }

    //这边应该上传用户留言，然后刷新界面
    private void updateComment() {
        Comment comment = null;
        comment = new Comment(47 + "", "张三" + 666, R.drawable.head_photo, 1, "信息科学与工程学院",info, "刚刚", null);
        mCommentList.add(0,comment);
        mBaseAdapter.notifyDataSetChanged();
        //还原
        comment(false);
    }

    //对于下面如何实现点击“评论”弹出评论框，同时原有两个按钮消失
    private void comment(boolean flag) {
        if(flag){
            mAmLlInfo.setVisibility(View.GONE);
            mAmLlLiuyan.setVisibility(View.VISIBLE);
            onFocusChange(flag);
        }else{
            mAmLlInfo.setVisibility(View.VISIBLE);
            mAmLlLiuyan.setVisibility(View.GONE);
            onFocusChange(flag);
        }
    }

    //显示或隐藏输入法
    private void onFocusChange(boolean hasFocus) {
        final boolean isFocus = hasFocus;
        (new Handler()).postDelayed(new Runnable() {
            public void run() {
                InputMethodManager imm = (InputMethodManager)
                        CommentActivity.this.getSystemService(INPUT_METHOD_SERVICE);
                if (isFocus) {
                    //显示输入法
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    mAmEtMsg.setFocusable(true);
                    mAmEtMsg.requestFocus();
                } else {
                    //隐藏输入法
                    imm.hideSoftInputFromWindow(mAmEtMsg.getWindowToken(), 0);
                }
            }
        }, 100);
    }
}
