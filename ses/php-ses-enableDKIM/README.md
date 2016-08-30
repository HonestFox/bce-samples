# java-ses-enableDKIM

## 用途：

为某个“发信邮件域”启用DKIM。

> 在SES中，发送邮件时要指定“发信邮箱”，并且该邮箱地址必须经过认证（或者其对应的邮件域经过认证）。
> 
> 如果是认证“发信邮件域”，可以选择是否开启“DKIM”（反垃圾邮件技术之一）。

一般建议以下列方式使用：

* 认证“发信邮件域”，并启用DKIM。

    ```
    verifyDomain  // 生成一条TXT记录，需手动添加到DNS解析中
    enableDKIM  // 启用DKIM
    verifyDKIM  // 生成一条CNAME记录，需手动添加到DNS解析中
    ```
    
* 认证“发信邮件域”，但禁用DKIM。
    
    ```
    verifyDomain  // 生成一条TXT记录，需手动添加到DNS解析中
    disableDKIM  // 禁用DKIM
    ```

## 使用方法：

* 第一步：在`enableDKIM.php`中配置AK/SK，以及要认证的“发信邮件域”的域名。
* 第二步：执行命令`php enableDKIM.php`。
* 第三步：进入“管理控制台”查看DKIM的状态。

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
$params = array("enableDkim" => "");

date_default_timezone_set('UTC');
$timestamp = new \DateTime();
$expirationInSeconds = 3600;

$authorization = generateAuthorization($ak, $sk, $method, $host, $uri, $params, $timestamp, $expirationInSeconds);
print("authorization: {$authorization}\n");
```

**认证字符串的生成方式，请参考：[auth.php](../../authorization/auth.php)**

### 第二步：构造HTTP请求的URL和Header。

```php
$url = "http://{$host}{$uri}?enableDkim";
$timeStr = $timestamp->format("Y-m-d\TH:i:s\Z");
$head =  array(
    "Content-Type:application/json",
    "Authorization:{$authorization}",
    "x-bce-date:{$timeStr}"
    );
```

### 第三步：发送HTTP请求，启用DKIM。

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