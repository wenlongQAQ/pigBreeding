package com.zzut.pigbreeding.paho.server.impl;

import com.zzut.pigbreeding.paho.server.ConnectServer;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConnectServerImpl implements ConnectServer {
    @Autowired
    private MqttClient client;
    //订阅主题
    @Value("${paho.subTopic}")
    private String subTopic;
    @Value("${paho.pubTopic}")
    //发布主题
    private String pubTopic;
    @Autowired
    private MqttCallback callback;
    public void retainMessage() {
        String content="Java Client is online";//定制上线消息
        int qos =1;//消息服务等级
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
            client.setCallback(callback);
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
            client.publish(pubTopic,message);
        } catch (MqttException me) {
            log.error("reason " + me.getReasonCode());
            log.error("msg " + me.getMessage());
            log.error("loc " + me.getLocalizedMessage());
            log.error("cause " + me.getCause());
            log.error("excep " + me);
            log.error("异常");
            me.printStackTrace();
        }
    }

}
