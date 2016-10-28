# php-iotdm-getSubGroup

## 用途：

根据指定的设备组ID，查询它的所有子节点列表。

**比如：设备组的层次结构为：`baidu-beijing-kejiyuan`，那么对于设备组`baidu`而言，`beijing`就是它的子节点。**

## 使用方法：

* 第一步：在`getSubGroup.php`中配置AK/SK和设备组ID。
* 第二步：执行命令`php getSubGroup.php`。

---

**认证字符串的算法，请参考：[auth.php](../../authorization/auth.php)**