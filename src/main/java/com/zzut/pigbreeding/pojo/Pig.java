package com.zzut.pigbreeding.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.zzut.pigbreeding.service.OrderBatchService;
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
    //订单号
    @TableField(exist = false)
    private Long orderNum;
    //订单id
    private Long orderId;
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

    public void calculatePigAge(OrderBatchService orderBatchService){

        Date birth = this.getBirth();
        Date date = new Date();
        if (birth!=null){
            Long a= (date.getTime() - birth.getTime())/1000/60/60/24/30;
            this.setAge(Math.toIntExact((a)));
        }
        else{
            this.setAge(-1);
        }
        if (this.getOrderId()!=null){
            this.setOrderNum(orderBatchService.getById(this.getOrderId()).getOrderNum());
        }

    }
}
