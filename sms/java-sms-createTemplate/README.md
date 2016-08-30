# java-sms-createTemplate

## 用途：

创建短信模板。

**注意：新创建的短信模板需要经过审核才能使用。**

## 使用方法：

* 第一步：在代码中配置AK/SK，填写要创建的短信模板的名称和内容。
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

### 第三步：填写要创建的短信模板的名称和内容，从返回结果中获取短信模板ID。

```java
CreateTemplateRequest request = new CreateTemplateRequest();
request.setName("模板名称");
request.setContent("模板内容");

CreateTemplateResponse response = client.createTemplate(request);
logger.info(response.toString());
logger.info("模板ID: " + response.getTemplateId());
```

**短信模板内容示例：**

```java
您的验证码是：${code}。
```

其中，`${code}`表示模板参数，其中参数的名字是`code`。