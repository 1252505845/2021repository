package com.atguigu.uploadAndDownLoad;

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
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.Charset;
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
     * 发送multipartFile
     *
     * @param url           请求路径
     * @param file          MultipartFile流
     * @param fileParamName controller对应的接收名称
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




//    public static HttpResultDTO postMultipartFile(String url, MultipartFile file, String fileParamName,
//                                                  Map<String, String> headerParams,
//                                                  Map<String, String> otherParams) {
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        try {
//            String fileName = file.getOriginalFilename();
//            HttpPost httpPost = new HttpPost(url);
//            //添加header
//            if (headerParams != null) {
//                for (Map.Entry<String, String> e : headerParams.entrySet()) {
//                    httpPost.addHeader(e.getKey(), e.getValue());
//                }
//            }
//            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//            builder.setCharset(Charset.forName("utf-8"));
//            //加上此行代码解决返回中文乱码问题
//            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
//            //文件流处理
//            builder.addBinaryBody(fileParamName, file.getInputStream(), ContentType.MULTIPART_FORM_DATA, fileName);
//            if (otherParams != null) {
//                for (Map.Entry<String, String> e : otherParams.entrySet()) {
//                    //追加其他请求参数信息
//                    builder.addTextBody(e.getKey(), e.getValue());
//                }
//            }
//            HttpEntity entity = builder.build();
//            httpPost.setEntity(entity);
//            //执行提交
//            HttpResponse response = httpClient.execute(httpPost);
//            HttpEntity responseEntity = response.getEntity();
//            StatusLine statusLine = response.getStatusLine();
//            int status = statusLine.getStatusCode();
//            Header[] headers = response.getAllHeaders();
//            String body = EntityUtils.toString(responseEntity);
//            Map<String, String> headerMap = Maps.newHashMap();
//            if (ArrayUtils.isNotEmpty(headers)) {
//                for (Header header : headers) {
//                    headerMap.put(header.getName(), header.getValue());
//                }
//            }
//            return new HttpResultDTO(status, body, headerMap);
//
//        } catch (Exception e) {
//            //打印日志
//            log.error("postMultipartFile error，url:{},ex:{}", url, e.getMessage());
//        }
//        return null;
//    }

}
