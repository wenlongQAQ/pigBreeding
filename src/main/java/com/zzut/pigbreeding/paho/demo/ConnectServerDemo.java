package com.zzut.pigbreeding.paho.demo;

import com.zzut.pigbreeding.paho.callback.MessageMqttCallback;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConnectServerDemo {
    @Autowired
    private MqttClient client;
    //订阅主题
    @Value("${paho.subTopic}")
    private String subTopic;
    public  void retainMessage() {
        int qos =1;//消息服务等级
        String content="Java Client is online";//发布消息
        String pubtopic="esp32";//发布主题
        MemoryPersistence persistence = new MemoryPersistence();//保存形式以内容保存
        try {


            //设置消息对象
            MqttMessage message = new MqttMessage(content.getBytes());//待发送的信息
            message.setQos(qos);

            // MQTT 连接选项
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setUserName("emqx_java");
            connOpts.setPassword("random".toCharArray());
            // 保留会话
            connOpts.setCleanSession(false);
            //设置超时时间
            connOpts.setConnectionTimeout(10);
            /*
            设置会话心跳时间 单位为秒 服务器会每隔
            1.5*20秒的时间向客户端发送个消息判断客户端是否在线，
            但这个方法并没有重连的机制
             */
            connOpts.setKeepAliveInterval(20);

            // 设置回调
            client.setCallback(new MessageMqttCallback());

            // 建立连接
            while (!client.isConnected()) {
                log.info("连接到地址->" + client.getServerURI());
                client.connect(connOpts);
            }
            log.info("连接成功");
            // 订阅
            client.subscribe(subTopic,qos);
            log.info("订阅的主题->" + subTopic);
            System.out.println("订阅主题"+subTopic);

            // 发布上线消息
            client.publish(pubtopic,message);


        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            System.out.println("异常");
            me.printStackTrace();
        }
    }



}