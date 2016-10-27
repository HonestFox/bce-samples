# php-iotdm-deleteDevice

## 用途：

从物管理中删除设备。

## 使用方法：

* 第一步：在`deleteDevice.php`中配置AK/SK和要删除的设备名称。
* 第二步：执行命令`php createDevice.php`。

**注意：如果请求中的`cleanThing`参数为`true`，那么在iotdevice中删除设备时，会自动把iothub中相应的配置也一并删除掉。**

---

**认证字符串的算法，请参考：[auth.php](../../authorization/auth.php)**