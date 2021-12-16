package com.atguigu.uploadAndDownLoad;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.XML;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: LongYuan
 * @Date: 2021/9/24 0024 - 10:03
 * @Description: com.atguigu.uploadAndDownLoad
 * @version: 1.0
 */

@Slf4j
public class HttpSendMultipartFile {


    /**
     * @RequestPart这个注解用在multipart/form-data表单提交请求的方法上。
     * 支持的请求方法的方式MultipartFile，属于Spring的MultipartResolver类。
     * 这个请求是通过http协议传输的。
     *
     * @RequestParam也同样支持multipart/form-data请求。
     * 他们最大的不同是，当请求方法的请求参数类型不再是String类型的时候。
     * @RequestParam适用于name-value “String”类型的请求域，
     * @RequestPart适用于复杂的请求域（像JSON，XML）例如：key = user, value = {“name”:“aaa”,“xx”:“xx”}。
     *
     * @RequestParam适用于name-valueString类型的请求域，
     * @RequestPart适用于复杂的请求域（像JSON，XML）
     * 比如上面的例子，前两个邮箱用户就是String类型的参数适用@RequestParam
     * ，后面时图片类型的也就是复杂的请求域用@RequestPart。
     *
     * 原文链接：https://blog.csdn.net/qq_41950447/article/details/113183067
     */
    @PostMapping("/upload")
    public String upload(
            @RequestParam("email") String email,//RequestParam将请求参数区域的数据映射到控制层方法的参数上
            @RequestParam("username") String username,
            @RequestPart("headerImg")MultipartFile headerImg,
            @RequestPart("photos") MultipartFile[] photos
    ) throws IOException {
        log.info("上传的信息：email={} ，username={}，headerImg={},photos={}",
                email, username, headerImg.getSize(), photos.length
        );
        return null;
    }



    /**
     * post请求发送multipartFile
     *
     * @param url           请求路径
     * @param file          MultipartFile流
     * @param
     * @param headerParams  追加的请求头信息
     * @param otherParams   其他请求参数
     * @return
     */
    public static HttpResultDTO postMultipartFile(String url, MultipartFile file,
                                                  Map<String, String> headerParams,
                                                  Map<String, String> otherParams) {
        String fileParamName="file";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            String fileName = file.getOriginalFilename();
            HttpPost httpPost = new HttpPost(url);
//            //添加header
//            if (headerParams != null) {
//                for (Map.Entry<String, String> e : headerParams.entrySet()) {
//                    httpPost.addHeader(e.getKey(), e.getValue());
//                }
//            }
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setCharset(Charset.forName("utf-8"));
            //加上此行代码解决返回中文乱码问题
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            //文件流处理
            builder.addBinaryBody(fileParamName, file.getInputStream(), ContentType.MULTIPART_FORM_DATA, fileName);
            if (otherParams != null) {
                for (Map.Entry<String, String> e : otherParams.entrySet()) {
                    //追加其他请求参数信息
                    builder.addTextBody(e.getKey(), e.getValue());
                }
            }
            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);
            //执行提交
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            StatusLine statusLine = response.getStatusLine();
            int status = statusLine.getStatusCode();
            Header[] headers = response.getAllHeaders();
            String body = EntityUtils.toString(responseEntity);
            Map<String, String> headerMap = Maps.newHashMap();
            if (ArrayUtils.isNotEmpty(headers)) {
                for (Header header : headers) {
                    headerMap.put(header.getName(), header.getValue());
                }
            }
            return new HttpResultDTO(status, body, headerMap);

        } catch (Exception e) {
            //打印日志
            log.error("postMultipartFile error，url:{},ex:{}", url, e.getMessage());
        }
        return null;
    }

    @Test
    public void test() throws Exception{
        File file=new File("C:\\Users\\long\\Desktop\\docker\\test.jpg");
        FileInputStream  fileInputStream=null;
        MockMultipartFile mockMultipartFile=null;
        try {
             fileInputStream=new FileInputStream(file);
             mockMultipartFile=new MockMultipartFile(file.getName(),fileInputStream);


        } catch (Exception e) {
            e.printStackTrace();
        }
        HashMap<String,Object> dataMap=new HashMap<String, Object>();
        dataMap.put("tran","1001");
        dataMap.put("file",mockMultipartFile);
        byte[] bytes = mockMultipartFile.getBytes();
        log.info("bytes:{}",bytes);

        OAPRequest<Object> oapRequest = OAPRequest.builder().data(dataMap).build();
        String string = JSON.toJSONString(oapRequest);
        log.info("string:{}",string);
        JSONObject jsonObject = JSONObject.parseObject(string);
        String dataJson = jsonObject.getString("data");
        JSONObject data = JSONObject.parseObject(dataJson);
        String fileData = jsonObject.getString("file");
        JSONObject fileJson = JSONObject.parseObject(fileData);



    }



}
