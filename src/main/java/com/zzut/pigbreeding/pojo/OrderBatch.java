package com.zzut.pigbreeding.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class OrderBatch {

    private Long id;
    // 订单号
    private Long orderNum;
    private String orderImageName;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime saveTime;
    // 订单备注
    private String description;
    private Date successTime;
}
