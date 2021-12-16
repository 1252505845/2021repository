package com.atguigu.uploadAndDownLoad;

import lombok.Builder;
import lombok.Data;

/**
 * @Auther: LongYuan
 * @Date: 2021/10/15 0015 - 15:22
 * @Description: com.atguigu.uploadAndDownLoad
 * @version: 1.0
 */

@Data
@Builder
public class OAPResultVO<T> {

    private  String appId;


    private  T data;
}
