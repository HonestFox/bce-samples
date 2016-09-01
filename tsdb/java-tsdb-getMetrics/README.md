# java-tsdb-getMetrics

## 用途：

获取TSDB中的所有metrics。

## 使用方法：

* 第一步：在代码中配置AK/SK和endpoint。
* 第二步：执行`App::main`方法。

## 代码简介：

### 第一步：新建Java工程，并导入bce-java-sdk。

`bce-java-sdk`下载链接：[https://cloud.baidu.com/doc/Developer/index.html](https://cloud.baidu.com/doc/Developer/index.html)

### 第二步：配置AK/SK和endpoint，创建TsdbClient。

```java
String ak = "";  // AccessKeyID
String sk = "";  // SecretAccessKey
String endpoint = "";  // TSDB的连接地址, 如: {database}.tsdb.iot.gz.baidubce.com

BceClientConfiguration config = new BceClientConfiguration()
        .withCredentials(new DefaultBceCredentials(ak, sk))
        .withEndpoint(endpoint);
TsdbClient client = new TsdbClient(config);
```

> 默认使用的是HTTP协议，如果希望使用HTTPS，可以使用下面的方法。
>
> ```java
BceClientConfiguration config = new BceClientConfiguration()
        .withProtocol(Protocol.HTTPS)
        .withCredentials(new DefaultBceCredentials(ak, sk))
        .withEndpoint(endpoint);
TsdbClient client = new TsdbClient(config);
> ```

### 第三步：查询所有metrics。

```java
GetMetricsResponse response = client.getMetrics();
for (String metric : response.getMetrics()) {
    System.out.println(metric);
}
```