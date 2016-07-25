<?php

// 生成HTTP请求头（内含认证信息）
function createHeadWithAuthorization($ak, $sk, $expirationPeriodInSeconds, $method, $uri, $host) {
    date_default_timezone_set("UTC");
    $timestamp = date("Y-m-d") . "T" . date("H:i:s") . "Z";

    // 生成认证字符串
    $authStringPrefix = "bce-auth-v1" . "/" . $ak . "/" . $timestamp . "/" . $expirationPeriodInSeconds;
    $signingKey = hash_hmac("SHA256", $authStringPrefix, $sk);
    $canonicalHeaders1 = "host;" . "x-bce-date";
    $canonicalHeaders2 = "host:" . $host . "\n" . "x-bce-date:" . urlencode($timestamp);
    print("canonicalHeaders2: " . $canonicalHeaders2 . "\n");

    $method = strtoupper($method);
    $canonicalQueryString = "";
    $canonicalRequest = $method . "\n" . $uri . "\n" . $canonicalQueryString . "\n" . $canonicalHeaders2;
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

?>