# php-ses-verifyDKIM

## 用途：

为某个“发信邮件域”设置“DKIM”认证。

> 在SES中，发送邮件时要指定“发信邮箱”，并且该邮箱地址必须经过认证（或者其对应的邮件域经过认证）。
> 
> 如果是认证“发信邮件域”，可以选择是否开启“DKIM”（反垃圾邮件技术之一）。

**注意：接口调用成功之后，SES会创建一条CNAME记录，需要把它添加到域名的DNS解析设置中（CNAME记录详情可进入“管理控制台”查看）。**

## 使用方法：

* 第一步：在`verifyDKIM.php`中配置AK/SK，以及要认证的“发信邮件域”的域名。
* 第二步：执行命令`php verifyDKIM.php`。
* 第三步：在输出结果中查看SES生成的CNAME记录值，或进入“管理控制台”查看该TXT记录的详情。

## 代码简介：

### 第一步：配置AK/SK和域名，生成认证字符串。

```php
require "../../authorization/auth.php";

$ak = "";  // AccessKeyId
$sk = "";  // SecretAccessKey
$domain = "";  // 发信邮件域，如：abc.com

$method = "PUT";
$host = "ses.bj.baidubce.com";
$uri = "/v1/verifiedDomain/{$domain}";
$params = array("dkim" => "");

date_default_timezone_set('UTC');
$timestamp = new \DateTime();
$expirationInSeconds = 3600;

$authorization = generateAuthorization($ak, $sk, $method, $host, $uri, $params, $timestamp, $expirationInSeconds);
print("authorization: {$authorization}\n");
```

**认证字符串的生成方式，请参考：[auth.php](../../authorization/auth.php)**

### 第二步：构造HTTP请求的URL和Header。

```php
$url = "http://{$host}{$uri}?dkim";
$timeStr = $timestamp->format("Y-m-d\TH:i:s\Z");
$head =  array(
    "Content-Type:application/json",
    "Authorization:{$authorization}",
    "x-bce-date:{$timeStr}"
    );
```

### 第三步：发送HTTP请求，认证“发信邮件域”。

```php
$curlp = curl_init();
curl_setopt($curlp, CURLOPT_PUT, 1);
curl_setopt($curlp, CURLOPT_URL, $url);
curl_setopt($curlp, CURLOPT_HTTPHEADER, $head);

curl_setopt($curlp, CURLOPT_RETURNTRANSFER, 1);
$response = curl_exec($curlp);
$status = curl_getinfo($curlp, CURLINFO_HTTP_CODE);
curl_close($curlp);

print("status: {$status}\n");
print("response: {$response}\n");
```