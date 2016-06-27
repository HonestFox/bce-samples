# IoT samples

项目命名规则：

| 项目名称 | 编程语言 | 云服务 | 动作 | 连接方式 |
| :-- | :-- | :-- | :-- | :-- | :-- |
| java-iot-pub-ssl | Java | IoT | 发布消息（publish） | SSL |
| java-iot-sub-ssl | Java | IoT | 订阅消息（subscribe）| SSL |
| java-iot-pub-tcp | Java | IoT | 发布消息（publish） | TCP |
| java-iot-sub-tcp | Java | IoT | 订阅消息（subscribe） | TCP |
| python-iot-pub-ssl | Python | IoT | 发布消息（publish） | SSL |
| python-iot-sub-ssl | Python | IoT | 订阅消息（subscribe） | SSL |
| python-iot-pub-tcp | Python | IoT | 发布消息（publish） | TCP |
| python-iot-sub-tcp | Python | IoT | 订阅消息（subscribe） | TCP |

---

附：如何查看AVRO文件的内容？

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