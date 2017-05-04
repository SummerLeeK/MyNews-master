package com.news.lee.mynews.bean;

import java.util.ArrayList;

/**
 * Created by u on 2017/3/17.
 */

public class ZhihuStories extends BaseModel{


    @ParamNames("images")
    public ArrayList<String> images;
    @ParamNames("type")
    public int type;
    @ParamNames("id")
    public int id;
    @ParamNames("ga_prefix")
    public String ga_prefix;
    @ParamNames("title")
    public String title;

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
//
//    @Override
//    public String toString() {
//        return "ZhihuStories{" +
//                "images=" + images +
//                ", type=" + type +
//                ", id=" + id +
//                ", ga_prefix='" + ga_prefix + '\'' +
//                ", title='" + title + '\'' +
//                '}';
//    }
}
