# java-sms-queryQuota

## 使用说明：

* 第一步：在代码中配置AK/SK。
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

### 第三步：查询配额。

```java
QueryQuotaResponse quota = client.queryQuota(new SmsRequest());
logger.info(quota.toString());
logger.info("单日发送配额: " + quota.getMaxSendPerDay());
logger.info("单日单终端接收配额: " + quota.getMaxReceivePerPhoneNumberDay());
logger.info("当日已发送: " + quota.getSentToday());
```