<<?php
// 配置签名信息
date_default_timezone_set('UTC');
$AK = "";  // 填写AK（AccessKeyId）
$SK = "";  // 填写SK（SecretAccessKey）
$timestamp = date("Y-m-d")."T".date("H:i:s")."Z";
$expirationPeriodInSeconds = "3600";  // 设置签名过期时间（单位：秒）

// 计算签名
$authStringPrefix = "bce-auth-v1"."/".$AK."/".$timestamp."/".$expirationPeriodInSeconds;
$SigningKey = hash_hmac('SHA256', $authStringPrefix, $SK);
$CanonicalHeaders1 = "host;"."x-bce-date";
$CanonicalHeaders2 = "host:sms.bj.baidubce.com\n"."x-bce-date:".urlencode($timestamp);//
print "CanonicalHeaders2:".$CanonicalHeaders2."\n";

$CanonicalString = "";
$CanonicalURI = "/v1/message";
$CanonicalRequest = "POST\n".$CanonicalURI."\n".$CanonicalString."\n".$CanonicalHeaders2;
print "CanonicalRequest:".$CanonicalRequest."\n";

$Signature = hash_hmac('SHA256', $CanonicalRequest, $SigningKey);
print "Signature:".$Signature."\n";

$Authorization = "bce-auth-v1/{$AK}/".$timestamp."/{$expirationPeriodInSeconds}/{$CanonicalHeaders1}/{$Signature}";
print "Authorization:".$Authorization."\n";

// 设置模板ID、模板参数值、接收者的手机号码。
$url = "http://sms.bj.baidubce.com/v1/message";
$data = array(
    'templateId' => 'smsTpl:e7476122a1c24e37b3b0de19d04ae900',  // 内置短信模板，内容为：您的验证码是${code}
    'contentVar' => json_encode(array('code' => '12345')),  // 设置模板中的参数值
    'receiver' => array('手机号码1', '手机号码2')  // 支持多个手机号码
    );
$data_string = json_encode($data);

// 构造HTTP请求
$head =  array(
    "Content-Type: application/json",
    "Authorization: {$Authorization}",
    "x-bce-date: {$timestamp}",
    "x-bce-content-sha256: {$SigningKey}"
    );
$curlp = curl_init();
curl_setopt($curlp, CURLOPT_URL, $url);
curl_setopt($curlp, CURLOPT_HTTPHEADER, $head);
curl_setopt($curlp, CURLOPT_POSTFIELDS, $data_string);
if(!empty($data)) {
    curl_setopt($curlp, CURLOPT_POST, 1);
}

// 发送短信
curl_setopt($curlp, CURLOPT_RETURNTRANSFER, 1);
$output = curl_exec($curlp);
curl_close($curlp);
echo $output;
print "\n";
?>