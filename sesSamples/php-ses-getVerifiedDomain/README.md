# php-ses-getVerifiedDomain

## 用途：

获取所有“发信邮件域”的认证状态。

> 在SES中，发送邮件时要指定“发信邮箱”。该邮箱地址必须经过认证，或其所属的邮件域必须经过认证。

| 状态码 | 说明 |
| :-- | :-- |
| 0 | 认证成功 |
| 1 | 认证最终失败 |
| 2 | 认证暂时失败 |
| 3 | 认证中 |
| 4 | 认证未开始 |
| 5 | 认证未创建 |

## 使用方法：

* 第一步：在`getVerifiedDomain.php`中配置AK/SK和域名。
* 第二步：执行命令`php getVerifiedDomain.php`。
* 第三步：在输出结果中查看单个“发信邮件域”的认证状态。

## 代码简介：

### 第一步：配置AK/SK，生成认证字符串。

```php
require "../../authorization/auth.php";

$ak = "";  // AccessKeyId
$sk = "";  // SecretAccessKey
$domain = "";  // 要查询的“发信邮件域”，比如：abc.com

$method = "GET";
$host = "ses.bj.baidubce.com";
$uri = "/v1/verifiedDomain/{$domain}";
$params = array();

date_default_timezone_set('UTC');
$timestamp = new \DateTime();
$expirationInSeconds = 3600;

$authorization = generateAuthorization($ak, $sk, $method, $host, $uri, $params, $timestamp, $expirationInSeconds);
print("authorization: {$authorization}\n");
```

**认证字符串的生成方式，请参考：[auth.php](../../authorization/auth.php)**

### 第二步：构造HTTP请求的header。 

```php
$timeStr = $timestamp->format("Y-m-d\TH:i:s\Z");
$url = "http://" . $host . $uri;
$head =  array(
    "Content-Type:application/json",
    "Authorization:{$authorization}",
    "x-bce-date:{$timeStr}"
    );
```

### 第三步：发送HTTP请求，获取所有“发信邮件域”的认证状态。

```php
$curlp = curl_init();
curl_setopt($curlp, CURLOPT_URL, $url);
curl_setopt($curlp, CURLOPT_HTTPHEADER, $head);

curl_setopt($curlp, CURLOPT_RETURNTRANSFER, 1);
$response = curl_exec($curlp);
$status = curl_getinfo($curlp, CURLINFO_HTTP_CODE);
curl_close($curlp);

print("http status: {$status}\n");
print("response: {$response}\n");
```