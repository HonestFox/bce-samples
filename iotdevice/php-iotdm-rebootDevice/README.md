# php-iotdm-rebootDevice

## 用途：

在物管理中重启设备，并向设备端`$baidu/iot/device/{deviceName}`发布消息。

## 使用方法：

* 第一步：在`rebootDevice.php`中配置AK/SK和要重启的设备名称。
* 第二步：执行命令`php rebootDevice.php`。

**设备端接收到的消息如下：**

```json
{
    "requestId":"6b02895f-da0d-453c-8bb9-14fd30dc1637",
    "method":"put",
    "path":"/v1/{deviceName}?reboot"
}
```

---

**认证字符串的算法，请参考：[auth.php](../../authorization/auth.php)**