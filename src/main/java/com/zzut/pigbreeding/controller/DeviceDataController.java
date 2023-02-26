package com.zzut.pigbreeding.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

import java.util.ArrayList;
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
    public R getTemperature(@RequestParam("type") String type){
        LambdaQueryWrapper<DeviceData> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        List<DeviceData> results = new ArrayList<>();

//        for(int i = 0; i<24 ; i++){
//            Page<DeviceData> deviceDataPage = new Page<>(i, 720);
//            lambdaQueryWrapper.clear();
//            lambdaQueryWrapper.eq(DeviceData::getType,type)
//                    .eq(DeviceData::getDeviceStatus,1)
//                    .orderByDesc(DeviceData::getSaveTime)
//                    .last("limit " + i*720 + ",720");
//            List<DeviceData> list = deviceDataService.list(lambdaQueryWrapper);
//            if (list.size()!=0)
//                results.add(list.get(0));
//            else
//                return new R<>().packing(results,"成功", Code.GET_DEVICE_DATA_SUCCESS) ;
//        }
        lambdaQueryWrapper.eq(DeviceData::getType,type)
                    .eq(DeviceData::getDeviceStatus,1)
                    .orderByDesc(DeviceData::getSaveTime)
                    .last("limit 24");
        results = deviceDataService.list(lambdaQueryWrapper);

        return new R<>().packing(results,"成功", Code.GET_DEVICE_DATA_SUCCESS);
    }
    @GetMapping("/getDataByType")
    public R getDataByType(@RequestParam("type") String type){
        LambdaQueryWrapper<DeviceData> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(DeviceData::getType,type)
                .eq(DeviceData::getDeviceStatus,1)
                .orderByDesc(DeviceData::getSaveTime)
                .last("limit 1");
        return new R<>().packing(deviceDataService.getOne(lambdaQueryWrapper),"成功", Code.GET_DEVICE_DATA_SUCCESS);
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
