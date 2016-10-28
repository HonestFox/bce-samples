# php-iotdm-updateGroup

## 用途：

更新设备组的信息（包括：名称、描述、父节点）。

## 使用方法：

* 第一步：在`updateGroup.php`中配置AK/SK和设备信息。
* 第二步：执行命令`php updateGroup.php`。

## 参数举例：

```json
{
    "name": "新的设备组名称",
    "parent": "新的上层设备组ID",
    "description": "新的设备组描述信息"
}
```

**注意：以上3个参数都是可选的，但至少要提供其中一个参数。**

---

**认证字符串的算法，请参考：[auth.php](../../authorization/auth.php)**