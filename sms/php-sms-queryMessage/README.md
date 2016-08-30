# php-sms-queryMessage

## 用途：

查询短信详情。主要包括：

* `messageId` - 短信流水号（发送短信时，返回结果中包含此流水号）
* `content` - 短信内容
* `receiver` － 短信接收者的手机号码
* `sendTime` － 短信的发送时间

## 使用方法：

* 第一步：在文件`queryMessage.php`中配置AK/SK和短信流水号。
* 第二步：执行命令`php queryMessage.php`。
* 第三步：在输出结果中查看短信详情。

## 代码简介：

### 第一步：配置AK/SK和短信流水号，生成认证字符串。

```php
require "../../authorization/auth.php";

$ak = "";  // AccessKeyId
$sk = "";  // SecretAccessKey
$messageId = "";  // 短信流水号

$method = "GET";
$host = "sms.bj.baidubce.com";
$uri = "/v1/message/{$messageId}";
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

### 第三步：发送HTTP请求，查询短信详情。

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