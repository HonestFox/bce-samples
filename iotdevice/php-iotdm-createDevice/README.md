# php-iotdm-createDevice

## 用途：

在物管理中创建设备。

## 使用方法：

* 第一步：在`createDevice.php`中配置AK/SK和设备信息。
* 第二步：执行命令`php createDevice.php`。

**注意：如果请求中的`bootstrap`参数为`true`，那么在iotdevice中创建设备时，会自动在iothub中添加相应的配置。此时，响应中会包含身份密钥等信息，用户需牢记身份密钥，如果忘记则只能重新生成。**

---

**认证字符串的算法，请参考：[auth.php](../../authorization/auth.php)**