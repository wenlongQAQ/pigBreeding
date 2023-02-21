package com.zzut.pigbreeding.pojo.weather;

import lombok.Data;
import lombok.ToString;

@Data

public class Lives {
    private String province;
    private String city;
    private String weather;
    private float temperature;
    private String winddirection;
    private float humidity;
    private float temperature_float;
    private float humidity_float;

    @Override
    public String toString() {
        return "Lives{" +
                "省份='" + province + '\'' +
                ", 城市='" + city + '\'' +
                ", 天气='" + weather + '\'' +
                ", 温度=" + temperature +
                ", 风向='" + winddirection + '\'' +
                ", 湿度=" + humidity +
                ", 温度浮动=" + temperature_float +
                ", 湿度浮动=" + humidity_float +
                '}';
    }
}
