package com.atguigu.jaskson.pojo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

/**
 * @Auther: LongYuan
 * @Date: 2021/8/25 0025 - 14:25
 * @Description: com.atguigu.jaskson.pojo
 * @version: 1.0
 */


@Data
@JacksonXmlRootElement(localName = "Car")
public class Car  extends Bus{


    private  int num;
    private  int  year;

}
