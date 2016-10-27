# php-iot-authorize

## 用途：

传入三个参数：

* principalUuid
* action
* topic

然后判断`principalUuid`对应的用户是否有权限执行对`topic`执行`action`操作。

**注意：**

* `principalUuid`可通过[php-iot-authenticate](../php-iot-authenticate)获取。
* `action`的可选项有：
    * CONNECT：与broker建立连接
    * CREATE：创建topic
    * SEND：向`topic`发布消息
    * RECEIVE：从`topic`接收消息
    * CONSUME：在clean_session=false时，代替RECEIVE。

## 使用方法：

* 第一步：在`authorize.php`中配置AK/SK。
* 第二步：执行命令`php authorize.php`。

---

**认证字符串的算法，请参考：[auth.php](../../authorization/auth.php)**