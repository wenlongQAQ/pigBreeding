package com.zzut.pigbreeding.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzut.pigbreeding.common.R;
import com.zzut.pigbreeding.pojo.OrderBatch;
import com.zzut.pigbreeding.service.OrderBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderBatchController {
    @Autowired
    private OrderBatchService orderBatchService;

    @GetMapping("/page")
    public R page(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize){
        Page<OrderBatch> orderBatchPage = new Page<>(page, pageSize);
        return new R<>().packing(orderBatchService.page(orderBatchPage),"success",1);
    }
    @PostMapping
    public R addOrder(@RequestBody OrderBatch orderBatch){
        boolean save = orderBatchService.saveOrUpdate(orderBatch);
        return new R<>().packing("",save?"success":"error",save?1:0);
    }
    @GetMapping("/getById")
    public R getById(@RequestParam("id") Long id ){
        OrderBatch byId = orderBatchService.getById(id);
        return new R<>().packing(byId,"success",1);
    }
    @GetMapping("/getOrderNum")
    public R getOrderNum(){
        List<OrderBatch> list = orderBatchService.list();
        ArrayList<Long> longs = new ArrayList<>();
        for (OrderBatch orderBatch : list) {
            longs.add(orderBatch.getOrderNum());
        }
        return new R<>().packing(longs,"success",1);
    }

}
