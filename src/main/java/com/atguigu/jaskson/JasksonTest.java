package com.atguigu.jaskson;


import com.alibaba.fastjson.JSON;
import com.atguigu.bean.User;
import com.atguigu.jaskson.pojo.JsonUtils;
import com.atguigu.jaskson.pojo.SimpleBean;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Auther: LongYuan
 * @Date: 2021/8/24 0024 - 18:43
 * @Description: com.atguigu.jaskson
 * @version: 1.0
 */


public class JasksonTest {
    /**
    {
        "Sys_Head":{
        "SOURCE_TYPE":"namYYY",
                "BRANCH_ID":"UUU"
    },
        "Body":{
        "yyDs":"sass"
    },
        "userName":"tom",
            "userAge":"12"
    }

     */
    public static void main(String[] args) {
      //Sys_HeadMap    BodyMap
        Map<String,Object> Sys_HeadMap=new HashMap<>();
        Map<String,Object> BodyMap=new HashMap<>();
        Map<String,Object> dataMap=new HashMap<>();

        Sys_HeadMap.put("SOURCE_TYPE","namYYY");
        Sys_HeadMap.put("BRANCH_ID","UUU");

        BodyMap.put("yyDs","yyDs");

        String Sys_HeadMaptoJSONString = JSON.toJSONString(Sys_HeadMap);
        String BodyMaptoJSONString = JSON.toJSONString(BodyMap);

        System.out.println(Sys_HeadMaptoJSONString);
        System.out.println(BodyMaptoJSONString);

        String json ="{\"userName\":\"sdNa\" ,\"age\":12,\"thisYear\":2021}";
        String s = JsonUtils.toUnderlineJSONString(json);
        //去除最前面{
        String dataMapString=s.substring(1,s.length());
        System.out.println(dataMapString);

        StringBuffer sb =new StringBuffer();
        String messgaejson =sb .append("{\"message\": {\"Sys_Head\":")
                .append( Sys_HeadMaptoJSONString)
                .append(",\"Body\":")
                .append(BodyMaptoJSONString)
                .append("," + dataMapString+"}").toString();

//        String messgaejson = Joiner.on("").skipNulls().join("{\"message\": {\"Sys_Head\":"
//                , Sys_HeadMaptoJSONString, ",\"Body\":", BodyMaptoJSONString,
//                "," + dataMapString,"}");
        System.out.println(messgaejson);
        Map<String,Object> MessageMap=new HashMap<>();

    }


    @Test
    public void whenJavaSerializedToXmlStr_thenCorrect() throws JsonProcessingException {
//        XmlMapper xmlMapper = new XmlMapper();
//
//        String xml = xmlMapper.writeValueAsString(new SimpleBean());
//        System.out.println(xml);
//        Assert.assertNotNull(xml);
//        User user = new User();
//        user.setNameInfo("coder");
//        user.setAgeInfo("28");
//        String s = JsonUtils.toUnderlineJSONString(user);
//        System.out.println(s);

        String json ="{\"userName\":\"sdNa\" ,\"age\":12,\"thisYear\":2021}";
        String s = JsonUtils.toUnderlineJSONString(json);
        System.out.println(s);




    }


}
