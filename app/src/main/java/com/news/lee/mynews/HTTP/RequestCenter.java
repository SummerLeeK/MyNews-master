package com.news.lee.mynews.HTTP;

import com.news.lee.mynews.bean.BaseWeixinBean;
import com.news.lee.mynews.bean.BaseZhihuBean;
import com.news.lee.mynews.bean.BaseZhihuDetail;

/**
 * Created by lee on 17-4-2.
 */

public class RequestCenter {


    private static void postRequest(String url, RequestParams params,
                                    DisposeDataListener listener,Class<?> clazz){
        OkhttpManager.get(CommonRequest.
                createGetRequest(url, params), new DisposeDataHandle(listener,clazz));
    }



    public static void requestRecommandData(DisposeDataListener listener){
        RequestCenter.postRequest(DataUrl.ZHIHU_TODAY,null,listener,BaseZhihuBean.class);
    }


    //某一天的知乎日报
    public static void requestLoadMore(DisposeDataListener listener,String date){
        RequestCenter.postRequest(DataUrl.ZHIHU_SOMEDAY+date,null,listener,BaseZhihuBean.class);
    }

    //知乎日报详情页
    public static void requestLoadDetail(DisposeDataListener listener,String id){
        RequestCenter.postRequest(DataUrl.ZHIHU_DETAIL+id,null,listener, BaseZhihuDetail.class);
    }

    public static void requestRecommandDataForWeChat(DisposeDataListener listener){
        RequestCenter.postRequest(DataUrl.WEIXIN_URL+"1",null,listener, BaseWeixinBean.class);
    }
    public static void requestRecommandDataForWeChatWithPage(DisposeDataListener listener,String pageNum){
        RequestCenter.postRequest(DataUrl.WEIXIN_URL+pageNum,null,listener, BaseWeixinBean.class);
    }
}
