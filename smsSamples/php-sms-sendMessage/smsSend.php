<?php

// 生成HTTP请求头（内含认证信息）
function createHeadWithAuthorization($ak, $sk, $expirationPeriodInSeconds, $method, $uri="/v1/message", $host='sms.bj.baidubce.com') {
    date_default_timezone_set("UTC");
    $timestamp = date("Y-m-d") . "T" . date("H:i:s") . "Z";

    // 生成认证字符串
    $authStringPrefix = "bce-auth-v1" . "/" . $ak . "/" . $timestamp . "/" . $expirationPeriodInSeconds;
    $signingKey = hash_hmac("SHA256", $authStringPrefix, $sk);
    $canonicalHeaders1 = "host;" . "x-bce-date";
    $canonicalHeaders2 = "host:" . $host . "\n" . "x-bce-date:" . urlencode($timestamp);
    print("canonicalHeaders2: " . $canonicalHeaders2 . "\n");

    $method = strtoupper($method);
    $canonicalRequest = $method . "\n" . $uri . "\n\n" . $canonicalHeaders2;
    print("canonicalRequest: " . $canonicalRequest . "\n");

    $signature = hash_hmac("SHA256", $canonicalRequest, $signingKey);
    print("signature: " . $signature . "\n");

    $authorization = "bce-auth-v1/{$ak}/" . $timestamp . "/{$expirationPeriodInSeconds}/{$canonicalHeaders1}/{$signature}";
    print("authorization: " . $authorization . "\n");

    // 生成HTTP请求头
    if ($method == "POST" or $method == "PUT") {
        return array(
            "Content-Type: application/json",
            "Authorization: {$authorization}",
            "x-bce-date: {$timestamp}",
            "x-bce-content-sha256: {$signingKey}"
        );
    } else {
        return array(
            "Content-Type: application/json",
            "Authorization: {$authorization}",
            "x-bce-date: {$timestamp}"
        );
    }
}

$AK = "";  // AccessKeyID
$SK = "";  // SecretAccessKey
$expirationPeriodInSeconds = 3600;
$method = "POST";
$head = createHeadWithAuthorization($AK, $SK, $expirationPeriodInSeconds, $method);

// 设置模板ID、模板参数值、接收者的手机号码。
$url = "http://sms.bj.baidubce.com/v1/message";
$data = array(
    'templateId' => 'smsTpl:e7476122a1c24e37b3b0de19d04ae900',  // 内置短信模板，内容为：您的验证码是${code}
    'contentVar' => json_encode(array('code' => '12345')),  // 设置模板中的参数值
    'receiver' => array('手机号码1', '手机号码2')  // 支持多个手机号码
    );
$data_string = json_encode($data);

// 设置HTTP请求的Header和Body
$curlp = curl_init();
curl_setopt($curlp, CURLOPT_URL, $url);
curl_setopt($curlp, CURLOPT_HTTPHEADER, $head);
curl_setopt($curlp, CURLOPT_POSTFIELDS, $data_string);
if (!empty($data)) {
    curl_setopt($curlp, CURLOPT_POST, 1);
}

// 发送短信
curl_setopt($curlp, CURLOPT_RETURNTRANSFER, 1);
$response = curl_exec($curlp);
curl_close($curlp);
print $response . "\n";
?>