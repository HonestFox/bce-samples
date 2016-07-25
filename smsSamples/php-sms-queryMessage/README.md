# php-sms-queryMessage

## 用途：

通过PHP调用SMS服务的API，根据指定的“短信流水号”来查询短信的详细信息。

## 使用方法：

* 第一步：在`queryMessage.php`中配置AK、SK、短信流水号。

    ```php
    $AK = "";  // AccessKeyID
    $SK = "";  // SecretAccessKey
    $messageId = "短信流水号";
    ```
    
* 第二步：执行PHP文件：`php queryMessage.php`。