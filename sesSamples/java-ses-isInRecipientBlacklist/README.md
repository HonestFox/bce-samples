# java-ses-isInRecipientBlacklist

## 用途：

查询某个邮箱地址是否在“接收邮箱黑名单”。

## 使用方法：

* 第一步：在代码中配置AK/SK，以及要查询的邮箱地址。
* 第二步：执行`App::main`方法。
* 第三步：查看输出结果。

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

### 第三步：查询某个邮箱是否在“接收邮箱黑名单”中。

```java
String email = "";  // 待查询的邮箱地址, 比如: xyz@abc.com
IsInRecipientBlacklistResponse response = client.isInRecipientBlacklist(email);
System.out.println(response);
```