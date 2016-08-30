package com.example;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.logging.Logger;

/**
 * 本程序在角色上为一个IoT客户端, 通过TCP方式连接到百度IoT服务端, 订阅某个主题并接收服务器发来的消息。
 */
public class App {

    private static Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) throws MqttException {
        final String ENDPOINT = "tcp://YOUR_ENDPOINT_NAME.mqtt.iot.gz.baidubce.com:1883";  // IoT实例的地址(TCP连接方式)
        final String USERNAME = "YOUR_ENDPOINT_NAME/YOUR_THING_NAME";  // IoT设备的全名
        final String PASSWORD = "身份对应的密钥";  // 创建IoT身份时生成的密钥
        final String TOPIC = "YOUR_TOPIC";  // 创建IoT策略时填写的主题, 需要有PUB权限。

        MqttClient client = new MqttClient(ENDPOINT, MqttClient.generateClientId());
        client.connect(createMqttConnectOptions(USERNAME, PASSWORD));
        logger.info("Connected to " + ENDPOINT);

        client.setCallback(new SubscriberMqttCallback());
        client.subscribe(TOPIC);
        logger.info("Subscribed topic: " + TOPIC);

//        client.unsubscribe(TOPIC);
//        client.disconnect();
    }

    private static MqttConnectOptions createMqttConnectOptions(String username, String password) {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        options.setUserName(username);
        options.setPassword(password.toCharArray());
        return options;
    }
}
