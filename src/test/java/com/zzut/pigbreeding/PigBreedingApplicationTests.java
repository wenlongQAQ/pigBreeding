package com.zzut.pigbreeding;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zzut.pigbreeding.common.Code;
import com.zzut.pigbreeding.common.R;
import com.zzut.pigbreeding.paho.message.IMessage;
import com.zzut.pigbreeding.paho.server.ConnectServer;
import com.zzut.pigbreeding.pojo.OrderBatch;
import com.zzut.pigbreeding.pojo.PigPrice;
import com.zzut.pigbreeding.pojo.device.Device;

import com.zzut.pigbreeding.pojo.TestPojo;

import com.zzut.pigbreeding.pojo.device.DeviceData;
import com.zzut.pigbreeding.pojo.weather.Lives;
import com.zzut.pigbreeding.pojo.weather.LivesDto;
import com.zzut.pigbreeding.service.DeviceDataService;
import com.zzut.pigbreeding.service.DeviceService;


import com.zzut.pigbreeding.service.OrderBatchService;
import com.zzut.pigbreeding.util.HtmlParseUtil;
import com.zzut.pigbreeding.util.SendRequestWeatherUtil;
import org.eclipse.paho.client.mqttv3.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class PigBreedingApplicationTests {

    @Autowired
    private IMessage sendMessage;
    @Autowired
    private MqttClient sampleClient;

    @Autowired
    private ConnectServer connectServer;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private HtmlParseUtil htmlParseUtil;


    @Test
    void contextLoads() throws MqttException {
        String topic = "testtopic";
        String content = "hello,world";
        int qos = 1;
        System.out.println("connected.");
        // 创建消息
        MqttMessage message = new MqttMessage(content.getBytes());
        // 设置消息的服务质量
        message.setQos(qos);
        // 发布消息
        MqttTopic mqttTopic = sampleClient.getTopic(topic);

        if (mqttTopic == null) {
            System.out.println("topic not exist");
        }
        MqttDeliveryToken token;

        token = mqttTopic.publish(message);
        token.waitForCompletion();

        System.out.println("已经发送成功:" + token.isComplete());

        System.out.println("publishing msg to " + topic + ":" + message);
        //sampleClient.publish(topic, message);
        sampleClient.subscribe("testtopic/#");
        // 断开连接
        sampleClient.disconnect();
        // 关闭客户端
        sampleClient.close();
    }
    @Test
    void testSendObjectMessage(){

        String topic = "dev/1";
        try {
            sendMessage.sendObjectMessage(topic,new TestPojo("lwl","lwl"),1);
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testRetainMessage(){
        connectServer.retainMessage();
    }

    @Test
    void testMetaObjectHandler(){

    }
    @Test
    void testSaveDevice(){
        Device device = new Device();
        device.setName("室外二氧化碳");
        device.setType("co2");
        device.setData(1.12f);

        System.out.println(JSON.toJSONString(device));
        deviceService.save(device);

    }
    @Test
    void testScheduledTask() {

    }
    @Test
    void test() throws IOException {

    }
    @Test
    void testGetPigPrice(){
        try {
            List<PigPrice> pigPrice = htmlParseUtil.getPigPrice();
            for (PigPrice price : pigPrice) {
                System.out.println(price);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void testLocalDateTime(){
        LocalDateTime of = LocalDateTime.of(2022, 11, 21, 11, 12);
        LocalDateTime now = LocalDateTime.now();
        Duration between = Duration.between(of, now);
        System.out.println(between.toDays());
    }
    @Autowired
    private OrderBatchService orderBatchService;
    @Test
    void testOrderBatchService(){
        OrderBatch orderBatch = new OrderBatch();
        Date date = new Date();
        DateFormat c = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(c.format(date));
        orderBatchService.save(orderBatch);
    }
    @Autowired
    private DeviceDataService deviceDataService;
    @Test
    void testaaa() throws IOException {
        HtmlParseUtil htmlParseUtil1 = new HtmlParseUtil();
        List<PigPrice> pigPrice = htmlParseUtil1.getPigPrice();
        System.out.println(JSON.toJSONString(pigPrice));
    }
    @Test
    void createData() throws MqttException {
        for (int i = 0 ; i < (24 * 720);i++)
            sendMessage.sendObjectMessage("testtopic/1111","{\"data\":17.8,\"name\":\"dht11_temp\",\"status\":1,\"type\":\"temp\"}",1);
    }
}
