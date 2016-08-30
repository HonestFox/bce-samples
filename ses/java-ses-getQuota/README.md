# java-ses-getQuota

## 用途：

查询配额。

主要包括：

* maxPerDay - 每日发信配额
* maxPerSecond － 最大发送频率
* usedToday － 今日已发信数量

## 使用方法：

* 第一步：在代码中配置AK/SK。
* 第二步：执行`App::main`方法。
* 第三步：在输出结果中查看配额信息。

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

### 第三步：查询配额。

```java
GetQuotaResponse quota = client.getQuota();
System.out.println("每日发信配额: " + quota.getMaxPerDay());
System.out.println("最大发送频率: " + quota.getMaxPerSecond());
System.out.println("今日已发送: " + quota.getUsedToday());
```