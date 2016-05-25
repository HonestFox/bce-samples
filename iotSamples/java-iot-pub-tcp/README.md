# java-iot-pub-tcp

## 用途：

编写Java代码，使用TCP方式连接到百度IoT服务，然后向指定的主题发布消息。

**注意：需提前开通百度IoT服务，并配置好IoT实例、设备、身份、策略、主题等。**

## 使用方法：

执行入口方法：`App:main`，可以看到类似于下面的输出内容（运行环境为IntelliJ IDEA）：

```
五月 25, 2016 10:42:20 上午 com.example.App main
信息: Connected to tcp://food.mqtt.iot.gz.baidubce.com:1883
五月 25, 2016 10:42:21 上午 com.example.App main
信息: Published message: 1 to topic: temperature/k2/f5/c with QoS 1
五月 25, 2016 10:42:22 上午 com.example.App main
信息: Published message: 2 to topic: temperature/k2/f5/c with QoS 1
五月 25, 2016 10:42:23 上午 com.example.App main
信息: Published message: 3 to topic: temperature/k2/f5/c with QoS 1
五月 25, 2016 10:42:24 上午 com.example.App main
信息: Published message: 4 to topic: temperature/k2/f5/c with QoS 1
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
final String ENDPOINT = "tcp://YOUR_ENDPOINT_NAME.mqtt.iot.gz.baidubce.com:1883";
final String USERNAME = "YOUR_ENDPOINT_NAME/YOUR_THING_NAME";
final String PASSWORD = "身份对应的密钥";
final String TOPIC = "YOUR_TOPIC";
```

说明：

* ENDPOINT

    > IoT实例的地址，可以在`管理控制台`的`实例详情`页面查看。
    >
    > 这里使用的是TCP连接方式，因此，**前缀`tcp://`和后缀的端口号`1883`都不能被省略。**

* USERNAME

    > IoT设备（thing）的**全名**，可以在`管理控制台`的`设备列表`页面查看。
    
* PASSWORD

    > 创建IoT身份（principal）时所生成的**密钥**。
    >
    > 该密钥无法在`管理控制台`查看，如果丢失，只能在`管理控制台`的`身份列表`页面`重新生成密钥`。

* TOPIC

    > 创建IoT策略（policy）时所添加的主题（topic）。可以在`管理控制台`的`策略列表`页面查看。
    >
    > 因为我们这里要向该主题发布消息，因此，针对该主题必须有发布（PUB）权限。

### 第三步：创建MqttClient实例，并使用它连接到IoT服务端。

```java
MqttClient client = new MqttClient(ENDPOINT, MqttClient.generateClientId());
client.connect(createMqttConnectOptions(USERNAME, PASSWORD));
```

在连接到IoT服务端时，需要配置`MqttConnectOptions`（与SSL连接方式不同之处：无需配置SSLContext）。

```java
private static MqttConnectOptions createMqttConnectOptions(String username, String password) throws Exception {
    MqttConnectOptions options = new MqttConnectOptions();
    options.setCleanSession(true);
    options.setUserName(username);
    options.setPassword(password.toCharArray());
    return options;
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
> 如果需要更改的话，可以使用如下代码：
> 
> ```java
> int qos = 0;
> message.setQos(qos);
> ```
>   
> 需要注意的是：**百度IoT服务暂时不支持QoS2。**

---

**注意：当不再需要发布消息时，需要与服务端断开连接。**

```java
client.disconnect();
```