# php-sms-deleteTemplate

## 用途：

删除短信模板。

## 使用方法：

* 第一步：在文件`deleteTemplate.php`中配置AK/SK和短信模板ID。
* 第二步：执行命令`php deleteTemplate.php`。

## 代码简介：

### 第一步：配置AK/SK和短信模板ID，生成认证字符串。

```php
require "../../authorization/auth.php";

$ak = "";  // AccessKeyId
$sk = "";  // SecretAccessKey
$templateId = "";  // 短信模板ID

$method = "DELETE";
$host = "sms.bj.baidubce.com";
$uri = "/v1/template/{$templateId}";
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

### 第三步：查询短信模板的详情。

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