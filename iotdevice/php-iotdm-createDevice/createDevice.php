<?php

require "../../authorization/auth.php";

// 第一步：生成认证字符串

$ak = "";  // AccessKeyId
$sk = "";  // SecretAccessKey

$method = "POST";
$host = "iotdm.gz.baidubce.com";
$uri = "/v1/iot/management/device";
$params = array("clientToken" => uniqid("", true));

date_default_timezone_set('UTC');
$timestamp = new \DateTime();
$expirationInSeconds = 3600;

$authorization = generateAuthorization($ak, $sk, $method, $host, $uri, $params, $timestamp, $expirationInSeconds);

// 第二步：构造HTTP请求的header、body等信息

$url = "http://{$host}{$uri}?clientToken={$params["clientToken"]}";
$timeStr = $timestamp->format("Y-m-d\TH:i:s\Z");
$head =  array(
    "Content-Type:application/json",
    "Authorization:{$authorization}",
    "x-bce-date:{$timeStr}"
);
$body = array(
    "amount" => 2,  // 创建的设备数量
    "devices" => array("device01", "device02"),  // 设备的名称（如果不提供，则iotdevice会自动生成名称）
    "bootstrap" => true  // true：在iothub中创建相应的thing、principal、policy、topic等配置
    // "parent" => "",  // 设备组ID，可选。
    // "description" => "",  // 设备描述信息，可选。
);
$bodyStr = json_encode($body);

// 第三步：发送HTTP请求，并输出响应信息。

$curlp = curl_init();
curl_setopt($curlp, CURLOPT_POST, 1);
curl_setopt($curlp, CURLOPT_URL, $url);
curl_setopt($curlp, CURLOPT_HTTPHEADER, $head);
curl_setopt($curlp, CURLOPT_POSTFIELDS, $bodyStr);

curl_setopt($curlp, CURLINFO_HEADER_OUT, 1);
curl_setopt($curlp, CURLOPT_RETURNTRANSFER, 1);

$response = curl_exec($curlp);
$request = curl_getinfo($curlp, CURLINFO_HEADER_OUT);
$status = curl_getinfo($curlp, CURLINFO_HTTP_CODE);
curl_close($curlp);

print("request: {$request}\n");
print("request body: {$bodyStr}\n");
print("status: {$status}\n");
print("response: {$response}\n");

?>