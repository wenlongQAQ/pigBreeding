package com.zzut.pigbreeding.paho.callback;


import com.alibaba.fastjson.JSON;
import com.zzut.pigbreeding.paho.callback.db.DeviceDBUtil;
import com.zzut.pigbreeding.pojo.device.Device;
import com.zzut.pigbreeding.service.DeviceService;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageMqttCallback implements MqttCallback {
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private DeviceDBUtil deviceDBUtil;
    @Override
    public void connectionLost(Throwable throwable) {

    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        try {
            System.out.println("接收来自主题:" + topic+"的信息内容:");
            String objectString =new String(mqttMessage.getPayload());
            System.out.println(objectString);
            // TODO 编写接受到信息的回调函数
            Device device = JSON.parseObject(objectString, Device.class);
            // 存储或者更新设备信息
            deviceService.saveOrUpdate(device);
            // 存储新传来的数据信息
            deviceDBUtil.saveDeviceData(device);
            System.out.println("--------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
