# php-iot-deletePermission

## 用途：

删除指定的topic。

## 使用方法：

* 第一步：在`deletePermission.php`中配置AK/SK，以及endpointName、permissionUuid。
* 第二步：执行命令`php deletePermission.php`。

**注意：如果是通过 [php-iot-createPermission](./php-iot-createPermission) 创建的topic，可以从执行结果中获取permissionUuid。或者通过 [php-iot-getPermissions](../php-iot-getPermissions) 查询。**

---

**认证字符串的算法，请参考：[auth.php](../../authorization/auth.php)**