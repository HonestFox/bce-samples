# php-iot-generatePassword

## 用途：

为指定的principal重新生成密钥。

## 使用方法：

* 第一步：在`generatePassword.php`中配置AK/SK和endpointName、principalName。
* 第二步：执行命令`php generatePassword.php`。

**执行结果中会包含新密钥。请牢记该密钥，如果丢失则无法找回，只能重新生成密钥。**

---

**认证字符串的算法，请参考：[auth.php](../../authorization/auth.php)**