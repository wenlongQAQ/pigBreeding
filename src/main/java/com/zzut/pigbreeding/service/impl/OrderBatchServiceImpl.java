package com.zzut.pigbreeding.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzut.pigbreeding.mapper.OrderBatchMapper;
import com.zzut.pigbreeding.pojo.OrderBatch;
import com.zzut.pigbreeding.service.OrderBatchService;
import org.springframework.stereotype.Service;

@Service
public class OrderBatchServiceImpl extends ServiceImpl<OrderBatchMapper, OrderBatch> implements OrderBatchService {
}
