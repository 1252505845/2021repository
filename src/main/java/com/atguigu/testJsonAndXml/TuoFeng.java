package com.atguigu.testJsonAndXml;


import com.alibaba.fastjson.JSON;


import com.alibaba.fastjson.JSONObject;

/**
 * @Auther: LongYuan
 * @Date: 2021/9/10 0010 - 17:25
 * @Description: com.atguigu.testJsonAndXml
 * @version: 1.0
 */


public class TuoFeng {

    public static void main(String[] args) {
        String str="{\n" +
                "  \"Body\": {\n" +
                "    \"APP_HEAD\": {\n" +
                "      \"SYS_HEAD\": {\n" +
                "        \"SOURCE_TYPE\": \"namYYY\",\n" +
                "        \"BRANCH_ID\": \"UUU\"\n" +
                "      }\n" +
                "    },\n" +
                "    \"YYDS\": \"sass\",\n" +
                "    \"USER_NAME\": \"tom\",\n" +
                "    \"USER_AGE\": \"12\"\n" +
                "  }\n" +
                "}";
        JSONObject jsonObject = JSON.parseObject(str);
        String sys_head = jsonObject.getString("Body");
        String app_head = JSON.parseObject(sys_head).getString("APP_HEAD");
        String sys_head1 = JSON.parseObject(app_head).getString("SYS_HEAD");
        String SOURCE_TYPE = JSON.parseObject(sys_head1).getString("SOURCE_TYPE");
        System.out.println(sys_head);
        System.out.println(app_head);
        System.out.println(SOURCE_TYPE);


    }

}
