package com.zzut.pigbreeding.paho;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Data
public class PahoClientFactory {
    @Value("${paho.factory.broker}")
    private  String broker;
    @Value("${paho.factory.userName}")
    private  String userName;
    @Value("${paho.factory.password}")
    private  String password;
    @Value("${paho.factory.clientId}")
    private  String clientId;

    /**
     * 使用非静态工厂得到MqttClient对象
     *
     */
    @Bean
    public MqttClient getPahoClient() {



        //int

        //string

        // 内存存储
        MemoryPersistence persistence = new MemoryPersistence();
        try {
            // 创建客户端
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            // 创建链接参数
            MqttConnectOptions connOpts = new MqttConnectOptions();
            // 在重新启动和重新连接时记住状态
            connOpts.setCleanSession(false);
            // 设置连接的用户名
            connOpts.setUserName(userName);
            connOpts.setPassword(password.toCharArray());
            // 建立连接
            System.out.println("connecting to broker: " + broker);
            sampleClient.connect(connOpts);
            return sampleClient;

        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }
}
