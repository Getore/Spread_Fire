package com.keliao.koko.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.keliao.koko.activitys.R;
import com.keliao.koko.base.Movie;

import java.util.List;

/**
 * Created by Carzy on 2018/3/25.
 * Movie.java 的适配器
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private List<Movie> mMovieList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View movieView; //用于View点击
        ImageView movie_image;
        TextView movie_name;
        TextView movie_price;
        TextView movie_chapter;

        public ViewHolder(View view){
            super(view);
            movieView = view;   //用于View点击
            movie_image = (ImageView)view.findViewById(R.id.movie_image);
            movie_name = (TextView)view.findViewById(R.id.movie_name);
            movie_price = (TextView)view.findViewById(R.id.movie_price);
            movie_chapter = (TextView)view.findViewById(R.id.movie_chapter);
        }
    }

    public MovieAdapter(List<Movie> movieList){
        mMovieList = movieList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        //触发点击事件
        holder.movieView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Movie movie = mMovieList.get(position);
                Toast.makeText(view.getContext(), "马上播放 " + movie.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        Movie movie = mMovieList.get(position);
        holder.movie_image.setImageResource(movie.getImageId());
        holder.movie_name.setText(movie.getName());
        holder.movie_price.setText(movie.getPrice());
        holder.movie_chapter.setText(movie.getChapter());
    }

    @Override
    public int getItemCount(){
        return mMovieList.size();
    }
}
