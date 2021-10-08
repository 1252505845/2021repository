package com.atguigu.uploadAndDownLoad;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: LongYuan
 * @Date: 2021/9/23 0023 - 9:54
 * @Description: com.atguigu.uploadAndDownLoad
 * @version: 1.0
 */


public class Test {

//    Calendar c = Calendar.getInstance();
    public static void main(String[] args) {
        URL resource=Test.class.getResource("/test.jpg");
        System.out.println(resource.getPath());
        File file = new File(resource.getPath());
//        File file = new File("C:\\Users\\long\\Desktop\\test\\test.jpg");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            MockMultipartFile mockMultipartFile = new MockMultipartFile(file.getName(),fileInputStream);
            System.out.println(mockMultipartFile.getOriginalFilename());
            System.out.println(mockMultipartFile.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

//    public static Date parse(String strDate)  {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//        try {
//            return sdf.parse(strDate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

}
