package com.example;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.logging.Logger;

/**
 * Created by hongtao on 16/5/24.
 */
public class SubscriberMqttCallback implements MqttCallback {

    private static Logger logger = Logger.getLogger(SubscriberMqttCallback.class.getName());

    public void connectionLost(Throwable cause) {
    }

    /*
     * 当接收到IoT服务端发来的消息时, 该方法被调用。
     */
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        String info = String.format("Received message: %s from topic: %s with QoS %d",
                new String(message.getPayload()), topic, message.getQos());
        logger.info(info);
    }

    public void deliveryComplete(IMqttDeliveryToken token) {
    }
}
