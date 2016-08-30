# java-ses-getVerifiedEmail

## 用途：

获取单个“发信邮箱”的认证状态。

> 在SES中，发送邮件时要指定“发信邮箱”，并且该邮箱地址必须事先经过验证才可以。

| 状态码 | 描述 |
| :-- | :-- |
| 0 | 认证通过 |
| 1 | 认证失败 |
| 2 | 暂时失败 |
| 3 | 认证中 |
| 4 | 认证未开始 |
| 5 | 不存在 |


## 使用方法：

* 第一步：在代码中配置AK/SK，并指定要查询的“发信邮箱”。
* 第二步：执行`App::main`方法。
* 第三步：在输出结果中查看该“发信邮箱”的认证状态。

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

### 第三步：获取单个“发信邮箱”的认证状态。

```java
GetVerifiedEmailResponse email = client.getVerifiedEmail("发信邮箱的地址");
String format = "发信邮箱: %s, 状态码: %d";
System.out.println(String.format(format, email.getDetail().getAddress(), email.getDetail().getStatus()));
```