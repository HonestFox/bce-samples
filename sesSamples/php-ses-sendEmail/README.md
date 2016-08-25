# php-ses-sendEmail

## 用途：

发送邮件。

## 使用方法：

* 第一步：在`sendEmail.php`中配置AK/SK，并设置发信邮箱、收信邮箱、邮件标题、邮件正文等。
* 第二步：执行命令`php sendEmail.php`。
* 第三步：在输出结果中查看所发送邮件的流水号。

## 代码简介：

### 第一步：配置AK/SK，生成认证字符串。

```php
require "../../authorization/auth.php";

$ak = "";  // AccessKeyId
$sk = "";  // SecretAccessKey

$method = "POST";
$host = "ses.bj.baidubce.com";
$uri = "/v1/email";
$params = array();

date_default_timezone_set('UTC');
$timestamp = new \DateTime();
$expirationInSeconds = 3600;

$authorization = generateAuthorization($ak, $sk, $method, $host, $uri, $params, $timestamp, $expirationInSeconds);
print("authorization: {$authorization}\n");
```

### 第二步：构造HTTP请求的header、body等信息。

```php
$timeStr = $timestamp->format("Y-m-d\TH:i:s\Z");
$head =  array(
    "Content-Type:application/json",
    "Authorization:{$authorization}",
    "x-bce-date:{$timeStr}"
    );
$body = array(
    "mail" => array(
        "source" => array(
            "from" => "发信邮箱",  // 必须经过认证（或对应的邮件域经过认证）
            "name" => "自定义签名"  // 可选
            ),

        "destination" => array(
            "to_addr" => array(
                array("addr" => "收信邮箱1"),
                array("addr" => "收信邮箱2")
                ),
            "cc_addr" => array(  // 可选
                array("addr" => "抄送邮箱1"),
                array("addr" => "抄送邮箱2")
                ),
            "bcc_addr" => array(  // 可选
                array("addr" => "密送邮箱1"),
                array("addr" => "密送邮箱2")
                )
            ),

        "subject" => array(
            "charset" => 1,
            "data" => "邮件标题"
            ),

        "priority" => 1,  // 可选

        "message" => array(
            "text" => array(
                "charset" => 1,
                "data" => "邮件正文"
                )
            )
        ),

        "attachments" => array(  // 可选
            array(
                "file_name" => "附件1",
                "file_data" => array("data" => "附件1的内容")
                ),
            array(
                "file_name" => "附件2",
                "file_data" => array("data" => "附件2的内容")
                )
            )
    );
```

### 第三步：发送邮件。

```php
$url = "http://{$host}{$uri}";

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