package com.zzut.pigbreeding.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Pig {
    //姓名
    private String name;
    //性别
    private String gender;
    //猪出生日期
    private LocalDateTime birth;
    //年龄 月份
    private Integer age;
    //登记该猪的时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime saveTime;
    //猪照片的名称
    private String imageName;
}
