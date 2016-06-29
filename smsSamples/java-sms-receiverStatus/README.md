# java-sms-receiverStatus

## 用途：

查询单终端用户接收的短信信息，主要包括：

* 单终端用户每日接收短信的配额
* 某个终端用户今日已经收到的短信数量

## 使用说明：

* 第一步：在代码中配置AK/SK以及要查询的手机号码。
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

### 第二步：配置AK/SK，并创建SmsClient。

```java
final String AK = "";
final String SK = "";

SmsClientConfiguration configuration = new SmsClientConfiguration();
configuration.setCredentials(new DefaultBceCredentials(AK, SK));
SmsClient client = new SmsClient(configuration);
```

### 第三步：查询单终端用户的短信信息。

```java
StatReceiverRequest request = new StatReceiverRequest();
request.setPhoneNumber("手机号码");  // 填写要查询的手机号码
StatReceiverResponse response = client.statReceiver(request);

logger.info(response.toString());
logger.info("每日接收配额: " + response.getMaxReceivePerPhoneNumberDay());
logger.info("今日已接收短信: " + response.getReceivedToday());
```