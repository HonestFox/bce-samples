# java-sms-send

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

### 第二步：配置AK/SK，并创建SmsClient。

```java
final String AK = "";  // 填写自己的AK（AccessKeyId）
final String SK = "";  // 填写自己的SK（SecretAccessKey）

SmsClientConfiguration configuration = new SmsClientConfiguration();
configuration.setCredentials(new DefaultBceCredentials(AK, SK));
SmsClient client = new SmsClient(configuration);
```

### 第三步：配置模板Id、模板参数、接收端手机号码。

```java
final String templateId = "smsTpl:e7476122a1c24e37b3b0de19d04ae900";  // 模板内容: 您的验证码是${code}
final String contentVar = "{\"code\": \"12345\"}";  // 配置模板参数的值, JSON格式。
final List<String> phoneNumbers = Arrays.asList("手机号码1", "手机号码2");  // 支持多个接收端手机号码

SendMessageRequest request = new SendMessageRequest();
request.setTemplateId(templateId);
request.setContentVar(contentVar);
request.setReceiver(phoneNumbers);
```

> 注意：contentVar的值应该是一个表示JSON的字符串，其格式如下：
> 
> ```
> "{
>     \"参数1\": \"参数1的值\",
>     \"参数2\": \"参数2的值\",
>     \"参数3\": \"参数3的值\"
> }"
> ```

### 第四步：发送短信

```java
SendMessageResponse response = client.sendMessage(request);
```