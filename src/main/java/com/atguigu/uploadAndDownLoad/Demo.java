package com.atguigu.uploadAndDownLoad;


import com.atguigu.testJsonAndXml.XmlJsonUtils;

import java.util.ArrayList;
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
        String str ="{\"data\": {\"row\": {\"userInfoData\": {\"row\": [{ \"age\": \"1\" },{ \"age\": \"66\" }]},\"perInfoData\": {\"row\": { \"perage\": \"1\" }}}}}";

        String jsonToxml = XmlJsonUtils.jsonTo2Xml(str);
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
