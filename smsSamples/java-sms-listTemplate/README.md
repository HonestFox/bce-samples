# java-sms-listTemplate

## 用途：

获取短信模板列表，其中每一个元素代表一个短信模板，每个模板包含以下内容：

* templateId -- 模板ID
* name -- 模板名
* content -- 模板内容
* status -- 模板状态（VALID/INVALID）
* createTime -- 模板的创建时间
* updateTime -- 模板的更新时间

## 使用方法：

* 第一步：在代码中配置AK/SK。
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

### 第二步：配置AK/SK，并创建SmsClient。

```java
final String AK = "";  // 填写自己的AK（AccessKeyId）
final String SK = "";  // 填写自己的SK（SecretAccessKey）

SmsClientConfiguration configuration = new SmsClientConfiguration();
configuration.setCredentials(new DefaultBceCredentials(AK, SK));
SmsClient client = new SmsClient(configuration);
```

### 第三步：发送请求以得到短信模板列表。

```java
// 获得短信模板
ListTemplateResponse response = client.listTemplate(new SmsRequest());
List<GetTemplateDetailResponse> templates = response.getTemplateList();

// 输出每一个短信模板的信息
for (GetTemplateDetailResponse template : templates) {
    logger.info(template.toString());
    logger.info("templateId: " + template.getTemplateId());
    logger.info("name: " + template.getName());
    logger.info("content: " + template.getContent());
    logger.info("status: " + template.getStatus());
    logger.info("createTime: " + template.getCreateTime());
    logger.info("updateTime: " + template.getUpdateTime());
}
```