# java-ses-deleteVerifiedEmail

## 用途：

删除单个“发信邮箱”。

> 在SES中，发送邮件时要指定“发信邮箱”，并且该邮箱地址必须事先经过验证才可以。

**注意：不管该“发信邮箱”处于什么状态，在被删除之后，都不能再被用作“发信邮箱”。如果需要重新使其作为“发信邮箱”，需要重新认证。**


## 使用方法：

* 第一步：在代码中配置AK/SK，并指定要删除的“发信邮箱”。
* 第二步：执行`App::main`方法。

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

### 第三步：删除单个“发信邮箱”。

```java
client.deleteVerifiedEmail("发信邮箱的地址");
```