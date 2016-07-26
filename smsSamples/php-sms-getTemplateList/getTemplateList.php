<?php

require "auth.php";

// 设置AK/SK，生成认证信息。
$AK = "";  // AccessKeyID
$SK = "";  // SecretAccessKey

$expirationPeriodInSeconds = 3600;
$method = "GET";
$uri = "/v1/template";
$host = "sms.bj.baidubce.com";
$head = createHeadWithAuthorization($AK, $SK, $expirationPeriodInSeconds, $method, $uri, $host);

// 设置HTTP请求的Header和Body
$curlp = curl_init();
$url = "http://" . $host . $uri;
curl_setopt($curlp, CURLOPT_URL, $url);
curl_setopt($curlp, CURLOPT_HTTPHEADER, $head);

// 发送短信
curl_setopt($curlp, CURLOPT_RETURNTRANSFER, 1);
$response = curl_exec($curlp);
curl_close($curlp);
print($response . "\n");

?>