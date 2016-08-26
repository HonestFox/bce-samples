# php-sms-receiverStatus

## 用途：

查询单终端用户接收的短信信息。包括：

* `max_receiver_per_phone_number_day` - 单日单终端用户的接收配额
* `received_today` - 单终端用户今日已接收的短信数量

## 使用方法：

* 第一步：在文件`receiverStatus.php`中配置AK/SK和要查询的手机号码。
* 第二步：执行命令`php receiverStatus.php`。

## 代码简介：

### 第一步：配置AK/SK和要查询的手机号码，生成认证字符串。

```php
require "../../authorization/auth.php";

$ak = "";  // AccessKeyId
$sk = "";  // SecretAccessKey
$phoneNumber = "";  // 要查询的接收端手机号码

$method = "GET";
$host = "sms.bj.baidubce.com";
$uri = "/v1/receiver/{$phoneNumber}";
$params = array();

date_default_timezone_set('UTC');
$timestamp = new \DateTime();
$expirationInSeconds = 3600;

$authorization = generateAuthorization($ak, $sk, $method, $host, $uri, $params, $timestamp, $expirationInSeconds);
print("authorization: {$authorization}\n");
```

**认证字符串的生成方式，请参考：[auth.php](../../authorization/auth.php)**

### 第二步：构造HTTP请求的URL和Header。

```php
$url = "http://{$host}{$uri}";
$timeStr = $timestamp->format("Y-m-d\TH:i:s\Z");
$head =  array(
    "Content-Type:application/json",
    "Authorization:{$authorization}",
    "x-bce-date:{$timeStr}"
    );
```

### 第三步：发送HTTP请求，查询单终端用户接收的短信信息。

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