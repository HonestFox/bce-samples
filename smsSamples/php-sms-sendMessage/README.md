# php-sms-send

编写PHP代码来发送HTTP请求，调用SMS的API来发送短信。

## 使用方法：

* 第一步：在代码中配置AK、SK、签名过期时间等。

    ```php
    $AK = "";  // 填写AK（AccessKeyId）
    $SK = "";  // 填写SK（SecretAccessKey）
    $expirationPeriodInSeconds = "3600";  // 设置签名过期时间（单位：秒）
    ```
    
* 第二步：在代码中配置短信模板ID、模板中的参数、接收者手机号码等。

    ```php
    $data = array(
    'templateId' => 'smsTpl:e7476122a1c24e37b3b0de19d04ae900',  // 内置短信模板，内容为：您的验证码是${code}
    'contentVar' => json_encode(array('code' => '12345')),  // 设置模板中的参数值
    'receiver' => array('手机号码1', '手机号码2')  // 支持多个手机号码
    );
    ```
    
* 第三步：执行PHP文件：`php smsSend.php`