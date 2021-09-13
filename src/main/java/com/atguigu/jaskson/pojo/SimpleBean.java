package com.atguigu.jaskson.pojo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

/**
 * @Auther: LongYuan
 * @Date: 2021/8/24 0024 - 18:51
 * @Description: com.atguigu.jaskson.pojo
 * @version: 1.0
 */

@Data
@JacksonXmlRootElement(localName = "Bean")
public class SimpleBean {
    @JacksonXmlProperty(localName = "xData")
    private String x ;
    @JacksonXmlProperty(localName = "yData")
    private int y ;

    @JacksonXmlProperty(localName = "Cars")
    private Car cars;


}
