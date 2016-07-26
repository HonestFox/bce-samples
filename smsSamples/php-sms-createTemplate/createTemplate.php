<?php

require "auth.php";

// 设置AK/SK，生成认证信息。
$AK = "";  // AccessKeyID
$SK = "";  // SecretAccessKey

$expirationPeriodInSeconds = 3600;
$method = "POST";
$uri = "/v1/template";
$host = "sms.bj.baidubce.com";
$head = createHeadWithAuthorization($AK, $SK, $expirationPeriodInSeconds, $method, $uri, $host);

// 设置模板ID、模板参数值、接收者的手机号码。
$body = array(
    "name" => "您的模板名称",  // 模板名字，不能重复。
    "content" => '您的模板内容，模板参数形式为：${code}。'  // 模板内容，建议使用单引号，否则模板参数${code}的会被解析成变量。
);
$bodyString = json_encode($body);

// 设置HTTP请求的Header和Body
$curlp = curl_init();
$url = "http://" . $host . $uri;
curl_setopt($curlp, CURLOPT_URL, $url);
curl_setopt($curlp, CURLOPT_HTTPHEADER, $head);
curl_setopt($curlp, CURLOPT_POST, 1);
curl_setopt($curlp, CURLOPT_POSTFIELDS, $bodyString);

// 发送短信
curl_setopt($curlp, CURLOPT_RETURNTRANSFER, 1);
$response = curl_exec($curlp);
curl_close($curlp);
print($response . "\n");

?>