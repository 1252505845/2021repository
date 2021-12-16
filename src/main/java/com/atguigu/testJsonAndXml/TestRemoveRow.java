package com.atguigu.testJsonAndXml;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Set;

/**
 * @Auther: LongYuan
 * @Date: 2021/11/23 0023 - 9:45
 * @Description: com.atguigu.testJsonAndXml
 * @version: 1.0
 */


public class TestRemoveRow {
    public static void main(String[] args) {
           String json="{\n" +
                   "  \"message\": {\n" +
                   "    \"Sys_Head\": {\n" +
                   "      \"row\": {\n" +
                   "        \"SOURCE_TYPE\": \"namYYY\",\n" +
                   "        \"BRANCH_ID\": \"UUU\"\n" +
                   "      }\n" +
                   "    },\n" +
                   "    \"Body\": {\n" +
                   "      \"row\": [\n" +
                   "        { \"yyDs\": \"23\" },\n" +
                   "        { \"yyDs\": \"34\" }\n" +
                   "      ]\n" +
                   "    },\n" +
                   "    \"THIS_YEAR\": \"2021\",\n" +
                   "    \"USER_NAME\": \"sdNa\",\n" +
                   "    \"AGE\": \"12\"\n" +
                   "  }\n" +
                   "}";
        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONObject jsonObject1 = jsonRemoveParams(jsonObject, "row");
        String string = JSONObject.toJSONString(jsonObject1);
        System.out.println(string);

    }


    /**
     * JSON去除指定属性(属性值，数组中，对象中，List中)
     * @param jsonObject 需处理JSON
     * @param removeParams 去除属性名称
     * @return
     */
    public static JSONObject jsonRemoveParams(JSONObject jsonObject, String removeParams){
        // 获取所有一级Key
        Set<String> keySet = jsonObject.keySet();
        // 通过一级Key遍历
        for (String key : keySet) {
            JSONArray jsonArray;
            try {
                // 将一级值转换为数组
                jsonArray = (JSONArray) jsonObject.get(key);
            } catch (Exception e) {
                // 转换失败，说明一级值不为数组
                try {
                    // 尝试将一级值转换为对象
                    JSONObject jsonObject1 = (JSONObject) jsonObject.get(key);
                    // 删除对象中属性
                    jsonObject1.remove(removeParams);
                } catch (Exception ex) {
                    // 转换失败，说明一级值为常量，循环结束
                    continue;
                }
                // 一级值不为数组，循环结束
                continue;
            }
            // 一级值为数组，遍历List行去除
            for (int i = 0; i < jsonArray.size(); i++) {
                try {
                    // 尝试直接去除List中的对象值
                    ((JSONObject) jsonArray.get(i)).remove(removeParams);
                } catch (Exception e) {
                    // 去除失败，说明此List为数组，进行数组去除值
                    jsonArray = (JSONArray) arrayDelete(removeParams, jsonArray);
                }
            }
        }
        return jsonObject;
    }


    /***
     * 删除数组中指定元素
     * @param removeParams 需要删除的元素
     * @param list  原始数组
     * @return
     */
    private static List arrayDelete(String removeParams, List list) {
        for (int i = 0; i < list.size(); i++) {
            if (removeParams.equals(list.get(i))) {
                list.remove(i);
            }
        }
        return list;
    }

}
