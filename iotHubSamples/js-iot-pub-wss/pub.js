// 引用paho-mqtt的javascript代码
document.write("<script language=\"javascript\" src=\"https://cdnjs.cloudflare.com/ajax/libs/paho-mqtt/1.0.1/mqttws31.js\"></script>");

var iotClient;

// 创建IoT客户端并连接到IoT实例（服务端）
function connectToIot() {
    // 创建IoT Client
    host = "YOUR_ENDPOINT_NAME.mqtt.iot.gz.baidubce.com";  // 设置IoT实例的连接地址
    port = 8884;

    clientId = String(Math.random()).substring(2);
    iotClient = new Paho.MQTT.Client(host, port, clientId);
    iotClient.onMessageDelivered = onMessageDelivered;
    iotClient.onConnectionLost = onConnectionLost;

    // 连接到IoT实例
    username = "YOUR_ENDPOINT_NAME/YOUR_THING_NAME";  // 设置用户名（设备全名）
    password = "身份对应的密钥";  // 设置密码（设备身份所对应的密钥）

    connectOptions = {
        invocationContext: {
            host: host,
            port: port
        },

        userName: username,
        password: password,

        timeout: 30,
        keepAliveInterval: 60,
        cleanSession: true,
        useSSL: true,

        onSuccess: onConnectSuccess,
        onFailure: onConnectFailure
    };
    iotClient.connect(connectOptions);
}

// 向某个主题发布消息
function publish() {
    topic = "YOUR_TOPIC";  // 设置主题（用来发布消息）
    payload = String(Math.random());
    message = new Paho.MQTT.Message(payload);
    message.destinationName = topic;

    iotClient.send(message);
}

// 与IoT实例（服务端）断开连接
function disconnect() {
    iotClient.disconnect();
}

// 当IoT客户端成功连接到IoT实例（服务端）时，该函数被调用。
function onConnectSuccess(connectedOptions) {
    host = connectedOptions.invocationContext.host;
    port = connectedOptions.invocationContext.port;
    console.log("connected to " + host + ":" + port + " successfully");
}

// 当IoT客户端连接IoT实例（服务端）失败时，该函数被调用。
function onConnectFailure(connectedOptions) {
    host = connectedOptions.invocationContext.host;
    port = connectedOptions.invocationContext.port;
    console.log("failed to connect to : " + host + ":" + port);
}

// 当IoT客户端成功发布消息后，该函数被调用。
function onMessageDelivered(message) {
    payload = message.payloadString;
    topic = message.destinationName;
    qos = message.qos;
    console.log("published message: " + payload + " to topic: " + topic + " with qos: " + qos);
}

// 当IoT客户端与IoT实例（服务端）断开连接时，该函数被调用。
function onConnectionLost(response) {
    errorCode = response.errorCode;
    errorMessage = response.errorMessage;
    if (errorCode == 0) {
        console.log("disconnected from iot host.");
    } else {
        console.log("disconnected form iot host, code: " + errorCode + ", message: " + errorMessage);
    }
}