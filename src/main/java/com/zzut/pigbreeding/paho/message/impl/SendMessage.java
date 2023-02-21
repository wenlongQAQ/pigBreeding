package com.zzut.pigbreeding.paho.message.impl;

import com.alibaba.fastjson.JSON;
import com.zzut.pigbreeding.excption.SendMessageException;
import com.zzut.pigbreeding.paho.message.IMessage;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SendMessage implements IMessage {
    @Autowired
    private MqttClient pahoClient;
    private MqttDeliveryToken token;
    private MqttMessage message;
    /**
     *
     * @param topic 发布消息的主题
     * @param msg 需要发布的具体消息对象
     * @param qos 发布消息的质量
     * @return 是否发送成功
     * @throws MqttException
     */
    @Override
    public boolean sendObjectMessage(String topic,Object msg,int qos) throws MqttException {
        String content = JSON.toJSONString(msg);
//        MqttClient pahoClient = PahoClientFactory.getPahoClient();
        System.out.println("connected.");
        // 创建消息
        message = new MqttMessage(content.getBytes());
        // 设置消息的服务质量
        message.setQos(qos);
        //设置发送信息的标题
        MqttTopic mqttTopic = pahoClient.getTopic(topic);

        if (mqttTopic == null) {
            System.out.println("topic not exist");
        }
        // 发布消息
        token = mqttTopic.publish(message);
        token.waitForCompletion();
        if (token.isComplete()) {
            log.info("已经发送成功:" + token.isComplete());
            log.info("发布的信息为->"+topic + ":" + message);
            pahoClient.subscribe("testtopic/#");
            //断开连接
            pahoClient.disconnect();
            // 关闭客户端
            return true;
        }else {
            pahoClient.subscribe("testtopic/#");
            pahoClient.disconnect();
            // 关闭客户端

            throw new SendMessageException("此信息发送失败->"+content+"\n"+"topic->"+topic);
        }

        //发送信息
        //pahoClient.publish(topic, message);


    }
}
