# java-tsdb-writeDatapoint

## 用途：

向TSDB写入数据点。

## 使用方法：

* 第一步：在代码中配置AK/SK和endpoint，以及数据点的信息（metric，tags，timestamp，value）。
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

### 第三步：设置数据点的相关信息。

```java
String metric = "cpu_idle";
String tagKey = "host";
String tagValue = "server1";

List<Datapoint> datapoints = Arrays.asList(new Datapoint()
        .withMetric(metric)
        .addTag(tagKey, tagValue)
        .addDoubleValue(System.currentTimeMillis(), 0.1)
);
```

> 如果有多个数据点具有相同的metric和tags，可以使用下面的方法，以减少请求的payload。
> 
> ```java
List<Datapoint> datapoints = Arrays.asList(new Datapoint()
        .withMetric(metric)
        .addTag(tagKey, tagValue)
        .addDoubleValue(System.currentTimeMillis(), 0.1)
        .addLongValue(System.currentTimeMillis(), 99)
        .addStringValue(System.currentTimeMillis(), "hello world")
);
> ```

### 第四步：写入数据点。

```java
WriteDatapointsResponse response = client.writeDatapoints(datapoints);
System.out.println(response.getMetadata());
```