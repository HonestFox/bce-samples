# java-sms-getTemplate

## 用途：

获取单个短信模板的信息，主要包括：

* 模板ID
* 模板名
* 模板内容
* 模板状态（VALID/INVALID）
* 模板创建时间
* 模板更新时间

## 使用说明：

* 第一步：在代码中配置AK/SK，以及需要查询的短信模板ID。
* 第二步：执行`App::main`方法。

## 代码简介：

### 第一步：添加`bce-java-sdk`依赖。

```xml
<dependency>
    <groupId>com.baidubce</groupId>
    <artifactId>bce-java-sdk</artifactId>
    <version>LATEST</version>
</dependency>
```

### 第二步：配置AK/SK和短信模板ID，并创建SmsClient。

```java
final String AK = "";
final String SK = "";
final String templateId = "";

SmsClientConfiguration configuration = new SmsClientConfiguration();
configuration.setCredentials(new DefaultBceCredentials(AK, SK));
SmsClient client = new SmsClient(configuration);
```

### 第三步：创建请求，获取短信模板。

```java
GetTemplateDetailRequest request = new GetTemplateDetailRequest();
request.setTemplateId(templateId);
GetTemplateDetailResponse template = client.getTemplateDetail(request);

logger.info(template.toString());
logger.info("templateId: " + template.getTemplateId());
logger.info("name: " + template.getName());
logger.info("content: " + template.getContent());
logger.info("status: " + template.getStatus());
logger.info("createTime: " + template.getCreateTime());
logger.info("updateTime: " + template.getUpdateTime());
```