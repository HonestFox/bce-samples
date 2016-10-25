# php-iot-authenticate

## 用途：

传入一对username/password，然后做以下两件事：

* 判断username/password是否有效
* 如果有效，获得它们所对应的endpointName、endpointUuid、principalUuid。

## 使用方法：

* 第一步：在`authenticate.php`中配置AK/SK。
* 第二步：执行命令`php authenticate.php`。

---

**认证字符串的算法，请参考：[auth.php](../../authorization/auth.php)**