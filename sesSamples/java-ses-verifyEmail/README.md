# java-ses-verifyEmail

## 用途：

验证“发信邮箱”。

> 在SES中，发送邮件时要指定“发信邮箱”，并且该邮箱地址必须事先经过验证才可以。

**注意：接口调用成功之后，SES会向该邮箱地址发送一封“验证邮件”，需要手动点击该邮件中的链接来完成认证。**

## 使用方法：

* 第一步：在代码中配置AK/SK，以及要验证的“发信邮箱”地址。
* 第二步：执行`App::main`方法。
* 第三步：在“发信邮箱”中查找SES的“验证邮件”，点击其中的链接完成认证。

## 代码简介：

### 第一步：添加`bce-java-sdk`依赖。

```xml
<dependency>
    <groupId>com.baidubce</groupId>
    <artifactId>bce-java-sdk</artifactId>
    <version>0.10.6</version>
</dependency>
```

### 第二步：配置AK/SK，创建SesClient。

```java
String AK = "";  // AccessKeyId
String SK = "";  // SecretAccessKey

SesClientConfiguration configuration = new SesClientConfiguration();
configuration.setCredentials(new DefaultBceCredentials(AK, SK));
SesClient client = new SesClient(configuration);
```

### 第三步：验证“发信邮箱”。

```java
client.verifyEmail("发信邮箱的地址");
```