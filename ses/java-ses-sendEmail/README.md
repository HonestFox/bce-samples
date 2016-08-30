# java-ses-sendEmail

## 用途：

发送邮件。

SDK提供了多个“发送邮件”的接口：

```java
SendEmailResponse sendEmail(String from, String[] toAddr, String subject, String body, File...attachmentFiles);
SendEmailResponse sendEmail(String from, String displayName, String[] toAddr, String subject, String body, File...attachmentFiles);
SendEmailResponse sendEmail(String from, String displayName, String[] toAddr, String[] ccAddr, String[] bccAddr, String subject, String body, File...attachmentFiles);
SendEmailResponse sendEmail(String from, String displayName, String returnPath, String replyTo, String[] toAddr, String[] ccAddr, String[] bccAddr, String subject, String body, int priority, int charset, File...attachmentFiles);
SendEmailResponse sendEmail(SendEmailRequest request);
```

这里只演示第一个接口的用法。

## 使用方法：

* 第一步：在代码中配置AK/SK，并设置发信邮箱、收信邮箱、邮件标题、邮件正文等。
* 第二步：执行`App::main`方法。
* 第三步：在输出结果中查看所发送邮件的流水号。

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

### 第三步：发送邮件。

```java
String fromAddress = "";  // 发信邮箱
String[] toAddresses = {"收信邮箱1", "收信邮箱2"};  // 收信邮箱
String subject = "";  // 邮件标题
String body = "";  // 邮件正文
SendEmailResponse response = client.sendEmail(fromAddress, toAddresses, subject, body);
System.out.println(String.format("邮件流水号: %s", response.getMessageId()));
```