package com.zzut.pigbreeding.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzut.pigbreeding.pojo.device.SafeData;
import com.zzut.pigbreeding.dto.DeviceDto;
import com.zzut.pigbreeding.mapper.DeviceMapper;
import com.zzut.pigbreeding.pojo.device.Device;
import com.zzut.pigbreeding.pojo.device.DeviceData;
import com.zzut.pigbreeding.service.DeviceService;
import com.zzut.pigbreeding.service.SafeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements DeviceService {

    @Autowired
    private SafeDataService safeDataService;
    @Value("${device.limit}")
    private String limit;
    @Override
    public List<DeviceDto> addSafeData(String type) {
        LambdaQueryWrapper<DeviceData> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(type!=null,DeviceData::getType,type)
                .eq(DeviceData::getDeviceStatus,1)
                .orderByDesc(DeviceData::getSaveTime)
                .last(limit);
        List<DeviceDto> result = super.list().stream().map(item -> {
            DeviceDto deviceDto = new DeviceDto();
            deviceDto.setDevice(item);
            LambdaQueryWrapper<SafeData> safeDataLambdaQueryWrapper = new LambdaQueryWrapper<>();

            safeDataLambdaQueryWrapper.eq(SafeData::getDeviceId, item.getId());
            SafeData one = safeDataService.getOne(safeDataLambdaQueryWrapper);
            if (one!=null){
                deviceDto.setSafeDataMin(one.getSafeDataMin());
                deviceDto.setSafeDataMax(one.getSafeDataMax());
            }
            return deviceDto;
        }).collect(Collectors.toList());
        return result;
    }

    @Override
    public boolean saveOrUpdate(Device entity) {
        System.out.println(0);
        LambdaQueryWrapper<Device> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Device::getName,entity.getName());
        Device list = super.getOne(lambdaQueryWrapper);
        if (entity.getName()!=null){
            if (list!=null) {
                super.update(entity,lambdaQueryWrapper);
                System.out.println(1);
            }else {
                super.save(entity);
                System.out.println(2);
            }
        }

        return true;
    }
}
