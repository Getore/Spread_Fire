package com.keliao.koko.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.keliao.koko.activitys.R;
import com.keliao.koko.base.photo;

import java.util.List;

/**
 * Created by Carzy on 2018/3/20.
 * photo.java 的适配器
 */

public class photoAdapter extends RecyclerView.Adapter<photoAdapter.ViewHolder>{
    private List<photo> mPhotoList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View photoView;     //实现点击事件①
        ImageView ad_image;

        public ViewHolder(View view){
            super(view);
            photoView = view;   //实现点击事件②
            ad_image = (ImageView)view.findViewById(R.id.ad_image);
        }
    }

    public photoAdapter(List<photo> photoList){
        mPhotoList = photoList;
    }

    //找到photo_activity的位置
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photo_item, parent, false);
        //实现点击事件③
        final ViewHolder holder = new ViewHolder(view);
        //实现点击事件④
        holder.photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                photo photo = mPhotoList.get(position);
                Toast.makeText(v.getContext(), "你点击了view", Toast.LENGTH_SHORT).show();
            }
        });
        //实现点击事件⑤
        holder.ad_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                photo photo = mPhotoList.get(position);
                Toast.makeText(v.getContext(), "你点击了image", Toast.LENGTH_SHORT).show();
            }
        });
        //不能点击的代码,可删 ---->  ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        photo photo = mPhotoList.get(position);
        holder.ad_image.setImageResource(photo.getImageId());
    }

    //告诉RecyclerView一共有多少子项
    @Override
    public int getItemCount(){
        return mPhotoList.size();
    }

}
