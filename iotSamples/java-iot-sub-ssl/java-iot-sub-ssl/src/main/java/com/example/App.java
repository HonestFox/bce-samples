package com.example;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import java.security.KeyStore;
import java.util.logging.Logger;

/**
 * Hello world!
 */
public class App {

    private static Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) throws Exception {
        final String ENDPOINT = "ssl://YOUR_ENDPOINT_NAME.mqtt.iot.gz.baidubce.com:1884";  // IoT实例的地址(SSL连接方式)
        final String USERNAME = "YOUR_ENDPOINT_NAME/YOUR_THING_NAME";  // IoT设备的全名
        final String PASSWORD = "";  // 创建IoT身份时生成的密钥
        final String TOPIC = "YOUR_TOPIC_NAME";  // 创建IoT策略时添加的主题, 需要有SUB权限。

        MqttClient client = new MqttClient(ENDPOINT, MqttClient.generateClientId());
        client.connect(createMqttConnectOptions(USERNAME, PASSWORD));
        logger.info("Connected to " + ENDPOINT);

        client.setCallback(new SubscriberMqttCallback());
        client.subscribe(TOPIC);
        logger.info("Subscribed topic: " + TOPIC);

//        client.unsubscribe(TOPIC);
//        client.disconnect();
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
        return  context;
    }
}
