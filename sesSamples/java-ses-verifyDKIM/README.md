# java-ses-verifyDKIM

## 用途：

为某个“发信邮件域”设置“DKIM”认证。

> 在SES中，发送邮件时要指定“发信邮箱”，并且该邮箱地址必须经过认证（或者其对应的邮件域经过认证）。
> 
> 如果是认证“发信邮件域”，可以选择是否开启“DKIM”（反垃圾邮件技术之一）。

**注意：接口调用成功之后，SES会创建一条CNAME记录，需要把它添加到域名的DNS解析设置中（CNAME记录详情可进入“管理控制台”查看）。**

## 使用方法：

* 第一步：在代码中配置AK/SK，以及要认证的“发信邮件域”的域名。
* 第二步：执行`App::main`方法。
* 第三步：在输出结果中查看SES生成的CNAME记录值，或进入“管理控制台”查看该TXT记录的详情。

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

### 第三步：对指定的“发信邮件域”进行DKIM认证。

```java
String domain = "发信邮件域的域名";  // 比如：abc.com
client.enableDKIM(domain);
VerifyDKIMResponse response = client.verifyDKIM(domain);
System.out.println(response);
```