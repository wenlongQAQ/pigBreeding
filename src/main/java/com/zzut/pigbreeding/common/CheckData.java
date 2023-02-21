package com.zzut.pigbreeding.common;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zzut.pigbreeding.pojo.device.Device;
import com.zzut.pigbreeding.pojo.device.DeviceDataWarning;
import com.zzut.pigbreeding.pojo.device.SafeData;
import com.zzut.pigbreeding.service.DeviceDataWarningService;
import com.zzut.pigbreeding.service.SafeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CheckData {
    @Autowired
    private SafeDataService safeDataService;

    @Autowired
    private DeviceDataWarningService deviceDataWarningService;
    public DeviceDataWarning checkWaringAndSave(Device device){
        float data = device.getData();
        Long id = device.getId();

        LambdaQueryWrapper<SafeData> safeDataLambdaQueryWrapper = new LambdaQueryWrapper<>();
        safeDataLambdaQueryWrapper.eq(SafeData::getDeviceId,id);
        SafeData one = safeDataService.getOne(safeDataLambdaQueryWrapper);
        DeviceDataWarning deviceDataWarning = new DeviceDataWarning();
        if (data>one.getSafeDataMin()&&data< one.getSafeDataMax()){
            return null;
        }else {
            deviceDataWarning.setWarningData(data);
            deviceDataWarning.setWarningTime(LocalDateTime.now());
            deviceDataWarning.setDeviceName(device.getName());
            deviceDataWarningService.save(deviceDataWarning);
        }
        return deviceDataWarning;
    }
}
