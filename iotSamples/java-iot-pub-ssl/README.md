# java-iot-pub-ssl

## 用途：

编写Java代码，使用SSL方式连接到百度IoT服务，然后向指定的主题发布消息。

**注意：需提前开通百度IoT服务，并配置好IoT实例、设备、身份、策略、主题等。**

## 使用方法：

直接运行入口方法：App:main，执行过程（IntelliJ IDEA）类似于：

```
五月 24, 2016 4:45:57 下午 com.example.App main
信息: Connected to ssl://food.mqtt.iot.gz.baidubce.com:1884
五月 24, 2016 4:45:57 下午 com.example.App main
信息: Send message: 1 with qos 1
五月 24, 2016 4:45:58 下午 com.example.App main
信息: Send message: 2 with qos 1
五月 24, 2016 4:45:59 下午 com.example.App main
信息: Send message: 3 with qos 1
五月 24, 2016 4:46:00 下午 com.example.App main
信息: Send message: 4 with qos 1
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

* TOPIC

    > 创建IoT策略（policy）时所添加的主题（topic）。
    >
    > 因为我们这里要向该主题发布消息，因此，针对该主题必须有发布（PUB）权限。

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

### 第四步：使用MqttClient实例发布消息。

```java
MqttMessage message = new MqttMessage();
message.setPayload("123".getBytes());  // "123"是要发布的消息内容
// message.setQos(0);  // 设置订阅的QoS, 默认值为: QoS 1.
client.publish(TOPIC, message);
```

> 在发布消息时，默认使用QoS1。  
> 如果需要更改的话，可以调用MqttMessage的setQos方法进行设置。  
> 需要注意的是：**百度IoT服务暂时不支持QoS2。**