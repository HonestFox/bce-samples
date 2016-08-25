# php-ses-deleteVerifiedEmail

## 用途：

删除单个“发信邮箱”。

> 在SES中，发送邮件时要指定“发信邮箱”，并且该邮箱地址必须事先经过验证才可以。

**注意：不管该“发信邮箱”处于什么状态，在被删除之后，都不能再被用作“发信邮箱”。如果需要重新使其作为“发信邮箱”，需要重新认证。**


## 使用方法：

* 第一步：在`deleteVerifiedEmail.php`中配置AK/SK，并指定要删除的“发信邮箱”。
* 第二步：执行命令`php deleteVerifiedEmail.php`。

## 代码简介：

### 第一步：配置AK/SK和要删除的“发信邮箱”，生成认证字符串。

```php
require "../../authorization/auth.php";

$ak = "";  // AccessKeyId
$sk = "";  // SecretAccessKey
$emailAddress = "";  // 要删除的“发信邮箱”，如：xyz@abc.com

$method = "DELETE";
$host = "ses.bj.baidubce.com";
$uri = "/v1/verifiedEmail/{$emailAddress}";
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

### 第三步：发送HTTP请求，来删除指定的“发信邮箱”。

```php
$curlp = curl_init();
curl_setopt($curlp, CURLOPT_CUSTOMREQUEST, $method);
curl_setopt($curlp, CURLOPT_URL, $url);
curl_setopt($curlp, CURLOPT_HTTPHEADER, $head);

curl_setopt($curlp, CURLOPT_RETURNTRANSFER, 1);
$response = curl_exec($curlp);
$status = curl_getinfo($curlp, CURLINFO_HTTP_CODE);
curl_close($curlp);

print("status: {$status}\n");
print("response: {$response}\n");
```