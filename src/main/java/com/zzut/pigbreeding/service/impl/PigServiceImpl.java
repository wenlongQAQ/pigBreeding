package com.zzut.pigbreeding.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzut.pigbreeding.mapper.PigMapper;
import com.zzut.pigbreeding.pojo.Pig;
import com.zzut.pigbreeding.service.PigService;
import org.springframework.stereotype.Service;

@Service
public class PigServiceImpl extends ServiceImpl<PigMapper, Pig> implements PigService {
}
