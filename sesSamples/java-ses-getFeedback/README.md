# java-ses-getFeedback

## 用途：

获取“退信通知”的接收邮箱。

## 使用方法：

* 第一步：在代码中配置AK/SK。
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

### 第三步：获取“退信通知”的接收邮箱。

```java
GetFeedbackResponse response = client.getFeedback();
System.out.println(response);
```