package com.atguigu.testJsonAndXml;

import org.json.JSONObject;
import org.json.XML;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * @Auther: LongYuan
 * @Date: 2021/9/15 0015 - 17:40
 * @Description: com.atguigu.testJsonAndXml
 * @version: 1.0
 */


public class testt {

    public static void main(String[] args) {
        String xml="";

        String s = Stream.of("a", "b", null)
                .filter(Objects::nonNull)
                .findFirst()
                .orElse("c");
        System.out.println(s);
//        JSONObject xmlJSONObj = XML.toJSONObject(xml);
//        //设置缩进
//        String jsonPrettyPrintString = xmlJSONObj.toString(4);
//        //输出格式化后的json
//        System.out.println(jsonPrettyPrintString);

    }
}
