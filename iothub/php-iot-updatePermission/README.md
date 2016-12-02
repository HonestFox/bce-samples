# php-iot-updatePermission

## 用途：

修改指定的topic信息。

## 使用方法：

* 第一步：在`updatePermission.php`中配置AK/SK，以及endpointName、permissionUuid、主题及相应权限。
* 第二步：执行命令`php updatePermission.php`。

**注意：如果是通过 [php-iot-createPermission](../php-iot-createPermission) 创建的topic，可以从执行结果中获取 permissionUuid。或者通过 [php-iot-getPermissions](../php-iot-getPermissions) 查询所有topic的 permissionUuid。**

---

**认证字符串的算法，请参考：[auth.php](../../authorization/auth.php)**