<?php

require "../../authorization/auth.php";

// 第一步：生成认证字符串

$ak = "";  // AccessKeyId
$sk = "";  // SecretAccessKey
$emailAddress = "";  // 要查询的“发信邮箱”， 如：xyz@abc.com

$method = "GET";
$host = "ses.bj.baidubce.com";
$uri = "/v1/verifiedEmail/{$emailAddress}";
$params = array();

date_default_timezone_set('UTC');
$timestamp = new \DateTime();
$expirationInSeconds = 3600;

$authorization = generateAuthorization($ak, $sk, $method, $host, $uri, $params, $timestamp, $expirationInSeconds);
print("authorization: {$authorization}\n");

// 第二步：构造HTTP请求的header、body等信息

$timeStr = $timestamp->format("Y-m-d\TH:i:s\Z");
$url = "http://{$host}{$uri}";
$head =  array(
    "Content-Type:application/json",
    "Authorization:{$authorization}",
    "x-bce-date:{$timeStr}"
    );

// 第三步：发送HTTP请求，并输出响应信息。

$curlp = curl_init();
curl_setopt($curlp, CURLOPT_URL, $url);
curl_setopt($curlp, CURLOPT_HTTPHEADER, $head);

curl_setopt($curlp, CURLOPT_RETURNTRANSFER, 1);
$response = curl_exec($curlp);
curl_close($curlp);

print("{$response}\n");

?>