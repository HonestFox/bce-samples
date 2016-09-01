# java-tsdb-queryDatapoints

## 用途：

从TSDB中查询数据点。

## 使用方法：

* 第一步：在代码中配置AK/SK、endpoint和查询条件。
* 第二步：执行`App::main`方法。

## 代码简介：

### 第一步：新建Java工程，并导入bce-java-sdk。

`bce-java-sdk`下载链接：[https://cloud.baidu.com/doc/Developer/index.html](https://cloud.baidu.com/doc/Developer/index.html)

### 第二步：配置AK/SK和endpoint，创建TsdbClient。

```java
String ak = "";  // AccessKeyID
String sk = "";  // SecretAccessKey
String endpoint = "";  // {database}.tsdb.iot.gz.baidubce.com

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
String metric = "cpu_idle";
String tagKey = "host";
String tagValue = "server1";
List<Query> queries = Arrays.asList(new Query()
        .withMetric(metric)
        .withFilters(new Filters()
                .withRelativeStart("7 days ago")
                .addTag(tagKey, tagValue)
        )
);
```

更详细的查询条件设置方法可参考[官方文档](https://cloud.baidu.com/doc/TSDB/Java-SDK.html#.E6.9F.A5.E8.AF.A2.E6.93.8D.E4.BD.9C)

### 第四步：查询并显示数据点。

```java
QueryDatapointsResponse response = client.queryDatapoints(queries);
for (Result result : response.getResults()) {
    System.out.println("Result:");

    for (Group group : result.getGroups()) {
        System.out.println("\tGroup:");

        for (GroupInfo info : group.getGroupInfos()) {
            System.out.println("\t\tGroupInfo:");
            System.out.println("\t\t\tName: " + info.getName());
        }

        System.out.println("\t\tTimeAndValue:");
        for (Group.TimeAndValue timeAndValue : group.getTimeAndValueList()) {
            if (timeAndValue.isDouble()) {
                System.out.println("\t\t\t[" + timeAndValue.getTime() + ", " + timeAndValue.getDoubleValue()
                        + "]");
            } else if (timeAndValue.isLong()) {
                System.out.println("\t\t\t[" + timeAndValue.getTime() + ", " + timeAndValue.getLongValue() +
                        "]");
            } else if (timeAndValue.isString()) {
                System.out.println("\t\t\t[" + timeAndValue.getTime() + ", " + timeAndValue.getStringValue() +
                        "]");
            }
        }
    }
}
```