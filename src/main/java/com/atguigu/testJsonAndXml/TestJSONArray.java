package com.atguigu.testJsonAndXml;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.imageio.stream.FileImageOutputStream;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * @Auther: LongYuan
 * @Date: 2021/11/2 0002 - 12:28
 * @Description: com.atguigu.testJsonAndXml
 * @version: 1.0
 */


public class TestJSONArray {
    public static void main(String[] args) {

        String file=  "C:\\Users\\long\\Desktop\\deno\\2021.png";
        byte[] bytes = getBytes(file);

        Map<String,Object> map=new HashMap<>();
        map.put("fileName","name");
        map.put("bytes",bytes);
        String encode = Base64.getEncoder().encodeToString(bytes);
        System.out.println("encode--"+encode);
        System.out.println("bytes"+bytes);
        List<Map<String,Object>> list=new ArrayList<>();
        list.add(map);
        String toJSONString = JSON.toJSONString(list);
        System.out.println(toJSONString);
        JSONArray jsonArray = JSONArray.parseArray(toJSONString);
        System.out.println(jsonArray);
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        String fileName = jsonObject.getString("fileName");
        String bytes2 = jsonObject.getString("bytes");
        byte[] decode = Base64.getDecoder().decode(bytes2);


        System.out.println("decode"+decode);
        byte2Image(decode);

    }



    /**
     * 将文件转为byte[]
     * @param filePath 文件路径
     * @return
     */
    public static byte[] getBytes(String filePath){
        File file = new File(filePath);
        ByteArrayOutputStream out = null;
        try {
            FileInputStream in = new FileInputStream(file);
            out = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int i = 0;
            while ((i = in.read(b)) != -1) {

                out.write(b, 0, b.length);
            }
            out.close();
            in.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        byte[] s = out.toByteArray();
        return s;

    }

    /**
     * 字节数组写入文件
     *
     * @param data
     */
     public static void byte2Image(byte[]data){

         try {
             FileImageOutputStream imageOut=new FileImageOutputStream(
                     new File("C:\\Users\\long\\Desktop\\deno\\gen\\gen2.jpg"));
             imageOut.write(data,0,data.length);
             imageOut.close();
             System.out.println("end");

         } catch (IOException e) {
             e.printStackTrace();
         }


     }

}
