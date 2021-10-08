package com.atguigu.uploadAndDownLoad;

/**
 * @Auther: LongYuan
 * @Date: 2021/9/21 0021 - 16:22
 * @Description: com.atguigu.uploadAndDownLoad
 * @version: 1.0
 */


import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/image")
public class TestDownLoad {

    @RequestMapping(value = "/get",produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getImage() throws IOException {
        File file = new File("C:/Users/long/Desktop/test/test.jpg");
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes, 0, inputStream.available());
        return bytes;
    }


//    @RequestMapping(value = "/get2",produces = MediaType.IMAGE_JPEG_VALUE)
//    @ResponseBody
//    public BufferedImage getImage2() throws IOException {
//        return ImageIO.read(new FileInputStream(new File("C:/Users/long/Desktop/test/test.jpg")));
//    }

    //@RequestMapping("/download")
    public String downLoadFile(String filename,HttpServletResponse response)  {
        String filePath = "F:/demo" ;
        //String filename="test.jpg";
        File file = new File(filePath + "/" + filename);
//        if(file.exists()){ //判断文件父目录是否存在
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
//             response.setContentType("application/force-download");
        try {
            response.setHeader("Content-Disposition", "attachment;fileName=" +   java.net.URLEncoder.encode(filename,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;

            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }finally {
                try {
                    bis.close();
                    fis.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

//        }
        return null;
    }

    @RequestMapping("/download2")
    public String downLoad2(HttpServletResponse response){
        List<String> fileNameList = getFileNameList();
        for (String fileName:fileNameList){
            downLoadFile(fileName,response);
        }


        return  null;
    }
        public List<String> getFileNameList(){
            File file = new File("F:/demo");
            List<String> list1 = new ArrayList<String>();
            File[] files = file.listFiles();
            for (File f1 : files){
                System.out.println( f1.getName());//获取全部路径的名称，放入集合
                list1.add(f1.getName());
            }
           return  list1;
        }

}