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
        String xml="2021suusyq1234<appHead>iuyywghjsjsuus</appHead>567uuuiwuqi1025";

        int start = xml.indexOf("<appHead>");
        int end = xml.indexOf("</appHead>");
        System.out.println(start+"--"+end);
        StringBuffer stringBuffer=new StringBuffer(xml.trim());
        String s = stringBuffer.substring(0, start);
        String s1 = stringBuffer.substring(end+10, xml.length());
//        System.out.println(s);
//        System.out.println(s1);
        StringBuffer str=new StringBuffer();
        String toString = str.append(s).append(s1).toString();
        System.out.println(toString);
//        JSONObject xmlJSONObj = XML.toJSONObject(xml);
//        //设置缩进
//        String jsonPrettyPrintString = xmlJSONObj.toString(4);
//        //输出格式化后的json
//        System.out.println(jsonPrettyPrintString);

    }
}
