package com.zzut.pigbreeding.paho.demo;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;


public class MyMqttCallback implements MqttCallback {

    @Override
    public void connectionLost(Throwable throwable) {

    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        try {
            System.out.println("接收来自主题:" + topic+"的信息内容:");
            String mystring ="";
            mystring=new String(mqttMessage.getPayload());
            System.out.println(mystring);
//            Job job = JSON.parseObject(mystring, Job.class);
//            System.out.println(job);
//            TODO paho信息 json对象转换
//            User user = JSON.parseObject(mystring, User.class);
//            System.out.println(user);
            System.out.println("--------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(33);
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
