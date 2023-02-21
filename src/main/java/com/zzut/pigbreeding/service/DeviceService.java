package com.zzut.pigbreeding.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzut.pigbreeding.dto.DeviceDto;
import com.zzut.pigbreeding.pojo.device.Device;

import java.util.List;

public interface DeviceService extends IService<Device> {
    @Override
    boolean saveOrUpdate(Device entity);
    List<DeviceDto> addSafeData(String type);


}
