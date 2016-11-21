# IoT Hub samples

## 使用MQTT客户端发布/订阅消息

| 操作 | 连接方式 | Java | Python | Javascript | C |
| :-- | :-- | :-- | :-- | :-- | :-- |
| 发布消息 | SSL | [java-iot-pub-ssl](./java-iot-pub-ssl) | [python-iot-pub-ssl](./python-iot-pub-ssl) || [c-iot-pub-ssl](./c-iot-pub-ssl) |
| 发布消息 | TCP | [java-iot-pub-tcp](./java-iot-pub-tcp) | [python-iot-pub-tcp](./python-iot-pub-tcp) |||
| 发布消息 | WSS(WebSockets) ||| [js-iot-pub-wss](./js-iot-pub-wss) ||
|
| 订阅消息 | SSL | [java-iot-sub-ssl](./java-iot-sub-ssl) | [python-iot-sub-ssl](./python-iot-sub-ssl) || [c-iot-sub-ssl](./c-iot-sub-ssl) |
| 订阅消息 | TCP | [java-iot-sub-tcp](./java-iot-sub-tcp) | [python-iot-sub-tcp](./python-iot-sub-tcp) |||
| 订阅消息 | WSS(WebSockets) ||| [js-iot-sub-wss](./js-iot-sub-wss) ||

## 使用iothub的API/SDK来操作实例

| 操作 | PHP (API) | Java (SDK) |
| :-- | :-- | :-- |
| 获取endpoint列表 | [php-iot-getEndpoints](./php-iot-getEndpoints) | [java-iot-getEndpoints](./java-iot-getEndpoints) |
| 获取指定的endpoint信息 | [php-iot-getEndpoint](./php-iot-getEndpoint) | [java-iot-getEndpoint](./java-iot-getEndpoint) |
| 创建endpoint | [php-iot-createEndpoint](./php-iot-createEndpoint) | [java-iot-createEndpoint](./java-iot-createEndpoint) |
| 删除endpoint | [php-iot-deleteEndpoint](./php-iot-deleteEndpoint) | [java-iot-deleteEndpoint](./java-iot-deleteEndpoint) |
|
| 获取thing列表 | [php-iot-getThings](./php-iot-getThings) | [java-iot-getThings](./java-iot-getThings) |
| 获取指定的thing信息 | [php-iot-getThing](./php-iot-getThing) | [java-iot-getThing](./java-iot-getThing) |
| 创建thing | [php-iot-createThing](./php-iot-createThing) | [java-iot-createThing](./java-iot-createThing) |
| 删除thing | [php-iot-deleteThing](./php-iot-deleteThing) | [java-iot-deleteThing](./java-iot-deleteThing) |
|
| 获取principal列表 | [php-iot-getPrincipals](./php-iot-getPrincipals) |
| 获取指定的principal信息 | [php-iot-getPrincipal](./php-iot-getPrincipal) |
| 创建principal | [php-iot-createPrincipal](./php-iot-createPrincipal) |
| 重新生成principal的密钥 | [php-iot-generatePassword](./php-iot-generatePassword) |
| 删除principal | [php-iot-deletePrincipal](./php-iot-deletePrincipal) |
|
| 获取policy列表 | [php-iot-getPolicies](./php-iot-getPolicies) |
| 获取指定的policy信息 | [php-iot-getPolicy](./php-iot-getPolicy) |
| 创建policy | [php-iot-createPolicy](./php-iot-createPolicy) |
| 删除policy | [php-iot-deletePolicy](./php-iot-deletePolicy) |
|
| 获取policy内的所有topic信息 | [php-iot-getPermissions](./php-iot-getPermissions) |
| 获取指定的topic信息 | [php-iot-getPermission](./php-iot-getPermission) |
| 在指定的policy内创建topic | [php-iot-createPermission](./php-iot-createPermission) |
| 修改指定的topic信息 | [php-iot-updatePermission](./php-iot-updatePermission) |
| 删除指定的topic | [php-iot-deletePermission](./php-iot-deletePermission) |
|
| 为指定的thing绑定principal | [php-iot-attachThingPrincipal](./php-iot-attachThingPrincipal) |
| 从指定的thing解绑principal | [php-iot-removeThingPrincipal](./php-iot-removeThingPrincipal) |
| 为指定的principal绑定policy | [php-iot-attachPrincipalPolicy](./php-iot-attachPrincipalPolicy) |
| 从指定的principal解绑policy | [php-iot-removePrincipalPolicy](./php-iot-removePrincipalPolicy) |
|
| 认证（通过username/password，获得endpoint信息及principalUuid）| [php-iot-authenticate](./php-iot-authenticate) |
| 鉴权（指定principalUuid，判断这个principal对某些操作是否有权限） | [php-iot-authorize](./php-iot-authorize) |

