package com.news.lee.mynews.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by lee on 17-3-30.
 */

public class BaseZhihuBean extends BaseModel{


    public String date;

    public ArrayList<ZhihuStories> stories ;

    public ArrayList<ZhihuBody> top_stories ;

//    @Override
//    public String toString() {
//        return "BaseZhihuBean{" +
//                "date='" + date + '\'' +
//                ", stories=" + stories +
//                ", top_stories=" + top_stories +
//                '}';
//    }
}
