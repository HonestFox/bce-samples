package com.example;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import java.security.KeyStore;
import java.util.logging.Logger;

/**
 * 本程序在角色上为一个IoT客户端, 它通过SSL方式连接到百度IoT服务端, 然后向某个主题发布消息。
 */
public class App {

    private static Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) throws Exception {
        final String ENDPOINT = "ssl://YOUR_ENDPOINT_NAME.mqtt.iot.gz.baidubce.com:1884";  // IoT实例的地址(SSL连接方式)
        final String USERNAME = "YOUR_ENDPOINT_NAME/YOUR_THING_NAME";  // IoT设备的全名
        final String PASSWORD = "";  // 创建IoT身份时生成的密钥
        final String TOPIC = "YOUR_TOPIC_NAME";  // 创建IoT策略时填写的主题, 需要有PUB权限。

        MqttClient client = new MqttClient(ENDPOINT, MqttClient.generateClientId());
//        MqttClient client = new MqttClient(ENDPOINT, "iot-publisher");  // 手动设置clientID, 不使用随机生成的值。
        client.connect(createMqttConnectOptions(USERNAME, PASSWORD));
        logger.info("Connected to " + ENDPOINT);

        try {
            for (int i = 1; true; i++) {
                MqttMessage message = new MqttMessage();
                message.setPayload(String.valueOf(i).getBytes());
//                message.setQos(0);  // 设置订阅的QoS, 默认值为: QoS 1.
                client.publish(TOPIC, message);
                logger.info(String.format("Send message: %s with qos %d",
                        new String(message.getPayload()), message.getQos()));

                Thread.sleep(1000);
            }
        } catch (InterruptedException exception) {
            client.disconnect();
            logger.info("Disconnected from " + ENDPOINT);
        }
    }

    private static MqttConnectOptions createMqttConnectOptions(String username, String password) throws Exception {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        options.setUserName(username);
        options.setPassword(password.toCharArray());
        options.setSocketFactory(createSslContext().getSocketFactory());
        return options;
    }

    private static SSLContext createSslContext() throws Exception {
        TrustManagerFactory factory = TrustManagerFactory.getInstance("X509");
        factory.init((KeyStore) null);
        TrustManager[] trustManagers = factory.getTrustManagers();

        SSLContext context = SSLContext.getInstance("TLS");
        context.init(null, trustManagers, null);
        return context;
    }
}
