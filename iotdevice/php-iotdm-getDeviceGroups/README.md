# php-iotdm-getDeviceGroups

## 用途：

查询device设备组。

**所谓device设备组，必须满足以下条件之一：**

* 在group层级中是叶节点。
* 非叶节点，但该组内至少有一个device。

## 使用方法：

* 第一步：在`getDeviceGroups.php`中配置AK/SK。
* 第二步：执行命令`php getDeviceGroups.php`。

---

**认证字符串的算法，请参考：[auth.php](../../authorization/auth.php)**