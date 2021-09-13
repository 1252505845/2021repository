package com.atguigu.testJsonAndXml;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.bag.SynchronizedSortedBag;


import java.util.Set;

/**
 * @Auther: LongYuan
 * @Date: 2021/9/10 0010 - 15:54
 * @Description: com.atguigu.testJsonAndXml
 * @version: 1.0
 */


public class JsonUtils {

    public final static void convert(Object json) {
        if (json instanceof JSONArray) {
            JSONArray arr = (JSONArray) json;
            for (Object obj : arr) {
                convert(obj);
            }
        } else if (json instanceof JSONObject) {
            JSONObject jo = (JSONObject) json;
            Set<String> keys = jo.keySet();
            String[] array = keys.toArray(new String[keys.size()]);
            for (String key : array) {
                Object value = jo.get(key);
                String[] key_strs = key.split("_");
                if (key_strs.length > 1) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < key_strs.length; i++) {
                        String ks = key_strs[i];
                        if (!"".equals(ks)) {
                            if (i == 0) {
                                sb.append(String.valueOf(ks).toLowerCase());
                            } else {//下划线后面的字段首字母大写  后面小写
                                char[] ksChars = ks.toCharArray();
                                for (int ci=0;ci<ksChars.length;ci++){
                                    char ksChar = ksChars[ci];
                                    if(ci==0){//下划线后面的字段首字母大写  后面小写
                                        sb.append(String.valueOf(ksChar).toUpperCase());
                                    }else{
                                        sb.append(String.valueOf(ksChar).toLowerCase());
                                    }
                                }

                            }
                        }
                    }
                    jo.remove(key);
                    jo.put(String.valueOf(sb), value);
                }else{
                    //说明没有下划线  可以直接转大写
                    jo.remove(key);
                    jo.put(String.valueOf(key).toLowerCase(), value);
                }

                convert(value);
            }
        }
    }

    public final static Object convert(String json) {
        Object obj = JSON.parse(json);
        convert(obj);
        return obj;
    }

    public static void main(String[] args) {
        String str ="{\n" +
                "  \"Body\": {\n" +
                "    \"APP_HEAD\": {\n" +
                "      \"SYS_HEAD\": {\n" +
                "        \"SOURCE_TYPE\": \"namYYY\",\n" +
                "        \"BRANCH_ID\": \"UUU\"\n" +
                "      }\n" +
                "    },\n" +
                "    \"YYDS\": \"sass\",\n" +
                "    \"USER_NAME_TAF\": \"tom\",\n" +
                "    \"USER_AGE\": \"12\"\n" +
                "  }\n" +
                "}";
        String json1 = convert(str).toString();
        System.out.println(json1);

//        String[] s = "key".split("_");
//        System.out.println(s.length);
    }
}
