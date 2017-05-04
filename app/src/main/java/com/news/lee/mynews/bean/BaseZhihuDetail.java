package com.news.lee.mynews.bean;

import java.util.ArrayList;

/**
 * Created by lee on 17-4-14.
 */

public class BaseZhihuDetail extends BaseModel {

    @ParamNames("body")
    public String body;

    @ParamNames("image_source")
    public String image_source;

    @ParamNames("title")
    public String title;

    @ParamNames("image")
    public String image;

    @ParamNames("share_url")
    public String share_url;

    @ParamNames("js")
    public ArrayList<String> js;

    @ParamNames("ga_prefix")
    public String ga_prefix;

    @ParamNames("images")
    public ArrayList<String> images;

    @ParamNames("type")
    public int type;

    @ParamNames("id")
    public int id;

    @ParamNames("css")
    public ArrayList<String> css;

}
