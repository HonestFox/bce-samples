package com.example;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.logging.Logger;

/**
 * Hello world!
 */
public class App {

    private static Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) throws MqttException {
        final String ENDPOINT = "tcp://YOUR_ENDPOINT_NAME.mqtt.iot.gz.baidubce.com:1883";  // IoT实例的地址(SSL方式)
        final String USERNAME = "YOUR_ENDPOINT_NAME/YOUR_THING_NAME";  // IoT设备的全名
        final String PASSWORD = "身份对应的密钥";  // 创建IoT身份时生成的密钥
        final String TOPIC = "YOUR_TOPIC";  // 创建IoT策略时填写的主题, 需要有PUB权限。

        MqttClient client = new MqttClient(ENDPOINT, MqttClient.generateClientId());
        client.connect(createMqttConnectOptions(USERNAME, PASSWORD));
        logger.info("Connected to " + ENDPOINT);

        try {
            for (int i = 1; true; i++) {
                MqttMessage message = new MqttMessage();
                message.setPayload(String.valueOf(i).getBytes());
//                message.setQos(0);
                client.publish(TOPIC, message);
                logger.info(String.format("Published message: %s to topic: %s with QoS %d",
                        new String(message.getPayload()), TOPIC, message.getQos()));

                Thread.sleep(1000);
            }
        } catch (InterruptedException exception) {
            client.disconnect();
        }
    }

    private static MqttConnectOptions createMqttConnectOptions(String username, String password) {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        options.setUserName(username);
        options.setPassword(password.toCharArray());
        return options;
    }
}
