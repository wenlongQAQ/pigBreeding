package com.zzut.pigbreeding.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzut.pigbreeding.common.R;
import com.zzut.pigbreeding.pojo.OrderBatch;
import com.zzut.pigbreeding.pojo.Pig;
import com.zzut.pigbreeding.service.OrderBatchService;
import com.zzut.pigbreeding.service.PigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/pig")
@Slf4j
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
    public R getAllPig(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize,@RequestParam("status") Integer status, @RequestParam(value = "orderNum",required = false) String orderNum){
        LambdaQueryWrapper<Pig> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Pig::getStatus,status);
        if (orderNum!=""){
            log.info(orderNum);
            OrderBatch one = orderBatchService.getOne(new LambdaQueryWrapper<OrderBatch>().eq(OrderBatch::getOrderNum, orderNum));
            if (one!=null){
                lambdaQueryWrapper.eq(Pig::getOrderId,one.getId());
                log.info(one.getId().toString());
            }else {
                return new R<>().packing(new Page<>(),"success",1);
            }
        }
        Page<Pig> pigPage = new Page<>(page, pageSize);
        Page<Pig> page1 = pigService.page(pigPage,lambdaQueryWrapper);
        List<Pig> records = page1.getRecords();

        for (Pig record : records) {
            record.calculatePigAge(orderBatchService);
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

    /**
     *  <=1 1
     *  1-3 2
     *  3-6 3
     *  6-12 4
     *  >=12 5
     * @return
     */
    @GetMapping("/ageList")
    public R getPigAgeList(){
        Integer level1 = 0;
        Integer level2 = 0;
        Integer level3 = 0;
        Integer level4 = 0;
        Integer level5 = 0;
        for (Pig pig : pigService.list()) {
            pig.calculatePigAge(orderBatchService);
            if (pig.getAge() > 1){
                if (pig.getAge()>3){
                    if (pig.getAge()>6){
                        if (pig.getAge()>=12){
                            level5++;
                        }else {
                            level4++;
                        }
                    }else {
                        level3++;
                    }
                }else {
                    level2++;
                }
            }else {
                level1++;
            }
        }

        Map<String,Integer> result = new HashMap<>();
        result.put("level1",level1);
        result.put("level2",level2);
        result.put("level3",level3);
        result.put("level4",level4);
        result.put("level5",level5);

        return new R<>().packing(result,"成功",1);
    }
}
