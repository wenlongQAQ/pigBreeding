package com.zzut.pigbreeding.pojo.device;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class ScheduleDeviceTask {
    //任务名称
    private String taskName;
    //任务开启时间
    private int beginTime;
    // 任务持续时间(分钟)
    private int endTime;

    // 操作的设备名称
    private String deviceId;
    //操作的数据类型
    private String type;
    // 目标浓度 >0表示限制的浓度 <0表示不限制浓度 只按照时间执行
    private float aimData;
    // 1 开 0 关
    private boolean operate;
    //任务备注
    private String remarks;
    //任务id
    private Long id;


}
