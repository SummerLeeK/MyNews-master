package com.news.lee.mynews.bean;

import java.util.ArrayList;

/**
 * Created by lee on 17-4-18.
 */

public class BaseWeixinBean extends BaseModel{
    @ParamNames("code")
    public int code;
    @ParamNames("emsg")
    public String emsg;
    @ParamNames("newslist")
    public ArrayList<Weixinbean> newslist;
}
