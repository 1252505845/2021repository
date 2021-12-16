package com.atguigu.uploadAndDownLoad;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.testJsonAndXml.XmlJsonUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Auther: LongYuan
 * @Date: 2021/9/24 0024 - 18:00
 * @Description: com.atguigu.uploadAndDownLoad
 * @version: 1.0
 */


public class Demo {

    public static void main(String[] args) {
//        String str="{\"data\": {\"userInfoData\": {\"row\": { \"age\": \"1\"}},\"perInfoData\": {\"row\": { \"perage\": \"1\" }}}}";
//        String str ="{\"data\": {\"row\": {\"userInfoData\": {\"row\": [{ \"age\": \"1\" },{ \"age\": \"66\" }]},\"perInfoData\": {\"row\": { \"perage\": \"1\" }}}}}";
            String str="{\n" +
                    "  \"data\": {\n" +
                    "    \"appHead\": {\n" +
                    "      \"yuyu\": \"878\",\n" +
                    "      \"tutu\": \"877\",\n" +
                    "      \"suyu\": \"78\"\n" +
                    "    },\n" +
                    "    \"userInfoData\": {\n" +
                    "      \"row\": [\n" +
                    "        { \"age\": \"1\" },\n" +
                    "        { \"age\": \"66\" }\n" +
                    "      ]\n" +
                    "    },\n" +
                    "    \"perInfoData\": {\n" +
                    "      \"row\": { \"perage\": \"1\" }\n" +
                    "    }\n" +
                    "  }\n" +
                    "}";

        JSONObject jsonObject = JSON.parseObject(str);
        String data = jsonObject.getString("data");
        HashMap hashMap = JSON.parseObject(data, HashMap.class);
        System.out.println("hashMap---"+hashMap);
        hashMap.remove("appHead");
        String string = JSONObject.toJSONString(hashMap);

        System.out.println("jsonObject2==========="+string);
        String jsonToxml = XmlJsonUtils.jsonTo2Xml(str.toString());
        System.out.println(jsonToxml.trim());

        //         str = str.trim();
//        System.out.println(str);
//         str = str.replace("{\"row\":[", "[").replace("]}", "]");
//        System.out.println("str"+str);
//        String s = removeRow(str, "{\"row\":");
//        System.out.println(s);
//        List<Integer> index = getIndex(str, "\"row\": {");
//         str = str.replace("{\"row\":", "");
//        StringBuilder myName = new StringBuilder(str);
//        for(Integer i:index){
//            int del = str.indexOf("}",i);
//            myName.delete(del,del+1);
//        }
//        System.out.println(myName.toString());



    }



    public static String removeRow(String strings, String str){
        List<Integer> list=new ArrayList<>();
        int flag=0;
        String firstStr=strings;
        strings=strings.trim();
        while (strings.indexOf(str)!=-1){
            //截取包含自身在内的前边部分
            String aa= strings.substring(0,strings.indexOf(str)+str.length());
            flag=flag+aa.length();
            list.add(flag-str.length());
            strings=strings.substring(strings.indexOf(str)+str.length());
        }
        System.out.println(strings);
        firstStr = firstStr.replace(str, "");
        System.out.println(firstStr);
        StringBuilder myStr = new StringBuilder(firstStr);
        for(Integer i:list){
            int del = firstStr.indexOf("}",i);
            myStr.delete(del,del+1);
        }
        return myStr.toString();
    }

}
