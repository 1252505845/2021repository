package com.atguigu.testJsonAndXml;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auther: LongYuan
 * @Date: 2021/9/10 0010 - 15:46
 * @Description: com.atguigu.testJsonAndXml
 * @version: 1.0
 */


public class testTuofeng {
    public static void main(String[] args) {
        String str = "ASSET_TYPE_CODE";
        str = str.toLowerCase();
        final StringBuffer sb = new StringBuffer();
        Pattern p = Pattern.compile("_(\\w)");
        Matcher m = p.matcher(str);
        while (m.find()){
            m.appendReplacement(sb,m.group(1).toUpperCase());
        }
        m.appendTail(sb);
        System.out.println(sb.toString());
    }
}
