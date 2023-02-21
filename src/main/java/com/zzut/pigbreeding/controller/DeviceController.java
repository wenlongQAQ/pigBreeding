package com.zzut.pigbreeding.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzut.pigbreeding.common.Code;
import com.zzut.pigbreeding.common.R;
import com.zzut.pigbreeding.dto.DeviceDto;
import com.zzut.pigbreeding.pojo.device.Device;
import com.zzut.pigbreeding.pojo.device.SafeData;
import com.zzut.pigbreeding.service.DeviceService;
import com.zzut.pigbreeding.service.SafeDataService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private SafeDataService safeDataService;

    /**
     * 根据id获取设备信息
     * @param id
     * @return
     */
    @GetMapping("/getDeviceCommonById")
    public R<DeviceDto> getDeviceCommon(@RequestParam("id") Long id){
        Device item = deviceService.getById(id);

        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setDevice(item);
        LambdaQueryWrapper<SafeData> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(item.getId()!=null,SafeData::getDeviceId,item.getId());
        SafeData one = safeDataService.getOne(lambdaQueryWrapper);
        if (one!=null) {
            deviceDto.setSafeDataMin(one.getSafeDataMin());
            deviceDto.setSafeDataMax(one.getSafeDataMax());
        }
        return new R<DeviceDto>().packing(deviceDto,"查询成功", Code.GET_DEVICE_SUCCESS);
    }
    /**
     *  分页查询设备信息
     * @param page 当前页数
     * @param pageSize 每页数量
     * @return
     */
    @GetMapping("/page")
    public R<Page> getDevicePage(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize){
        Page<Device> deviceInfo = new Page<>(page,pageSize);
        Page<DeviceDto> deviceDtoPage = new Page<>();

        deviceService.page(deviceInfo);

        //对象拷贝 将分页相关的信息赋值给 DishDto的分页,因为 Dish的分页 无法在页面上展示菜品的分类
        BeanUtils.copyProperties(deviceInfo,deviceDtoPage,"records");
        //得到Dish数组
        List<Device> records = deviceInfo.getRecords();
        //根据Dish数组中的CategoryId查询CategoryName然后赋值给新的List
        List<DeviceDto> list = records.stream().map((item)->{
            DeviceDto deviceDto = new DeviceDto();

            LambdaQueryWrapper<SafeData> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(SafeData::getDeviceId,item.getId());
            SafeData one = safeDataService.getOne(lambdaQueryWrapper);
            if (item.getStatus().equals("1")) {
                item.setStatus("开启");
            }else {
                item.setStatus("关闭");
            }

            if (one!=null) {
                deviceDto.setSafeDataMin(one.getSafeDataMin());
                deviceDto.setSafeDataMax(one.getSafeDataMax());
            }

            deviceDto.setDevice(item);

            return deviceDto;
        }).collect(Collectors.toList());
        //然后设置DishDto的page的数据
        deviceDtoPage.setRecords(list);
        return new R<Page>().packing(deviceDtoPage,"",Code.GET_DEVICE_SUCCESS);
    }

    @PutMapping("/updateDevice")
    public R updateDeviceByEntity(@RequestBody DeviceDto entity){
        Device device = entity.getDevice();
        deviceService.updateById(device);
        LambdaQueryWrapper<SafeData> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SafeData::getDeviceId,device.getId());
        safeDataService.saveOrUpdate(new SafeData(device.getId(), null,entity.getSafeDataMin(),entity.getSafeDataMax()),lambdaQueryWrapper);
        return new R<>().packing(null,"success",Code.UPDATE_DEVICE_SUCCESS);

    }

}
