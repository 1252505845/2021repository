package com.atguigu.testJsonAndXml;

/**
 * @Auther: LongYuan
 * @Date: 2021/9/6 0006 - 15:07
 * @Description: com.atguigu.testJsonAndXml
 * @version: 1.0
 */

import com.atguigu.bean.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.SQLOutput;

/**
 * 将json里的 userName转化为USER_NAME
 */
public class testHump {

//    {
//
//        "courseStageId": "PcjbvAQ8h9KfZ8q6UZcw",
//                "gradeId": "26vYkWDVjhivNno6Kbz7ZM",
//                "code": 1009430255,
//                "stageId": "go2Leq1wj5y8vuA5w7Azw"
//    }

    public static void main(String[] args) {
//        String json ="{\"userame\":\"sda\" ,\"age\":12,\"thisYear\":2021}";


        User user = new User();
        user.setNameInfo("coder");
        user.setAgeInfo("28");

        ObjectMapper mapper = new ObjectMapper();

//        SNAKE_CASE  增加下划线
//        UPPER_CAMEL_CASE  驼峰  第一个字母大写  之后首字母大写
//        LOWER_CAMEL_CASE  驼峰第一个字母小写 之后首字母小写
//        LOWER_CASE   全小写
//         KEBAB_CASE   加横杠  name-info
        //增加下划线
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

        try {
            String ss=mapper.writeValueAsString(user);
            System.out.println(ss);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
//        IDENTITY   驼峰  第一个字母小写  之后首字母大写
//        UPPER_CAMEL_CASE   驼峰  第一个字母大写  之后首字母大写
//        UPPER_CAMEL_CASE_WITH_SPACES  Name Info
//        LOWER_CASE_WITH_UNDERSCORES   下划线
//        LOWER_CASE_WITH_DASHES   横杠
//        LOWER_CASE_WITH_DOTS   点

        String json2=new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DOTS)
                .create()
                .toJson(user);
        System.out.println(json2);

    }









}
