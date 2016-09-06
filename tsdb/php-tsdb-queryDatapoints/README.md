# php-tsdb-queryDatapoints

## 用途：

从TSDB中查询数据点。

**查询数据点有两种请求方式：GET 或 PUT。**

## 使用GET请求：

### 使用方法：

* 第一步：在`queryDatapoints-via-get.php`中配置AK/SK、endpoint以及查询条件。
* 第二步：执行命令`php queryDatapoints-via-get.php`。

### 代码简介：

#### 第一步：配置AK/SK、endpoint和查询条件，生成认证字符串。

```php
require "../../authorization/auth.php";

$ak = "";  // AccessKeyID
$sk = "";  // SecretAccessKey
$endpoint = "";  // TSDB连接地址，如：{database}.tsdb.iot.gz.baidubce.com

$method = "GET";
$uri = "/v1/datapoint";
$queryConditions = array(
    "queries" => array(
        array(
            "metric" => "cpu_idle",
            "filters" => array(
                "start" => "10 days ago",
                "tags" => array(
                    "host" => array("server1", "server2")
                )
            )
        )
    )
);
$params = array(
    "query" => json_encode($queryConditions)
);

date_default_timezone_set("UTC");
$timestamp = new \DateTime();
$expirationInSeconds = 3600;

$authorization = generateAuthorization($ak, $sk, $method, $endpoint, $uri, $params, $timestamp, $expirationInSeconds);
print("authorization: {$authorization}\n");
```

#### 第二步：构造HTTP请求的URL、Header。

```php
$queryStr = HttpUtil::getCanonicalQueryString($params);
$url = "http://{$endpoint}{$uri}?{$queryStr}";
$timeStr = $timestamp->format("Y-m-d\TH:i:s\Z");
$head = array(
    "Content-Type:application/json",
    "Authorization:{$authorization}",
    "x-bce-date:{$timeStr}"
);
```

#### 第三步：发送HTTP请求，查询数据点。

```php
$curlp = curl_init();
curl_setopt($curlp, CURLOPT_URL, $url);
curl_setopt($curlp, CURLOPT_HTTPHEADER, $head);

curl_setopt($curlp, CURLOPT_RETURNTRANSFER, 1);
$response = curl_exec($curlp);
$status = curl_getinfo($curlp, CURLINFO_HTTP_CODE);
curl_close($curlp);

print("status: {$status}\n");
print("response: {$response}\n");
```

## 使用PUT请求：

### 使用方法：

* 第一步：在`queryDatapoints-via-put.php`中配置AK/SK、endpoint以及查询条件。
* 第二步：执行命令`php queryDatapoints-via-put.php`。

### 代码简介：

#### 第一步：配置AK/SK和endpoint，生成认证字符串。

```php
require "../../authorization/auth.php";

$ak = "";  // AccessKeyID
$sk = "";  // SecretAccessKey
$endpoint = "";  // TSDB连接地址，如：{database}.tsdb.iot.gz.baidubce.com

$method = "PUT";
$uri = "/v1/datapoint";
$params = array("query" => "");

date_default_timezone_set("UTC");
$timestamp = new \DateTime();
$expirationInSeconds = 3600;

$authorization = generateAuthorization($ak, $sk, $method, $endpoint, $uri, $params, $timestamp, $expirationInSeconds);
print("authorization: {$authorization}\n");
```

**认证字符串的生成方式，请参考：[auth.php](../../authorization/auth.php)**

#### 第二步：构造HTTP请求的URL、Header、Body。

```php
$url = "http://{$endpoint}{$uri}?query";
$timeStr = $timestamp->format("Y-m-d\TH:i:s\Z");
$head = array(
    "Content-Type:application/json",
    "Authorization:{$authorization}",
    "x-bce-date:{$timeStr}"
);
$body = array(
    "queries" => array(
        array(
            "metric" => "cpu_idle",
            "filters" => array(
                "start" => "10 days ago",
                "tags" => array(
                    "host" => array("server1", "server2")
                )
            )
        )
    )
);
```

#### 第三步：发送HTTP请求，查询数据点。

```php
$curlp = curl_init();
curl_setopt($curlp, CURLOPT_CUSTOMREQUEST, $method);
curl_setopt($curlp, CURLOPT_URL, $url);
curl_setopt($curlp, CURLOPT_HTTPHEADER, $head);
curl_setopt($curlp, CURLOPT_POSTFIELDS, json_encode($body));

curl_setopt($curlp, CURLOPT_RETURNTRANSFER, 1);
$response = curl_exec($curlp);
$status = curl_getinfo($curlp, CURLINFO_HTTP_CODE);
curl_close($curlp);
```

### 执行结果如下：

```json
status: 200
response: 
{
    "results":[
        {
            "metric":"cpu_idle",
            "rawCount":9,
            "groups":[
                {
                    "groupInfos":[
                        {
                            "name":"Type",
                            "type":"Number"
                        }
                    ],
                    "values":[
                        [1472704190853,0.100000],
                        [1472704220846,0.100000],
                        [1472704532973,0.100000],
                        [1472704532989,99],
                        [1472704591646,0.100000],
                        [1472704591679,99],
                        [1472704618171,0.100000]
                    ]
                },
                {
                    "groupInfos":[
                        {
                            "name":"Type",
                            "type":"String"
                        }
                    ],
                    "values":[
                        [1472704532989,"hello world"],
                        [1472704591679,"hello world"]
                    ]
                }
            ]
        }
    ]
}
```