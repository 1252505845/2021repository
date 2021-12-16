package com.atguigu.Scheduling;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: LongYuan
 * @Date: 2021/10/9 0009 - 18:04
 * @Description: com.atguigu
 * @version: 1.0
 */


public enum WeatherType {
    SUNNY(1, "晴天"),
    CLOUDY(2, "多云"),
    PARTLY_CLOUDY(3, "少云"),
    LIGHT_RAIN(4, "小雨"),
    MODERATE_RAIN(5, "中雨"),
    HEAVY_RAIN(6, "大雨"),
    SHOWER(7, "阵雨"),
    THUNDER_SHOWER(8, "雷阵雨"),
    RAINSTORM(9, "暴雨"),
    SMOG(10, "雾霾"),
    FROST(11, "霜冻"),
    TYPHOON(12, "台风"),
    SNOWSTORM(13, "暴风雪"),
    HEAVY_STORM(14, "大雪"),
    MODERATE_SNOW(15, "中雪"),
    LIGHT_SNOW(16, "小雪"),
    HAIL(17, "冰雹");

    private WeatherType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private static Map<Integer, WeatherType> codeMap = new HashMap<>(23);

    static {
        for (WeatherType weatherType : values()) {
            codeMap.put(weatherType.getCode(), weatherType);
        }
    }

    private Integer code;
    private String desc;

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static WeatherType getByCode(Integer code) {
        return codeMap.get(code);
    }
}
