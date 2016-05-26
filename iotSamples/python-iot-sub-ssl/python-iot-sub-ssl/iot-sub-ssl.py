# -*- coding: utf-8 -*-

import paho.mqtt.client as mqtt

# 配置IoT
ENDPOINT = "YOUR_ENDPOINT_NAME.mqtt.iot.gz.baidubce.com"  # IoT实例的地址
PORT = 1884  # IoT实例地址的端口号

USERNAME = "YOUR_ENDPOINT_NAME/YOUR_THING_NAME"  # IoT设备的全名
PASSWORD = "身份对应的密钥"  # 创建IoT身份时生成的密钥
CA_CERT = "./root_cert.pem"  # SSL认证文件

TOPIC = "YOUR_TOPIC_NAME"  # 创建IoT策略时配置的主题, 需要有订阅(SUB)权限.


def main():
    # 创建MQTT客户端
    client = mqtt.Client(
        client_id="iot-subscriber",
        clean_session=True,
        userdata=None,
        protocol="MQTTv311"
    )

    # 配置MQTT客户端
    client.tls_insecure_set(False)
    client.tls_set(CA_CERT)
    client.username_pw_set(USERNAME, PASSWORD)

    # 配置回调函数
    client.on_connect = onConnect
    client.on_subscribe = onSubscribe
    client.on_message = onMessage
    client.on_disconnect = onDisconnect

    # 连接到百度IoT服务端, 在连接成功之后订阅某个主题, 然后接收并处理消息.
    client.connect(host=ENDPOINT, port=PORT)
    client.loop_forever()

    client.loop_stop()
    client.disconnect()


def onConnect(client, userdata, flags, resultCode):
    if resultCode == 0:
        print("Connected to {}:{}".format(ENDPOINT, PORT))
        client.subscribe(topic=TOPIC, qos=1)  # 订阅主题
    else:
        print("Failed to connect to {}:{}".format(ENDPOINT, PORT))


def onSubscribe(client, userdata, messageId, grantedQos):
    print("Subscribed topic: {} with QoS {}".format(TOPIC, grantedQos[0]))


def onMessage(client, userdata, message):
    print("Received message: {} from topic: {} with QoS: {}, messageId: {}".format(
        str(message.payload), message.topic, message.qos, message.mid
    ))


def onDisconnect(client, userdata, resultCode):
    if resultCode == 0:
        print("Disconnected from {}:{}".format(ENDPOINT, PORT))
    else:
        print("Exceptional disconnection, resultCode: {}".format(resultCode))


if __name__ == "__main__":
    main()
