package com.zzut.pigbreeding.pojo.device;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString
public class DeviceDataWarning {
    private LocalDateTime warningTime;
    private String deviceName;
    private float warningData;
//    @TableLogic
    private int status;
    private Long id;
}
