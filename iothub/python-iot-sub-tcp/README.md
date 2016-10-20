# python-iot-sub-tcp

## 用途：

编写Python代码，使用SSL方式连接到百度IoT服务，订阅某个主题并接收相关的消息。

**注意：需提前开通百度IoT服务，并配置好IoT实例、设备、身份、策略、主题等。**

## 使用方法：

* 第一步：进入`iot-sub-tcp.py`所在的目录，执行命令：

    > * Python 2.x: `python iot-sub-tcp.py`
    > * Python 3.x: `python3 iot-sub-tcp.py`

* 第二步：使用MQTT.fx向该主题发布消息，或者使用[python-iot-pub-tcp](https://github.com/floodliu/bceSamples/tree/master/iotSamples/python-iot-pub-tcp)。

* 第三步：切换回`iot-sub-tcp.py`的运行环境，执行结果如下：

    ```
    Connected to food.mqtt.iot.gz.baidubce.com:1883
    Subscribed topic: temperature/# with QoS 1
    Received message: b'1' from topic: temperature/k2/f5/c with QoS: 1, messageId: 32768
    Received message: b'2' from topic: temperature/k2/f5/c with QoS: 1, messageId: 32769
    Received message: b'3' from topic: temperature/k2/f5/c with QoS: 1, messageId: 32770
    Received message: b'4' from topic: temperature/k2/f5/c with QoS: 1, messageId: 32771
    Received message: b'5' from topic: temperature/k2/f5/c with QoS: 1, messageId: 32772
    Received message: b'6' from topic: temperature/k2/f5/c with QoS: 1, messageId: 32773
    Received message: b'7' from topic: temperature/k2/f5/c with QoS: 1, messageId: 32774
    Received message: b'8' from topic: temperature/k2/f5/c with QoS: 1, messageId: 32775
    Received message: b'9' from topic: temperature/k2/f5/c with QoS: 1, messageId: 32776
    Received message: b'10' from topic: temperature/k2/f5/c with QoS: 1, messageId: 32777
    ```

## 代码简介：

### 第一步：安装paho-mqtt。

* Python 2.x: `pip install paho-mqtt`
* Python 3.x: `pip3 install paho-mqtt`

### 第二步：配置IoT信息。

```python
ENDPOINT = "YOUR_ENDPOINT_NAME.mqtt.iot.gz.baidubce.com"  # IoT实例的地址
PORT = 1883  # IoT实例地址的端口号

USERNAME = "YOUR_ENDPOINT_NAME/YOUR_THING_NAME"  # IoT设备的全名
PASSWORD = "身份对应的密钥"  # 创建IoT身份时生成的密钥

TOPIC = "YOUR_TOPIC_NAME"  # 创建IoT策略时配置的主题, 需要有订阅(SUB)权限).
```

说明：

* ENDPOINT

    > IoT实例的地址，可以在`管理控制台`的`实例详情`页面查看。

* PORT

    > IoT实例地址的端口号，可以在`管理控制台`的`实例详情`页面查看。这里使用TCP连接方式，因此端口号为：1883。

* USERNAME

    > IoT设备（thing）的**全名**，可以在`管理控制台`的`设备列表`页面查看。
    
* PASSWORD

    > 创建IoT身份（principal）时所生成的**密钥**。
    >
    > 该密钥无法在`管理控制台`查看，如果丢失，只能在`管理控制台`的`身份列表`页面`重新生成密钥`。

* TOPIC

    > 创建IoT策略（policy）时所添加的主题（topic）。可以在`管理控制台`的`策略列表`页面查看。
    >
    > 因为我们这里要向该主题发布消息，因此，针对该主题必须有订阅（SUB）权限。

### 第三步：创建并配置MqttClient实例。

```python
client = mqtt.Client(
    client_id="iot-subscriber",
    clean_session=True,
    userdata=None,
    protocol="MQTTv311"
)

client.username_pw_set(USERNAME, PASSWORD)
```

### 第四步：连接到百度IoT服务端并订阅某个主题。

```python
client.connect(host=ENDPOINT, port=PORT)
client.loop_forever()
```

连接成功后，`on_connect`函数会被调用。我们在该函数内订阅某个主题：

```python
client.subscribe(topic=TOPIC, qos=1)
```

> 如果在调用`subscribe`方法时未提供`qos`参数，则默认使用QoS0。  
> 需要注意的是：**百度IoT服务暂时不支持QoS2。**

当客户端接收到新消息时，`on_message`函数会被调用。我们可以在该函数内处理消息。