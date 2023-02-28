package com.zzut.pigbreeding.controller;

import com.zzut.pigbreeding.paho.message.IMessage;
import com.zzut.pigbreeding.paho.server.ConnectServer;
import com.zzut.pigbreeding.pojo.TestPojo;
import com.zzut.pigbreeding.pojo.device.Device;
import com.zzut.pigbreeding.pojo.device.DeviceData;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private IMessage sendMessage;
    @Autowired
    private ConnectServer connectServer;
    @GetMapping("/sendMessage")
    public String testSendMessage(){
        try {
            connectServer.retainMessage();
//            Device deviceData = new Device();
//            deviceData.setData(17.8f);
//            deviceData.setName("dht11_temp");
//            deviceData.setType("temp");
//            deviceData.setStatus("1");
//            for (int i = 0 ; i < (24 * 720);i++)
//                sendMessage.sendObjectMessage("testtopic/1111",deviceData,1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @GetMapping("/testHot")
    public String testHot(){

        return "456";
    }

}
