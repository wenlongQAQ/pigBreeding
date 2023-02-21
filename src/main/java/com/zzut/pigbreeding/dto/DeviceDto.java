package com.zzut.pigbreeding.dto;

import com.zzut.pigbreeding.pojo.device.Device;
import lombok.Data;

@Data
public class DeviceDto {
    private Device device;
    private float safeDataMin;
    private float safeDataMax;
}
