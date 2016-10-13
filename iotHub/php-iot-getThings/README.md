# php-iot-getThings

## 用途：

获取某个iot实例的thing列表。

## 使用方法：

* 第一步：在`getThings.php`中配置AK/SK和实例名称。
* 第二步：执行命令`php getThings.php`。

## 代码简介：

### 第一步：配置AK/SK，生成认证字符串。

```php
require "../../authorization/auth.php";

$ak = "";  // AccessKeyId
$sk = "";  // SecretAccessKey
$endpointName = "";  // 要查询的thing所属的实例名称

$method = "GET";
$host = "iot.gz.baidubce.com";
$uri = "/v1/endpoint/{$endpointName}/thing";
$params = array();

date_default_timezone_set('UTC');
$timestamp = new \DateTime();
$expirationInSeconds = 3600;

$authorization = generateAuthorization($ak, $sk, $method, $host, $uri, $params, $timestamp, $expirationInSeconds);
print("authorization: {$authorization}\n");
```

**生成认证字符串的方法，请参考：[auth.php](../../authorization/auth.php)**

### 第二步：构造HTTP请求的URL、Header。

```php
$url = "http://{$host}{$uri}";
$timeStr = $timestamp->format("Y-m-d\TH:i:s\Z");
$head =  array(
    "Content-Type:application/json",
    "Authorization:{$authorization}",
    "x-bce-date:{$timeStr}"
);  
```

### 第三步：发送HTTP请求，并输出响应信息。

```php
$curlp = curl_init();
curl_setopt($curlp, CURLOPT_URL, $url);
curl_setopt($curlp, CURLOPT_HTTPHEADER, $head);

curl_setopt($curlp, CURLINFO_HEADER_OUT, 1);
curl_setopt($curlp, CURLOPT_RETURNTRANSFER, 1);

$response = curl_exec($curlp);
$request = curl_getinfo($curlp, CURLINFO_HEADER_OUT);
$status = curl_getinfo($curlp, CURLINFO_HTTP_CODE);
curl_close($curlp);

print("request: {$request}\n");
print("status: {$status}\n");
print("response: {$response}\n");
```