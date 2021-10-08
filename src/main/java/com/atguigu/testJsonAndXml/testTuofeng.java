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
        //驼峰转换下划线
        String str="userName250";
        char[] chars = str.toCharArray();
        StringBuffer sb = new StringBuffer();
        //数字的话,第一个数字前要加下划线  后面的数字不加
        boolean flag=false;
        for (char c:chars){
            if (Character.isLowerCase(c) ||(Character.isDigit(c)&&flag)){
                sb.append(String.valueOf(c).toUpperCase());
            }else {
                if(Character.isDigit(c)&&!flag){
                    flag=true;
                }
                sb.append("_"+String.valueOf(c).toUpperCase());
            }
        }
        System.out.println(sb.toString());

    }

    public void test(){
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
