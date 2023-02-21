package com.zzut.pigbreeding.controller;

import com.zzut.pigbreeding.paho.message.IMessage;
import com.zzut.pigbreeding.paho.server.ConnectServer;
import com.zzut.pigbreeding.pojo.TestPojo;
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
            sendMessage.sendObjectMessage("dev",new TestPojo("lwl","lwl"),1);
            connectServer.retainMessage();
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
