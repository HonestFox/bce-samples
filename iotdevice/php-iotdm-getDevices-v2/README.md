# php-iotdm-getDevices-v2

## 用途：

根据指定的条件，在物管理中查找符合该条件的所有设备。

## 使用方法：

* 第一步：在`getDevices.php`中配置 AK/SK 和查询条件。
* 第二步：执行命令`php getDevices.php`。

## 查询条件：

请参考[官方文档](https://cloud.baidu.com/doc/IOTDM/API.html#DeviceProfile.E5.8F.82.E6.95.B0.E5.88.97.E8.A1.A8)中的**DeviceProfile**和**DeviceAttributes**对象。

分页条件请参考[官方文档](https://cloud.baidu.com/doc/IOTDM/API.html#DeviceProfile.E5.8F.82.E6.95.B0.E5.88.97.E8.A1.A8)中的**Device.Page**对象。

查询条件举例：

```json
{
    "condition": {
        "id": "设备ID",
        "name": "设备名称",
        "parent": "设备组ID",
        "description": "设备描述信息",
        "createTime": "设备创建时间",
        "state": "设备状态",
        "enable": "启用or禁用",
        "lastActiveTime": "最后一次的活跃时间",
        "attributes.region": "",  // attributes是一个JSON对象，region是它内部的一个key，通过点“.”来指定层级关系。
        "device.reported.color": ""  // device是一个DeviceAttributes对象，reported是它内部的一个key，而color是更深层次的一个key，通过点“.”来指定层级关系。
    },
    "page": {
        "orderBy": "name",
        "order": "asc",
        "pageNo": 1,  // 1为第一页
        "pageSize": 10
    }
}
```

**需要注意的是，实际在调用API的时候，`condition`对应的值并不是一个JSON对象，而是把这个JSON对象编码成了字符串格式。详情请参考代码。**

---

**认证字符串的算法，请参考：[auth.php](../../authorization/auth.php)**