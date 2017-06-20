# php-iotdm-createDevice-v2

## 用途：

在“物管理创建的 iothub 实例”中创建 thing、principal、policy、topic 等配置。

## 使用方法：

* 第一步：在`createDevice.php`中配置 AK/SK、iothub 实例名称、设备信息。
* 第二步：执行命令`php createDevice.php`。

**注意：如果请求中的`bootstrap`参数为`true`，那么在iotdevice中创建设备时，会自动在iothub中添加相应的配置。此时，响应中会包含身份密钥等信息，用户需牢记身份密钥，如果忘记则只能重新生成。**

---

**认证字符串的算法，请参考：[auth.php](../../authorization/auth.php)**