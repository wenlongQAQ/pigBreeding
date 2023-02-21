package com.zzut.pigbreeding.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Pig {
    private Long id;
    //姓名
    private String name;
    //性别
    private String gender;
    //猪出生日期
    private Date birth;
    //年龄 月份
    private Integer age;
    //登记该猪的时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime saveTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    private String description;
    //猪照片的名称
    private String imageName;
}
