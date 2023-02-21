package com.zzut.pigbreeding.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzut.pigbreeding.pojo.device.SafeData;
import com.zzut.pigbreeding.mapper.SafeDataMapper;
import com.zzut.pigbreeding.service.SafeDataService;
import org.springframework.stereotype.Service;

@Service
public class SafeDataServiceImpl extends ServiceImpl<SafeDataMapper, SafeData> implements SafeDataService {
}
