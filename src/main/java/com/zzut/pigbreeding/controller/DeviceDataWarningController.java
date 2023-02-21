package com.zzut.pigbreeding.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzut.pigbreeding.common.R;
import com.zzut.pigbreeding.pojo.device.DeviceDataWarning;
import com.zzut.pigbreeding.service.DeviceDataWarningService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warning")
@Slf4j
public class DeviceDataWarningController {
    @Autowired
    private DeviceDataWarningService warningService;
    @GetMapping("/getNum")
    public Integer getWarningNum(){
        LambdaQueryWrapper<DeviceDataWarning> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(DeviceDataWarning::getStatus,1);
        return warningService.count(lambdaQueryWrapper);
    }
//    @GetMapping("/getAllWarnings")
//    public R getAllWarnings(){
//        LambdaQueryWrapper<DeviceDataWarning> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        List<DeviceDataWarning> list = warningService.list(lambdaQueryWrapper);
//        return new R<>().packing(list,"成功",1);
//    }
    @DeleteMapping ()
    public R removeWarning(@RequestParam List<Long> ids){
        ids.forEach(System.out::println);
        for (Long id : ids) {
            DeviceDataWarning byId = warningService.getById(id);
            if (byId.getStatus()==0)
                warningService.removeById(id);
            else {
                byId.setStatus(0);
                warningService.updateById(byId);
            }
        }
        return new R<>().packing(null,"成功",1);
    }

    @GetMapping("/page")
    public R getPage(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize,@RequestParam Integer status){
        Page<DeviceDataWarning> warningPage = new Page<>(page,pageSize);
        Page<DeviceDataWarning> page1;
        LambdaQueryWrapper<DeviceDataWarning> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(DeviceDataWarning::getStatus,status);
        page1 = warningService.page(warningPage,lambdaQueryWrapper);

        return new R<>().packing(page1,"查询成功",1);
    }
}
