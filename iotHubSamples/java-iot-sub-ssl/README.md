# java-iot-sub-ssl

## 用途：

编写Java代码，使用SSL方式连接到百度IoT服务，然后订阅某个主题并接收相关的消息。

**注意：需提前开通百度IoT服务，并配置好IoT实例、设备、身份、策略、主题等。**

## 使用方法：

* 第一步：执行入口方法`App:main`，连接到IoT服务端并订阅相应的主题。
* 第二步：使用其它设备或软件向该主题发送消息（可以使用MQTT.fx，也可以使用 [java-iot-pub-ssl](https://github.com/floodliu/bceSamples/tree/master/iotSamples/java-iot-pub-ssl) 的示例代码）。
* 第三步：切换回当前程序的执行环境，可以看到类似于下面的输出内容（运行环境为IntelliJ IDEA）：

    ```
    五月 24, 2016 8:04:01 下午 com.example.App main
    信息: Connected to ssl://food.mqtt.iot.gz.baidubce.com:1884
    五月 24, 2016 8:04:01 下午 com.example.App main
    信息: Subscribed topic: temperature/#
    五月 24, 2016 8:04:01 下午 com.example.SubscriberMqttCallback messageArrived
    信息: Received message: 9 from topic: temperature/k2/f5/c with QoS 1
    五月 24, 2016 8:04:02 下午 com.example.SubscriberMqttCallback messageArrived
    信息: Received message: 10 from topic: temperature/k2/f5/c with QoS 1
    五月 24, 2016 8:04:03 下午 com.example.SubscriberMqttCallback messageArrived
    信息: Received message: 11 from topic: temperature/k2/f5/c with QoS 1
    五月 24, 2016 8:04:04 下午 com.example.SubscriberMqttCallback messageArrived
    信息: Received message: 12 from topic: temperature/k2/f5/c with QoS 1
    ......
    ```

## 代码简介：

### 第一步：添加org.eclipse.paho.client.mqttv3依赖。

在pom.xml文件中添加以下内容：

```xml
<dependencies>
    <dependency>
        <groupId>org.eclipse.paho</groupId>
        <artifactId>org.eclipse.paho.client.mqttv3</artifactId>
        <version>1.0.2</version>
    </dependency>
</dependencies>
```

### 第二步：配置IoT信息。

```java
final String ENDPOINT = "ssl://YOUR_ENDPOINT_NAME.mqtt.iot.gz.baidubce.com:1884";
final String USERNAME = "YOUR_ENDPOINT_NAME/YOUR_THING_NAME";
final String PASSWORD = "身份对应的密钥";
final String TOPIC = "YOUR_TOPIC";
```

说明：

* ENDPOINT

    > IoT实例的地址，可以在`管理控制台`的`实例详情`页面查看。
    >
    > 这里使用的是SSL连接方式，因此，**前缀`ssl://`和后缀的端口号`1884`都不能被省略。**

* USERNAME

    > IoT设备（thing）的**全名**，可以在`管理控制台`的`设备列表`页面查看。
    
* PASSWORD

    > 创建IoT身份（principal）时所生成的**密钥**。
    >
    > 该密钥无法在`管理控制台`查看，如果丢失，只能在`管理控制台`的`身份列表`页面`重新生成密钥`。

* TOPIC

    > 创建IoT策略（policy）时所添加的主题（topic）。可以在`管理控制台`的`策略列表`页面查看。
    >
    > 因为我们这里要订阅该主题并收取相关消息，因此，针对该主题必须有订阅（SUB）权限。

### 第三步：创建MqttClient实例，并使用它连接到IoT服务端。

```java
MqttClient client = new MqttClient(ENDPOINT, MqttClient.generateClientId());
client.connect(createMqttConnectOptions(USERNAME, PASSWORD));
```

在连接到IoT服务端时，需要配置MqttConnectOptions和SSLContext。

```java
private static MqttConnectOptions createMqttConnectOptions(String username, String password) throws Exception {
    MqttConnectOptions options = new MqttConnectOptions();
    options.setCleanSession(true);
    options.setUserName(username);
    options.setPassword(password.toCharArray());
    options.setSocketFactory(createSslContext().getSocketFactory());
    return options;
}
```

```java
private static SSLContext createSslContext() throws Exception {
    TrustManagerFactory factory = TrustManagerFactory.getInstance("X509");
    factory.init((KeyStore) null);
    TrustManager[] trustManagers = factory.getTrustManagers();

    SSLContext context = SSLContext.getInstance("TLS");
    context.init(null, trustManagers, null);
    return context;
}
```

### 第四步：订阅主题。

```java
client.subscribe(TOPIC);
```

> 在订阅主题时，默认使用QoS1。  
> 如果需要更改的话，可以使用如下代码：
> 
> ```java
> int qos = 0;
> client.subscribe(TOPIC, qos);
> ```
> 
> 需要注意的是：**百度IoT服务暂时不支持QoS2。**

### 第五步：接收消息。

为`MqttClient`设置callback：

```java
client.setCallback(new SubscriberMqttCallback());
```

其中，`SubscriberMqttCallback`类的定义如下：

```java
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
```

当接收到IoT服务端发来的新消息时，该类中的`messageArrived`方法将被执行，我们在该方法中输出消息的内容。

---

**注意：当不再需要接收消息时，需要取消订阅，最后与服务端断开连接。**

```java
client.unsubscribe(TOPIC);
client.disconnect();
```