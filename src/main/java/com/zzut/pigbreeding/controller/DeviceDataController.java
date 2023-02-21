package com.zzut.pigbreeding.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zzut.pigbreeding.common.Code;
import com.zzut.pigbreeding.common.R;
import com.zzut.pigbreeding.pojo.device.Device;
import com.zzut.pigbreeding.pojo.device.DeviceData;
import com.zzut.pigbreeding.service.DeviceDataService;
import com.zzut.pigbreeding.service.DeviceService;
import com.zzut.pigbreeding.service.SafeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deviceData")
public class DeviceDataController {
    @Autowired
    private DeviceDataService deviceDataService;
    @Autowired
    private DeviceService deviceService;
    @Value("${device.limit}")
    private String limit;
    @Value("${device.type.temperature}")
    private String temperatureType;
    @Autowired
    private SafeDataService safeDataService;



    @GetMapping("/getRecentDataByType")
    public R getTemperature(@RequestParam String type){
        LambdaQueryWrapper<DeviceData> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(DeviceData::getType,type)
                .eq(DeviceData::getDeviceStatus,1)
                .orderByDesc(DeviceData::getSaveTime)
                .last(limit);
        return new R<>().packing(deviceDataService.list(lambdaQueryWrapper),"成功", Code.GET_DEVICE_DATA_SUCCESS);
    }
    @GetMapping("/getDeviceDataById")
    public R getDeviceDataById(@RequestParam("id") Long id){
        LambdaQueryWrapper<Device> deviceLambdaQueryWrapper = new LambdaQueryWrapper<>();
        deviceLambdaQueryWrapper.eq(Device::getId,id);
        Device one = deviceService.getOne(deviceLambdaQueryWrapper);

        LambdaQueryWrapper<DeviceData> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        lambdaQueryWrapper.eq(DeviceData::getType,one.getType())
                .eq(DeviceData::getDeviceStatus,1)
                .orderByDesc(DeviceData::getSaveTime)
                .eq(DeviceData::getDeviceId,id)
                .last(limit);
        List<DeviceData> dataList = deviceDataService.list(lambdaQueryWrapper);
        return new R<>().packing(dataList,"成功",Code.GET_DEVICE_DATA_SUCCESS);
    }




}
