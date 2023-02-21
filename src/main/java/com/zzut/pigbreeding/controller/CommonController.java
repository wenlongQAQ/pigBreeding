package com.zzut.pigbreeding.controller;


import com.baomidou.mybatisplus.extension.api.R;

import com.zzut.pigbreeding.pojo.weather.Lives;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping("/common")
@RestController
@Slf4j
public class CommonController {
    @Value("${filepath}")
    private String path;

    @GetMapping("/getTodayWeather")
    public Lives getTodayWeather(){


        return null;
    }
    @GetMapping("/getDemoData")
    public List<Integer> getDemoData(){
        List<Integer> data = new ArrayList<>();
        data.add(3);
        data.add(5);
        data.add(7);
        data.add(8);
        data.add(22);
        return data;
    }

    @PostMapping("/uploadImg")
    public String upload(MultipartFile file){
        //file是一个临时文件 不转存 很快消失
        log.info(file.toString());
        //转存
        String originalFilename = file.getOriginalFilename();
        //获取文件后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        //使用UUID重新生成文件名,防止文件名称重复造成文件覆盖
        String fileName = UUID.randomUUID().toString() + suffix;
        try {
            File dir = new File(path);
            if (!dir.exists()) {
                //目录不存在 需要创建
                dir.mkdirs();
            }
            file.transferTo(new File(path+fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileName;
    }
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response) {
        //输入流 读取文件内容
        try {
            FileInputStream fileInputStream = new FileInputStream(path + name);
            ServletOutputStream outputStream = response.getOutputStream();
            int length = 0;
            byte[] bytes = new byte[1024];
            while ((length = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, length);
                outputStream.flush();
            }
            outputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
