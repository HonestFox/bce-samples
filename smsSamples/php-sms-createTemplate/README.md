# php-sms-createTemplate

## 用途：

通过PHP调用SMS服务的API，来创建短信模板。

**注意：此时创建的模板处于待审核的状态，必须要审核通过之后才可以使用。**

## 使用方法：

* 第一步：在`sendMessage.php`中配置AK、SK、签名过期时间等。

    ```php
    $AK = "";  // AccessKeyID
    $SK = "";  // SecretAccessKey
    ```
    
* 第二步：在`createTemplate.php`中配置模板名称、内容。

    ```php
    $body = array(
        "name" => "您的模板名称",  // 模板名字，不能重复。
        "content" => '您的模板内容，模板参数形式为：${code}。'  // 模板内容，建议使用单引号，否则模板参数${code}的会被解析成变量。
);
    ```
    
    **注意：模板参数的形式为 `${code}`，其中`code`是参数的名字。**
    
* 第三步：执行PHP文件：`php createTemplate.php`。