# php-iot-generatePassword

## 用途：

为指定的principal重新生成密钥。

## 使用方法：

* 第一步：在`generatePassword.php`中配置AK/SK和endpointName、principalName。
* 第二步：执行命令`php generatePassword.php`。

**执行结果中会包含新密钥。请牢记该密钥，如果丢失则无法找回，只能重新生成密钥。**

## 代码简介：

### 第一步：配置AK/SK，生成认证字符串。

```php
require "../../authorization/auth.php";

$ak = "";  // AccessKeyId
$sk = "";  // SecretAccessKey
$endpointName = "";  // 实例名称
$principalName = "";  // 身份名称

$method = "POST";
$host = "iot.gz.baidubce.com";
$uri = "/v1/endpoint/{$endpointName}/principal/{$principalName}";
$params = array();

date_default_timezone_set('UTC');
$timestamp = new \DateTime();
$expirationInSeconds = 3600;

$authorization = generateAuthorization($ak, $sk, $method, $host, $uri, $params, $timestamp, $expirationInSeconds);
print("authorization: {$authorization}\n");
```

**生成认证字符串的方法，请参考：[auth.php](../../authorization/auth.php)**

### 第二步：构造HTTP请求的URL、Header和Body。

```php
$url = "http://{$host}{$uri}";
$timeStr = $timestamp->format("Y-m-d\TH:i:s\Z");
$head =  array(
    "Content-Type:application/json",
    "Authorization:{$authorization}",
    "x-bce-date:{$timeStr}"
);
$body = json_encode(
    array("target" => "all")  # 可选项：all, password, cert。默认值：all。
);
```

### 第三步：发送HTTP请求，并输出响应信息。

```php
$curlp = curl_init();
curl_setopt($curlp, CURLOPT_POST, 1);
curl_setopt($curlp, CURLOPT_URL, $url);
curl_setopt($curlp, CURLOPT_HTTPHEADER, $head);
curl_setopt($curlp, CURLOPT_POSTFIELDS, $body);

curl_setopt($curlp, CURLINFO_HEADER_OUT, 1);
curl_setopt($curlp, CURLOPT_RETURNTRANSFER, 1);

$response = curl_exec($curlp);
$request = curl_getinfo($curlp, CURLINFO_HEADER_OUT);
$status = curl_getinfo($curlp, CURLINFO_HTTP_CODE);
curl_close($curlp);

print("request: {$request}\n");
print("status: {$status}\n");
print("response: {$response}\n");
```