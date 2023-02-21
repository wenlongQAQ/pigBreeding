package com.zzut.pigbreeding.pojo.device;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceData {
    private float data;
    private String deviceName;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime saveTime;

    private String type;
    private long deviceId;
    private Long id;
    private String deviceStatus;
}
