# php-iotdm-createGroup

## 用途：

在物管理中创建设备组。

## 使用方法：

* 第一步：在`createGroup.php`中配置AK/SK和设备信息。
* 第二步：执行命令`php createGroup.php`。

### 请求参数：

示例1: 设备组：baidu

```json
{
    "uri": "baidu",
    "description": "百度"
}
```

示例2: 设备组：baidu/beijing/kejiyuan

```json
{
    "uri": "baidu-beijing-kejiyuan",
    "description": "百度 北京 科技园"
}
```

### 响应结果：

```json
{
    "id": "8389a42306f440e9ac6e8d4ecfae4987",  // 设备组“kejiyuan”的ID
    "name": "kejiyuan",
    "uri": "baidu-beijing-kejiyuan",  // 具有层级的设备组
    "devices": 0,  // 组内的设备数量
    "parent": "eb72f8b3d2f54c60826694e344fcf288",  // 上层设备组的ID
    "description": "百度 北京 科技园",
    "createTime": "2016-10-28T09:24:25Z"
}
```

---

**认证字符串的算法，请参考：[auth.php](../../authorization/auth.php)**