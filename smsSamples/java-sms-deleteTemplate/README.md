# java-sms-deleteTemplate

## 用途：

删除短信模板。

## 使用方法：

* 第一步：配置AK/SK以及要删除的短信模板ID。
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

### 第二步：配置AK/SK，创建SmsClient。

```java
final String AK = "";
final String SK = "";

SmsClientConfiguration configuration = new SmsClientConfiguration();
configuration.setCredentials(new DefaultBceCredentials(AK, SK));
SmsClient client = new SmsClient(configuration);
```

### 第三步：删除短信模板。

```java
DeleteTemplateRequest request = new DeleteTemplateRequest();
request.setTemplateId("短信模板ID");
client.deleteTemplate(request);
```