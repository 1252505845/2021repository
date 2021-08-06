package com.atguigu.testJsonAndXml;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * @Auther: zhaoss
 * @Date: 2021/8/2 0002 - 08 - 02 - 14:46
 * @Description: com.atguigu.testJsonAndXml
 * @version: 1.0
 */


public class XmlTest  {

    public static void main(String[] args) {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", "zhangsan");
        jsonObject.put("age", "15");

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("username", "lisi");
        jsonObject2.put("age", "35");

        JSONArray jsonArray = new JSONArray();
        JSONObject dataJson = new JSONObject();

        jsonArray.add(jsonObject);
        jsonArray.add(jsonObject2);
        //jsonArray.add(jsonObject);
        dataJson.put("data", jsonArray);
        System.out.println(dataJson.toString());

        String xml = XmlJsonUtils.jsonToxml(dataJson.toString());
        System.out.println("xml:" + xml);
        String json = XmlJsonUtils.xmlTojson(xml);
        System.out.println("json:" + json);

        System.out.println("测试 ");
        System.out.println("第二次");


    }
}
