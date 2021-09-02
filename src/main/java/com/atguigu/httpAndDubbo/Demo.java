package com.atguigu.httpAndDubbo;

/**
 * @Auther: LongYuan
 * @Date: 2021/9/1 0001 - 15:28
 * @Description: com.atguigu.httpAndDubbo
 * @version: 1.0
 */

/**
 *  httpclient转发get请求和post请求
 */
public class Demo {

    public static void main(String[] args) {
        System.setProperty("server.port", "9090");
        //直接发送get请求
        String response = HttpClientUtil.sendHttpGet("http://localhost:8080/hello?name=lisi&age=12");
        System.out.println("get返回---"+response);

        String requestJson="{\n" +
                "   \"message\": {\n" +
                "      \"head\": {\n" +
                "         \"courseStageId\": \"PcjbvAQ8h9KfZ8q6UZcw\",\n" +
                "         \"gradeId\": \"26vYkWDVjhivNno6Kbz7ZM\",\n" +
                "         \"code\": 1009430255,\n" +
                "         \"stageId\": \"go2Leq1wj5y8vuA5w7Azw\"\n" +
                "      }\n" +
                "   }\n" +
                "}";
        String postResponse = HttpClientUtil.sendHttpPostJson("http://localhost:8080/stringParameter", requestJson);
        System.out.println("post返回---"+postResponse);


    }

}
