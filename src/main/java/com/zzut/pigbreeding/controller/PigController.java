package com.zzut.pigbreeding.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzut.pigbreeding.common.R;
import com.zzut.pigbreeding.pojo.OrderBatch;
import com.zzut.pigbreeding.pojo.Pig;
import com.zzut.pigbreeding.service.OrderBatchService;
import com.zzut.pigbreeding.service.PigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/pig")
public class PigController {
    @Autowired
    private PigService pigService;
    @Autowired
    private OrderBatchService orderBatchService;
    @PostMapping
    public R addPig(@RequestBody Pig pig){
        if (pigService.save(pig)) {
            return new R<>().packing("","success",1);
        }else {
            return new R<>().packing("","error",0);
        }
    }
    @GetMapping("/page")
    public R getAllPig(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize){
        Page<Pig> pigPage = new Page<>(page, pageSize);
        Page<Pig> page1 = pigService.page(pigPage);
        List<Pig> records = page1.getRecords();
        for (Pig record : records) {
            Date birth = record.getBirth();
            Date date = new Date();
            if (birth!=null){
                Long a= (date.getTime() - birth.getTime())/1000/60/60/24/30;
                record.setAge(Math.toIntExact((a)));
            }
            else{
                record.setAge(-1);
            }
            if (record.getOrderId()!=null){
                record.setOrderNum(orderBatchService.getById(record.getOrderId()).getOrderNum());
            }


        }
        return new R<>().packing(page1,"success",1);
    }
    @DeleteMapping
    public R deletePig(@RequestParam("id") Long id){
        if (pigService.removeById(id)) {
            return new R<>().packing("","success",1);
        }else {
            return new R<>().packing("","error",0);
        }
    }
    @PutMapping
    public R updatePigInformation(@RequestBody Pig pig){
       pigService.updateById(pig);
       return new R<>().packing("","success",1);

    }
    @GetMapping("/getById")
    public R getPigById(@RequestParam("id") Long id){

        return new R<>().packing(pigService.getById(id),"success",1);
    }
    @PutMapping("/sale")
    public R salePig(@RequestBody Pig pig){
        if (pig.getOrderNum()!=null){
            LambdaQueryWrapper<OrderBatch> l = new LambdaQueryWrapper<>();
            l.eq(OrderBatch::getOrderNum,pig.getOrderNum());
            OrderBatch one = orderBatchService.getOne(l);
            Long id = one.getId();
            pig.setOrderId(id);
        }
        pigService.updateById(pig);
        return new R<>().packing("","success",1);

    }
}
