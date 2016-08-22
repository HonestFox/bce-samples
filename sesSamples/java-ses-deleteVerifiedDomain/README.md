# java-ses-deleteVerifiedDomain

## 用途：

删除单个“发信邮件域”。

> 在SES中，发送邮件时要指定“发信邮箱”，该邮箱必须经过认证，或其对应的邮件域经过认证。

**不管“发信邮件域”处于什么状态，在删除后，其包含的所有邮箱都不能再被用作“发信邮箱”。如果需要再次使用该“发信邮件域”，需重新认证。**

## 使用方法：

* 第一步：在代码中配置AK/SK，并指定要删除的“发信邮件域”。
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
String domain = "";  // 发信邮件域的域名, 比如: abc.com
client.deleteVerifiedDomain(domain);
```