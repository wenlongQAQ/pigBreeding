package com.zzut.pigbreeding.paho.callback.db;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zzut.pigbreeding.common.CheckData;
import com.zzut.pigbreeding.pojo.device.Device;
import com.zzut.pigbreeding.pojo.device.DeviceData;
import com.zzut.pigbreeding.service.DeviceDataService;
import com.zzut.pigbreeding.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeviceDBUtil {
    @Autowired
    private DeviceDataService deviceDataService;
    @Autowired
    private CheckData checkData;
    @Autowired
    private DeviceService deviceService;
    public void saveDeviceData(Device device){
        float data = device.getData();
        String deviceName = device.getName();
        String type = device.getType();
        DeviceData deviceData = new DeviceData();
        LambdaQueryWrapper<Device> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Device::getName,device.getName());
        Device one = deviceService.getOne(lambdaQueryWrapper);
        deviceData.setData(data);
        deviceData.setDeviceName(deviceName);
        deviceData.setDeviceId(one.getId());
        deviceData.setType(type);
        deviceData.setDeviceStatus(one.getStatus());
        deviceDataService.save(deviceData);
        System.out.println(checkData.checkWaringAndSave(one));


    }
}
