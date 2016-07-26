# php-sms-getTemlateList

## 用途：

通过PHP调用SMS服务的API，来查询所有短信模板。

## 使用方法：

* 第一步：在`getTemplateList.php`中配置AK、SK、签名过期时间等。

    ```php
    $AK = "";  // AccessKeyID
    $SK = "";  // SecretAccessKey
    ```
    
* 第二步：执行PHP文件：`php getTemplateList.php`。