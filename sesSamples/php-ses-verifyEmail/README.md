# php-ses-verifyEmail

## 用途：

验证“发信邮箱”。

> 在SES中，发送邮件时要指定“发信邮箱”，并且该邮箱地址必须事先经过验证才可以。

**注意：接口调用成功之后，SES会向该邮箱地址发送一封“验证邮件”，需要手动点击该邮件中的链接来完成认证。**

## 使用方法：

* 第一步：在`verifyEmail.php`中配置AK/SK，以及要验证的“发信邮箱”。
* 第二步：执行命令`php verifyEmail.php`。
* 第三步：在“发信邮箱”中查找SES的“验证邮件”，点击其中的链接完成认证。

## 代码简介：

### 第一步：配置AK/SK和待验证的“发信邮箱”，生成认证字符串。

```php
require "../../authorization/auth.php";

$ak = "";  // AccessKeyId
$sk = "";  // SecretAccessKey
$emailAddress = "";  // 待认证的“发信邮箱”，如：xyz@abc.com

$method = "PUT";
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

### 第三步：发送HTTP请求以验证“发信邮箱”。

```php
$curlp = curl_init();
curl_setopt($curlp, CURLOPT_PUT, 1);
curl_setopt($curlp, CURLOPT_URL, $url);
curl_setopt($curlp, CURLOPT_HTTPHEADER, $head);

curl_setopt($curlp, CURLOPT_RETURNTRANSFER, 1);
$response = curl_exec($curlp);
curl_close($curlp);

print("{$response}\n");
```