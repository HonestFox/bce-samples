# c-iot-pub-ssl

## 用途：

编写C代码，使用SSL方式连接到百度IoT服务，然后向指定的主题发布消息。

**注意：需提前开通百度IoT服务，并配置好 IoT实例、设备、身份、策略、主题等。**

## 使用方法：

代码请参考官方文档：[C代码示例](https://cloud.baidu.com/doc/IOT/IOTService.html)

**注意：编译代码前，需要在源文件`src/PublisherSync.c`中配置您自己的用户名和密码。**

即修改下面这两行：

```c
#define USER "设备全名"
#define PWD  "身份密钥"
```

之后按照官方文档操作即可。