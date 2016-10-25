# php-iot-createPrincipal

## 用途：

创建principal。

## 使用方法：

* 第一步：在`createPrincipal.php`中配置AK/SK和endpointName、principalName。
* 第二步：执行命令`php createPrincipal.php`。

**执行结果中，有一个字段`password`，是该身份的密钥。请牢记该密钥，如果丢失则无法找回，只能重新生成新的密钥。**

---

**认证字符串的算法，请参考：[auth.php](../../authorization/auth.php)**