## 附：如何查看AVRO文件的内容？

百度IoT提供了“归档列表”功能，可以把IoT的消息内容保存到BOS中。但默认的保存格式是AVRO，无法直接用文本编辑器查看。

下面我们提供一种把AVRO转成JSON的方式，以方便使用文本编辑器查看IoT消息。

所需软件：

* JRE
    * 下载地址：[http://www.java.com/zh_CN/download/manual.jsp](http://www.java.com/zh_CN/download/manual.jsp)
* AVRO-TOOLS
    * 下载地址：[http://apache.mirrors.tds.net/avro/](http://apache.mirrors.tds.net/avro/)

操作步骤：

1. 下载AVRO-TOOLS（以1.8.1版本为例，完整下载地址为：[http://apache.mirrors.tds.net/avro/avro-1.8.1/java/avro-tools-1.8.1.jar](http://apache.mirrors.tds.net/avro/avro-1.8.1/java/avro-tools-1.8.1.jar)），下载后得到文件`avro-tools-1.8.1.jar`。
2. 打开命令行工具，进入`avro-tools-1.8.1.jar`所在的目录。
3. 执行命令：`java -jar avro-tools-1.8.1.jar tojson test.avro > test.json`

    > 该命令读取文件`test.avro`的内容，将其转换成JSON格式，最后保存到文件`test.json`中。可根据实际情况自行替换这两个文件名。
4. 使用任意文本编辑器打开文件`test.json`，其内容形式如下：

    ```
    {"ip":"61.191.217.235","clientId":"4683b8bdcaf9486d9346756d0cb98cd2","topicName":"pubsub","messageId":{"long":0},"payload":"asdf ","time":"2016-06-16 15:14:47"}
    {"ip":"61.191.217.235","clientId":"4683b8bdcaf9486d9346756d0cb98cd2","topicName":"pubsub","messageId":{"long":0},"payload":"asdf ","time":"2016-06-16 15:15:01"}
    {"ip":"61.191.217.235","clientId":"4683b8bdcaf9486d9346756d0cb98cd2","topicName":"pubsub","messageId":{"long":0},"payload":"asdf ","time":"2016-06-16 15:16:38"}
    {"ip":"61.191.217.235","clientId":"4683b8bdcaf9486d9346756d0cb98cd2","topicName":"pubsub","messageId":{"long":0},"payload":"ssdss","time":"2016-06-16 15:16:44"}
    {"ip":"61.191.217.235","clientId":"4683b8bdcaf9486d9346756d0cb98cd2","topicName":"pubsub","messageId":{"long":0},"payload":"sdfasdfadfadsfadf\ndsfafasdf\nmytext is very good","time":"2016-06-16 16:24:19"}
    {"ip":"61.191.217.235","clientId":"4683b8bdcaf9486d9346756d0cb98cd2","topicName":"pubsub","messageId":{"long":0},"payload":"sdfasdfadfadsfadf\ndsfafasdf\nmytext is very good","time":"2016-06-16 16:24:26"}
    ```