package com.atguigu.jaskson.pojo;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Auther: LongYuan
 * @Date: 2021/9/8 0008 - 21:50
 * @Description: com.atguigu.jaskson.pojo
 * @version: 1.0
 */


public class JsonUtils {
     /**
           * 将驼峰转换为下划线加大写，例如：userName-->user_name
           *
           * @param
           * @return
           * @throws JsonProcessingException
           */
        public static String toUnderlineString(String str)  {
            char[] chars = str.toCharArray();
            StringBuffer sb=new StringBuffer();
            for (char c:chars){
                    if(Character.isLowerCase(c)){
                        sb.append(String.valueOf(c).toUpperCase());
                    }else{
                        sb.append("_"+String.valueOf(c).toUpperCase());
                    }
            }
            return sb.toString();
         }

    public static String toUnderlineJSONString(String json)  {
        Map<String,Object> dataMap = (Map) JSON.parse(json);
        Map<String,Object> dataUnderlineMap=new HashMap<>();

        Iterator<Map.Entry<String, Object>> it = dataMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            dataUnderlineMap.put(JsonUtils.toUnderlineString(entry.getKey())
                    ,entry.getValue());
        }
        String toJSONString = JSON.toJSONString(dataUnderlineMap);
        return toJSONString;
    }






}
