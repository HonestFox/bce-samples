# php-ses-getVerifiedEmail

## 用途：

获取单个“发信邮箱”的认证状态。

> 在SES中，发送邮件时要指定“发信邮箱”，并且该邮箱地址必须事先经过验证才可以。

| 状态码 | 描述 |
| :-- | :-- |
| 0 | 认证通过 |
| 1 | 认证失败 |
| 2 | 暂时失败 |
| 3 | 认证中 |
| 4 | 认证未开始 |
| 5 | 不存在 |


## 使用方法：

* 第一步：在`getVerifiedEmail.php`中配置AK/SK，并指定要查询的“发信邮箱”。
* 第二步：执行命令`php getVerifiedEmail.php`。
* 第三步：在输出结果中查看该“发信邮箱”的认证状态。

## 代码简介：

### 第一步：配置AK/SK和发信邮箱，生成认证字符串。

```php
require "../../authorization/auth.php";

$ak = "";  // AccessKeyId
$sk = "";  // SecretAccessKey
$emailAddress = "";  // 要查询的“发信邮箱”， 如：xyz@abc.com

$method = "GET";
$host = "ses.bj.baidubce.com";
$uri = "/v1/verifiedEmail/{$emailAddress}";
$params = array();

date_default_timezone_set('UTC');
$timestamp = new \DateTime();
$expirationInSeconds = 3600;

$authorization = generateAuthorization($ak, $sk, $method, $host, $uri, $params, $timestamp, $expirationInSeconds);
print("authorization: {$authorization}\n");
```

### 第二步：构造HTTP请求的header。

```php
$timeStr = $timestamp->format("Y-m-d\TH:i:s\Z");
$url = "http://{$host}{$uri}";
$head =  array(
    "Content-Type:application/json",
    "Authorization:{$authorization}",
    "x-bce-date:{$timeStr}"
    );
```

### 第三步：发送HTTP请求，获取单个“发信邮箱”的认证状态。

```php
$curlp = curl_init();
curl_setopt($curlp, CURLOPT_URL, $url);
curl_setopt($curlp, CURLOPT_HTTPHEADER, $head);

curl_setopt($curlp, CURLOPT_RETURNTRANSFER, 1);
$response = curl_exec($curlp);
curl_close($curlp);

print("{$response}\n");
```