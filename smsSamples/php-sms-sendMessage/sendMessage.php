<?php

require "auth.php";

// 设置AK/SK，生成认证信息。
$ak = "";  // AccessKeyID
$sk = "";  // SecretAccessKey

$method = "POST";
$host = "sms.bj.baidubce.com";
$uri = "/v1/message";

date_default_timezone_set('UTC');
$timestamp = new \DateTime();

$head = headWithAuthorization($ak, $sk, $method, $host, $uri, array(), $timestamp);
var_dump($head);

// 设置模板ID、模板参数值、接收者的手机号码。
$body = array(
    'templateId' => 'smsTpl:e7476122a1c24e37b3b0de19d04ae900',  // 内置短信模板，内容为：您的验证码是${code}
    'contentVar' => json_encode(array('code' => '12345')),  // 设置模板中的参数值
    'receiver' => array('手机号码1', '手机号码2')  // 支持多个手机号码
    );
$bodyString = json_encode($body);
var_dump($bodyString);

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