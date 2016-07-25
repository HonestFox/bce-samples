# php-sms-sendMessage

## 用途：

通过PHP调用SMS服务的API，来实现发送短信的功能。

## 使用方法：

* 第一步：在`sendMessage.php`中配置AK、SK、签名过期时间等。

    ```php
    $AK = "";  // AccessKeyID
    $SK = "";  // SecretAccessKey
    ```
    
* 第二步：在`sendMessage.php`中配置短信模板ID、模板中的参数、接收者手机号码等。

    ```php
    $body = array(
        "templateId" => "smsTpl:e7476122a1c24e37b3b0de19d04ae900",  // 内置短信模板，内容为：您的验证码是${code}
        "contentVar" => json_encode(array("code" => "12345")),  // 设置模板中的参数值
        "receiver" => array("手机号码1", "手机号码2")  // 支持多个手机号码
);
    ```
    
* 第三步：执行PHP文件：`php sendMessage.php`。