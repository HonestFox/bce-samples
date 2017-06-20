# php-iotdm-deleteDevice-v2

## 用途：

从物管理对应的 iothub 实例中删除 thing（以及 principal、policy、topic）等配置。

## 使用方法：

* 第一步：在`deleteDevice.php`中配置AK/SK和要删除的设备名称。
* 第二步：执行命令`php deleteDevice.php`。

**注意：如果请求中的`cleanThing`参数为`true`，那么在iotdevice中删除设备时，会自动把 iothub 中相应的配置也一并删除掉。**

---

**认证字符串的算法，请参考：[auth.php](../../authorization/auth.php)**