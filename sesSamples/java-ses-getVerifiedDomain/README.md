# java-ses-getVerifiedDomain

## 用途：

获取单个“发信邮件域”的认证状态。

> 在SES中，发送邮件时要指定“发信邮箱”。该邮箱地址必须经过认证，或其所属的邮件域必须经过认证。

| 状态码 | 说明 |
| :-- | :-- |
| 0 | 认证成功 |
| 1 | 认证最终失败 |
| 2 | 认证暂时失败 |
| 3 | 认证中 |
| 4 | 认证未开始 |
| 5 | 认证未创建 |

## 使用方法：

* 第一步：在代码中配置AK/SK。
* 第二步：执行`App::main`方法。
* 第三步：在输出结果中查看单个“发信邮件域”的认证状态。

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

### 第三步：获取单个“发信邮件域”的认证状态。

```java
String domain = "";  // 发信邮件域的域名, 比如: abc.com
GetVerifiedDomainResponse response = client.getVerifiedDomain(domain);
System.out.println(response);
```