# php-ses-setFeedback

## 用途：

设置通知方式。

| 代码 | 通知类型 |
| :-- | :-- |
| 1 | 退信或投诉 |
| 2 | 退信 |
| 3 | 投诉 |

## 使用方法：

* 第一步：在`setFeedback.php`中配置AK/SK，以及邮箱地址等信息。
* 第二步：执行命令`php setFeedback.php`。

## 代码简介：

### 第一步：配置AK/SK，生成认证字符串。

```php
require "../../authorization/auth.php";

$ak = "";  // AccessKeyId
$sk = "";  // SecretAccessKey

$method = "PUT";
$host = "ses.bj.baidubce.com";
$uri = "/v1/feedback";
$params = array();

date_default_timezone_set('UTC');
$timestamp = new \DateTime();
$expirationInSeconds = 3600;

$authorization = generateAuthorization($ak, $sk, $method, $host, $uri, $params, $timestamp, $expirationInSeconds);
print("authorization: {$authorization}\n");
```

### 第二步：构造HTTP请求的URL、Header、Body。

```php
$url = "http://{$host}{$uri}";
$timeStr = $timestamp->format("Y-m-d\TH:i:s\Z");
$head =  array(
    "Content-Type:application/json",
    "Authorization:{$authorization}",
    "x-bce-date:{$timeStr}"
    );
$body = array(
    "type" => 2,  // 通知类型：1 退信或投诉，2 退信，3 投诉
    "enable" => true,  // true：接收通知，false：不接收通知
    "email" => ""  // 接收通知的邮箱地址，如：xyz@abc.com
    );
```

### 第三步：发送HTTP请求，设置通知方式。

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

print("status: {$status}\n");
print("response: {$response}\n");
```