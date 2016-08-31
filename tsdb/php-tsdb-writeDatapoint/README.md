# php-tsdb-writeDatapoint

## 用途：

向TSDB写入数据点。

## 使用方法：

* 第一步：在`writeDatapoint.php`中配置AK/SK和endpoint。
* 第二步：执行命令`php writeDatapoint.php`。

## 代码简介：

### 第一步：配置AK/SK和endpoint，生成认证字符串。

```php
require "../../authorization/auth.php";

$ak = "";  // AccessKeyID
$sk = "";  // SecretAccessKey
$endpoint = "";  // TSDB连接地址，如：{database}.tsdb.iot.gz.baidubce.com

$method = "POST";
$uri = "/v1/datapoint";
$params = array();

date_default_timezone_set("UTC");
$timestamp = new \DateTime();
$expirationInSeconds = 3600;

$authorization = generateAuthorization($ak, $sk, $method, $endpoint, $uri, $params, $timestamp, $expirationInSeconds);
print("authorization: {$authorization}\n");
```

**认证字符串的生成方式，请参考：[auth.php](../../authorization/auth.php)**

### 第二步：构造HTTP请求的URL、Header、Body。

```php
$url = "http://{$endpoint}{$uri}";
$timeStr = $timestamp->format("Y-m-d\TH:i:s\Z");
$head = array(
    "Content-Type:application/json",
    "Authorization:{$authorization}",
    "x-bce-date:{$timeStr}"
);
$body = array(
    "datapoints" => array(
        array(
            "metric" => "cpu_idle",
            "tags" => array(
                "host" => "server1",
                "rack" => "rack1"
            ),
            "timestamp" => (new \DateTime())->getTimestamp(),
            "value" => 51
        ),

        array(
            "metric" => "cpu_idle",
            "tags" => array(
                "host" => "server2",
                "rack" => "rack2"
            ),
            "values" => array(
                array((new \DateTime())->getTimestamp(), 67),
                array((new \DateTime())->getTimestamp(), 61)
            )
        )
    )
);
```

**其中，数据点有两种表示方式：**

> 
> 第一种：
> 
> ```php
> array(
    "metric" => "cpu_idle",
    "tags" => array(
        "host" => "server1",
        "rack" => "rack1"
    ),
    "timestamp" => (new \DateTime())->getTimestamp(),
    "value" => 51
)
> ```
> 
> **其中，`timestamp`和`value`必须同时存在。**
> 
> 第二种：
> 
> ```php
> array(
    "metric" => "cpu_idle",
    "tags" => array(
        "host" => "server2",
        "rack" => "rack2"
    ),
    "values" => array(
        array((new \DateTime())->getTimestamp(), 67),
        array((new \DateTime())->getTimestamp(), 61)
    )
)
> ```
> 
> **如果有多个数据点的`metric`和`tags`都是相同的，可以使用`values`参数来设置多个数据点的时间戳和数据值，这种方式可以减少HTTP请求的payload。**

### 第三步：发送HTTP请求，写入数据点。

```php
$curlp = curl_init();
curl_setopt($curlp, CURLOPT_POST, 1);
curl_setopt($curlp, CURLOPT_URL, $url);
curl_setopt($curlp, CURLOPT_HTTPHEADER, $head);
curl_setopt($curlp, CURLOPT_POSTFIELDS, json_encode($body));

curl_setopt($curlp, CURLOPT_RETURNTRANSFER, 1);
$response = curl_exec($curlp);
$status = curl_getinfo($curlp, CURLINFO_HTTP_CODE);
curl_close($curlp);

print("status: {$status}\n");
print("response: {$response}\n");
```

**如果写入成功的话，HTTP响应的状态码为：204。**