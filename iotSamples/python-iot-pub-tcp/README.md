# python-iot-pub-tcp

## 用途：

编写Python代码，使用TCP方式连接到百度IoT服务，然后向指定的主题发布消息。

**注意：需提前开通百度IoT服务，并配置好IoT实例、设备、身份、策略、主题等。**

## 使用方法：

进入`iot-pub-tcp.py`所在的目录，执行命令：

* Python 2.x: `python iot-pub-tcp.py`
* Python 3.x: `python3 iot-put-tcp.py`

运行结果如下：

```
Connected to food.mqtt.iot.gz.baidubce.com:1883
Published message: 1 to topic: temperature/k2/f5/c with QoS 1, messageId: 1
Published message: 2 to topic: temperature/k2/f5/c with QoS 1, messageId: 2
Published message: 3 to topic: temperature/k2/f5/c with QoS 1, messageId: 3
Published message: 4 to topic: temperature/k2/f5/c with QoS 1, messageId: 4
Published message: 5 to topic: temperature/k2/f5/c with QoS 1, messageId: 5
Published message: 6 to topic: temperature/k2/f5/c with QoS 1, messageId: 6
Published message: 7 to topic: temperature/k2/f5/c with QoS 1, messageId: 7
Published message: 8 to topic: temperature/k2/f5/c with QoS 1, messageId: 8
Published message: 9 to topic: temperature/k2/f5/c with QoS 1, messageId: 9
Published message: 10 to topic: temperature/k2/f5/c with QoS 1, messageId: 10
Disconnected from food.mqtt.iot.gz.baidubce.com:1883
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

TOPIC = "temperature/k2/f5/c"  # 创建IoT策略时配置的主题, 需要有发布(PUB)权限).
```

说明：

* ENDPOINT

    > IoT实例的地址，可以在`管理控制台`的`实例详情`页面查看。

* PORT

    > IoT实例地址的端口号，可以在`管理控制台`的`实例详情`页面查看。这里使用SSL连接方式，因此端口号为：1884。

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

### 第三步：创建并配置MqttClient实例。

```python
client = mqtt.Client(
    client_id="iot-publisher",
    clean_session=True,
    userdata=None,
    protocol="MQTTv311"
)

client.username_pw_set(USERNAME, PASSWORD)
```

### 第四步：连接到百度IoT服务端，并向某个主题发布消息。

```python
client.connect(host=ENDPOINT, port=PORT)
client.loop_start()

for count in range(1, 11):
    time.sleep(1)
    client.publish(topic=TOPIC, payload=count, qos=1)
```

> 如果在调用`publish`方法时未提供`qos`参数，则默认使用QoS0。  
> 需要注意的是：**百度IoT服务暂时不支持QoS2。**

### 第五步：与服务端断开连接。

```python
client.loop_stop()
client.disconnect()
```