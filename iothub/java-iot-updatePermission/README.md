# java-iot-updatePermission

## 用途：

修改指定的topic信息。

## 使用方法：

* 第一步：在`App.java`中配置AK/SK，以及endpointName、permissionUuid、主题及相应权限。
* 第二步：执行`App::main`方法。

**注意：如果是通过 [java-iot-createPermission](../java-iot-createPermission) 创建的topic，可以从执行结果中获取 permissionUuid。或者通过 [java-iot-getPermissions](../java-iot-getPermissions) 查询所有topic的 permissionUuid。**

---

bce-java-sdk 下载链接：[https://cloud.baidu.com/doc/Downloadcenter/Java.html](https://cloud.baidu.com/doc/Downloadcenter/Java.html)