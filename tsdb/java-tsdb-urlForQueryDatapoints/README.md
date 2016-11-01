# java-tsdb-urlForQueryDatapoints

## 用途：

生成一个预签名的URL，前端可以使用这个URL来查询数据点。

## 使用方法：

* 第一步：在代码中配置AK/SK、endpoint和查询条件。
* 第二步：执行`App::main`方法。

## 代码简介：

### 第一步：新建Java工程，并导入bce-java-sdk。

`bce-java-sdk`下载链接：[https://cloud.baidu.com/doc/Developer/index.html](https://cloud.baidu.com/doc/Developer/index.html)

### 第二步：配置AK/SK和endpoint，创建TsdbClient。

```java
String ak = "";  // AccessKeyId
String sk = "";  // SecretAccessKey
String endpoint = "";  // {dbName}.tsdb.iot.gz.baidubce.com

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

### 第三步：设置查询条件。

```java
String metric = "pm25";
List<Query> queries = Arrays.asList(new Query()
        .withMetric(metric)
        .withFilters(new Filters()
                .withRelativeStart("1 year ago"))
        .addGroupBy(new GroupBy()
                .withName(TsdbConstants.GROUP_BY_NAME_TIME)
                .withTimeRangeSize("1 second")
                .withGroupCount(1))
        .withLimit(100)
        .addAggregator(new Aggregator()
                .withName(TsdbConstants.AGGREGATOR_NAME_SUM)
                .withSampling("1 second")));
```

更详细的查询条件设置方法可参考[官方文档](https://cloud.baidu.com/doc/TSDB/Java-SDK.html#.E6.9F.A5.E8.AF.A2.E6.93.8D.E4.BD.9C)

### 第四步：生成预签名的URL。

```java
Integer expirationInSeconds = 1800;
URL url = client.generatePresignedUrlForQueryDatapoints(queries, expirationInSeconds);
logger.info(url.toString());
```