# java-sms-queryMessageDetail

## 使用说明：

* 第一步：在代码中配置AK/SK、短信模板ID、模板参数值、接收者手机号码。
* 第二步：执行`App::main`方法。

## 代码简介：

### 第一步：添加`bce-java-sdk`依赖。

```xml
<dependency>
    <groupId>com.baidubce</groupId>
    <artifactId>bce-java-sdk</artifactId>
    <version>LATEST</version>
</dependency>
```

### 第二步：发送短信，并获取messageId。

发送短信相关的代码详解，可以参考：[smsSamples/java-sms-send](https://github.com/floodliu/bceSamples/tree/master/smsSamples/java-sms-send)

### 第三步：根据messageId，查询短信信息。

```java
// 查询短信信息
QueryMessageDetailRequest queryRequest = new QueryMessageDetailRequest();
queryRequest.setMessageId(response.getMessageId());
QueryMessageDetailResponse queryResponse = client.queryMessageDetail(queryRequest);

logger.info(queryResponse.toString());
logger.info("messageId: " + queryResponse.getMessageId());
logger.info("content: " + queryResponse.getContent());
logger.info("receiver: " + queryResponse.getReceiver());
logger.info("sendTime: " + queryResponse.getSendTime());
```