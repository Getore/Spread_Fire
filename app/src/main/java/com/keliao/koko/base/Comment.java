package com.keliao.koko.base;

/**
 * 2018.03.31
 * 评论Java
 */

public class Comment {
    private int sex;            //1:男；2:女；0：未知
    private String id;
    private String nickName;
    private int imgId;
    private String local;
    private String content;
    private String time;
    private String replyed;    //回复了谁

    public int getSex() {
        return sex;
    }
    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getImgId() {
        return imgId;
    }
    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getLocal() {
        return local;
    }
    public void setLocal(String local) {
        this.local = local;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public String getReplyed() {
        return replyed;
    }
    public void setReplyed(String replyed) {
        this.replyed = replyed;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", nickName='" + nickName + '\'' +
                ", imgId='" + imgId + '\'' +
                ", local='" + local + '\'' +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                ", replyed='" + replyed + '\'' +
                '}';
    }

    public Comment() {

    }
    public Comment(String id, String nickName, int imgId, int sex, String local, String content, String time, String replyed) {
        this.id = id;
        this.nickName = nickName;
        this.imgId = imgId;
        this.sex = sex;
        this.local = local;
        this.content = content;
        this.time = time;
        this.replyed = replyed;
    }

}
