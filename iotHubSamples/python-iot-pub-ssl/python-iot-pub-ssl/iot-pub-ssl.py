# -*- coding: utf-8 -*-

import paho.mqtt.client as mqtt
import time

# 配置IoT
ENDPOINT = "YOUR_ENDPOINT_NAME.mqtt.iot.gz.baidubce.com"  # IoT实例的地址
PORT = 1884  # IoT实例地址的端口号

USERNAME = "YOUR_ENDPOINT_NAME/YOUR_THING_NAME"  # IoT设备的全名
PASSWORD = ""  # 创建IoT身份时生成的密钥
CA_CERT = "./root_cert.pem"  # SSL认证文件

TOPIC = "YOUR_TOPIC_NAME"  # 创建IoT策略时配置的主题, 需要有发布(PUB)权限).

# 保存所有已经发布的消息
publishedMessages = {}


def main():
    # 创建MQTT客户端
    client = mqtt.Client(
        client_id="iot-publisher",
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
    client.on_publish = onPublish
    client.on_disconnect = onDisconnect

    # 连接到百度IoT服务端
    client.connect(host=ENDPOINT, port=PORT)
    client.loop_start()

    # 向指定的主题发布10条消息, 内容分别为数值: 1, 2, 3, ..., 10, 每条消息之间的发送时间间隔为1秒.
    for count in range(1, 11):
        time.sleep(1)

        message = mqtt.MQTTMessage()
        message.topic = TOPIC
        message.payload = count
        message.qos = 1

        resultCode, messageId = client.publish(topic=message.topic, payload=message.payload, qos=message.qos)

        message.mid = messageId
        publishedMessages[messageId] = message

    # 与百度IoT服务端断开连接
    client.loop_stop()
    client.disconnect()


def onConnect(client, userdata, flags, resultCode):
    if resultCode == 0:
        print("Connected to {}:{}".format(ENDPOINT, PORT))
    else:
        print("Failed to connect to {}:{}, resultCode: {}".format(ENDPOINT, PORT, resultCode))


def onPublish(client, userdata, messageId):
    message = publishedMessages[messageId]
    print("Published message: {} to topic: {} with QoS {}, messageId: {}".format(
        str(message.payload), message.topic, message.qos, message.mid
    ))


def onDisconnect(client, userdata, resultCode):
    if resultCode == 0:
        print("Disconnected from {}:{}".format(ENDPOINT, PORT))
    else:
        print("Exceptional disconnection, resultCode: {}".format(resultCode))


if __name__ == "__main__":
    main()
