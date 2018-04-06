package com.keliao.koko.base;

/**
 * Created by Carzy on 2018/3/25.
 * 作为视频搜索学习的瀑布模型
 */

public class Movie {
    private String name;    //视频的名字
    private String price;   //价格 免费or100元...
    private String chapter; //视频的章节
    private int imageId;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }

    public String getChapter() {
        return chapter;
    }
    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public int getImageId() {
        return imageId;
    }
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public Movie(String name, String price, String chapter, int imageId){
        this.name = name;
        this.price = price;
        this.chapter = chapter;
        this.imageId = imageId;
    }
}
