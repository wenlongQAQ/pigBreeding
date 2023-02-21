package com.zzut.pigbreeding.paho.message;

import org.eclipse.paho.client.mqttv3.MqttException;

public interface IMessage {
    public  boolean sendObjectMessage(String topic,Object msg,int qos) throws MqttException;
}
