# java-tsdb-getTags

## 用途：

获取TSDB中与某个metric相关的所有tags。

## 使用方法：

* 第一步：在代码中配置AK/SK和endpoint、metric。
* 第二步：执行`App::main`方法。

## 代码简介：

### 第一步：新建Java工程，并导入bce-java-sdk。

`bce-java-sdk`下载链接：[https://cloud.baidu.com/doc/Developer/index.html](https://cloud.baidu.com/doc/Developer/index.html)

### 第二步：配置AK/SK和endpoint、metric，创建TsdbClient。

```java
String ak = "";  // AccessKeyID
String sk = "";  // SecretAccessKey
String endpoint = "";  // {database}.tsdb.iot.gz.baidubce.com
String metric = "";  // 查询tags时必须要指定metric

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

### 第三步：查询与metric相关的所有tags。

```java
GetTagsResponse response = client.getTags(metric);
for (Map.Entry<String, List<String>> entry : response.getTags().entrySet()) {
    String tagKey = entry.getKey();
    List<String> tagValue = entry.getValue();
    System.out.println(tagKey + " : " + tagValue);
}
```