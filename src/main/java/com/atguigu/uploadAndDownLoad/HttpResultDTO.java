package com.atguigu.uploadAndDownLoad;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
 * @Auther: LongYuan
 * @Date: 2021/9/24 0024 - 10:07
 * @Description: com.atguigu.uploadAndDownLoad
 * @version: 1.0
 */


@Data
@AllArgsConstructor
public class HttpResultDTO {

    /**
     * 返回的状态码
     */
    private int status;

    /**
     * 返回的数据信息
     */
    private String body;

    /**
     * 返回的头信息
     */
    private Map<String, String> header;
}

