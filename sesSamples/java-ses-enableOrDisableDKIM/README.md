# java-ses-enableOrDisableDKIM

## 用途：

为某个“发信邮件域”启用或禁用DKIM。

> 在SES中，发送邮件时要指定“发信邮箱”，并且该邮箱地址必须经过认证（或者其对应的邮件域经过认证）。
> 
> 如果是认证“发信邮件域”，可以选择是否开启“DKIM”（反垃圾邮件技术之一）。

一般建议以下列方式使用：

* 认证“发信邮件域”，并启用DKIM。

    ```java
    client.verifyDomain("abc.com");  // 生成一条TXT记录，需手动添加到DNS解析中
    client.enableDKIM("abc.com");  // 启用DKIM
    client.verifyDKIM("abc.com");  // 生成一条CNAME记录，需手动添加到DNS解析中
    ```
    
* 认证“发信邮件域”，但禁用DKIM。
    
    ```java
    client.verifyDomain("abc.com");  // 生成一条TXT记录，需手动添加到DNS解析中
    client.disableDKIM("abc.com");  // 禁用DKIM
    ```

## 使用方法：

* 第一步：在代码中配置AK/SK，以及要认证的“发信邮件域”的域名。
* 第二步：执行`App::main`方法。
* 第三步：进入“管理控制台”查看DKIM的状态。

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

### 第三步：认证“发信邮件域”。

```java
String domain = "发信邮件域的域名";
//        client.enableDKIM(domain);  // 启用DKIM
client.disableDKIM(domain);  // 禁用DKIM
```