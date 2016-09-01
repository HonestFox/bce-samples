# php-tsdb-getTags

## 用途：

获取与某个metric相关的所有tags。

## 使用方法：

* 第一步：在`getTags.php`中配置AK/SK和endpoint。
* 第二步：执行命令`php getTags.php`。

## 代码简介：

### 第一步：配置AK/SK和endpoint，生成认证字符串。

```php
require "../../authorization/auth.php";

$ak = "";  // AccessKeyID
$sk = "";  // SecretAccessKey
$endpoint = "";  // TSDB连接地址，如：{database}.tsdb.iot.gz.baidubce.com
$metric = "";  // 只能获取与metric对应的tags

$method = "GET";
$uri = "/v1/metric/{$metric}/tag";
$params = array();

date_default_timezone_set("UTC");
$timestamp = new \DateTime();
$expirationInSeconds = 3600;

$authorization = generateAuthorization($ak, $sk, $method, $endpoint, $uri, $params, $timestamp, $expirationInSeconds);
print("authorization: {$authorization}\n");
```

**认证字符串的生成方式，请参考：[auth.php](../../authorization/auth.php)**

### 第二步：构造HTTP请求的URL、Header。

```php
$url = "http://{$endpoint}{$uri}";
$timeStr = $timestamp->format("Y-m-d\TH:i:s\Z");
$head = array(
    "Content-Type:application/json",
    "Authorization:{$authorization}",
    "x-bce-date:{$timeStr}"
);
```

### 第三步：发送HTTP请求，写入数据点。

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