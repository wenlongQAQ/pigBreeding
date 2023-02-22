package com.zzut.pigbreeding.pojo.device;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Device {
    // 目前的数据
    private float data;
    // 传感器的名称 -- 自定义
    private String name;
    // 传感器状态 1 - 在线 0 - 异常/离线
    private String  status;
    // 传感器类型
    private String type;
    // 得到数据的时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime saveTime;
    private Long id;

//    private float safeMin;
//    private float sageMax;

}
