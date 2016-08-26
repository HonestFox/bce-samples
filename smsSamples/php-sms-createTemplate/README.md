# php-sms-createTemplate

## 用途：

创建短信模板。返回结果：

* template_id - 短信模板ID

**注意：创建成功的短信模板需要审核通过之后才能使用。**

## 使用方法：

* 第一步：在文件`createTemplate.php`中配置AK/SK，以及短信模板名称和内容。
* 第二步：执行命令`php createTemplate.php`。

## 代码简介：

### 第一步：配置AK/SK，生成认证字符串。

```php
require "../../authorization/auth.php";

$ak = "";  // AccessKeyId
$sk = "";  // SecretAccessKey

$method = "POST";
$host = "sms.bj.baidubce.com";
$uri = "/v1/template";
$params = array();

date_default_timezone_set('UTC');
$timestamp = new \DateTime();
$expirationInSeconds = 3600;

$authorization = generateAuthorization($ak, $sk, $method, $host, $uri, $params, $timestamp, $expirationInSeconds);
print("authorization: {$authorization}\n");
```

**认证字符串的生成方式，请参考：[auth.php](../../authorization/auth.php)**

### 第二步：构造HTTP请求的URL和Header、Body。

```php
$url = "http://{$host}{$uri}";
$timeStr = $timestamp->format("Y-m-d\TH:i:s\Z");
$head =  array(
    "Content-Type:application/json",
    "Authorization:{$authorization}",
    "x-bce-date:{$timeStr}"
    );
$body = array(
    "name" => "短信模板名称",
    "content" => "短信模板内容"
    );
```

### 第三步：发送HTTP请求，创建短信模板。

```php
$curlp = curl_init();
curl_setopt($curlp, CURLOPT_POST, 1);
curl_setopt($curlp, CURLOPT_URL, $url);
curl_setopt($curlp, CURLOPT_HTTPHEADER, $head);
curl_setopt($curlp, CURLOPT_POSTFIELDS, json_encode($body));

curl_setopt($curlp, CURLOPT_RETURNTRANSFER, 1);
$response = curl_exec($curlp);
$status = curl_getinfo($curlp, CURLINFO_HTTP_CODE);
curl_close($curlp);

print("status: {$status}\n");
print("response: {$response}\n");
```