# php-ses-listVerifiedEmail

## 用途：

获取所有“发信邮箱”的认证状态。

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

* 第一步：在`listVerifiedEmail.php`中配置AK/SK。
* 第二步：执行命令`php listVerifiedEmail.php`。
* 第三步：在输出结果中查看所有“发信邮箱”的认证状态。

## 代码简介：

### 第一步：配置AK/SK，生成认证字符串。

```php
require "../../authorization/auth.php";

$ak = "";  // AccessKeyId
$sk = "";  // SecretAccessKey

$method = "GET";
$host = "ses.bj.baidubce.com";
$uri = "/v1/verifiedEmail";
$params = array();

date_default_timezone_set('UTC');
$timestamp = new \DateTime();
$expirationInSeconds = 3600;

$authorization = generateAuthorization($ak, $sk, $method, $host, $uri, array(), $timestamp, $expirationInSeconds);
print("authorization: {$authorization}\n");
```

**认证字符串的生成方法，可参考：[auth.php](../../authorization/auth.php)**

### 第二步：生成HTTP请求的header。

```php
$timeStr = $timestamp->format("Y-m-d\TH:i:s\Z");
$url = "http://" . $host . $uri;
$head =  array(
    "Content-Type:application/json",
    "Authorization:{$authorization}",
    "x-bce-date:{$timeStr}"
    );
```

### 第三步：发送请求，以获取所有“发信邮箱”的认证状态。

```php
$curlp = curl_init();
curl_setopt($curlp, CURLOPT_URL, $url);
curl_setopt($curlp, CURLOPT_HTTPHEADER, $head);
curl_setopt($curlp, CURLOPT_RETURNTRANSFER, 1);
$response = curl_exec($curlp);
curl_close($curlp);

print("{$response}\n");
```