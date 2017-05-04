package com.example;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

import java.util.Date;

public class MyClass {

    public static void main(String args[]){

        Schema schema=new Schema(1,"com.news.lee.mynews");
        Entity entity=schema.addEntity("ZhihuDaily");
        entity.addIdProperty().primaryKey().autoincrement();
        entity.addStringProperty("image");
        entity.addIntProperty("news_id");
        entity.addStringProperty("title");
        entity.addStringProperty("date");
        entity.addIntProperty("type");

        Entity weixin=schema.addEntity("WeChatDaily");
        weixin.addIdProperty().primaryKey().autoincrement();
        weixin.addStringProperty("date");
        weixin.addStringProperty("title");
        weixin.addStringProperty("description");
        weixin.addStringProperty("image");
        weixin.addStringProperty("url");


        Entity favorites=schema.addEntity("Favorites");
        favorites.addIdProperty().primaryKey().autoincrement();
        favorites.addIntProperty("type");
        favorites.addStringProperty("title");
        favorites.addStringProperty("description");
        favorites.addStringProperty("image");
        favorites.addStringProperty("url");
        favorites.addStringProperty("ctime");



        try {
            new DaoGenerator().generateAll(schema,"../MyNews/app/src/main/java-gen");
        } catch (Exception e) {
            e.printStackTrace();
        }

//        Date date=DateUtils.stringtoDate("2017-01-24",DateUtils.LONG_DATE_FORMAT__);
//        System.out.print(DateUtils.dateToString(date,DateUtils.LONG_DATE_FORMAT));
    }


}
