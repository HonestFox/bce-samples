<?php

require "sign_sample.php";

class SmsAuth {

    public static function headWithAuth($ak, $sk, $httpMethod, $uri, $host, $expirationInSeconds) {
        date_default_timezone_set("UTC");
        $timestamp = new \DateTime();

        $httpMethod = strtoupper($httpMethod);

        $contentType = "application/json";
        $signature = SmsAuth::sign($ak, $sk, $httpMethod, $uri, $host, $timestamp, $expirationInSeconds);
        $timeString = $timestamp->format("Y-m-d\TH:i:s\Z");
        $head = array(
            "Content-Type: applicaiton/json",
            "Authorization: $signature",
            "x-bce-date: $timeString"
            );

        if ($httpMethod == "POST" or $httpMethod == "PUT") {
            $signingKey = SmsAuth::signingKey($ak, $sk, $timestamp, $expirationInSeconds);
            array_push($head, "x-bce-content-sha256: $signingKey");
        }
        return $head;
    }

    private static function sign($ak, $sk, $httpMethod, $uri, $host, $timestamp, $expirationInSeconds) {
        $credentials = array(
            "ak" => $ak,
            "sk" => $sk
            );
        $headers = array(
            "host" => $host,
            "x-bce-date" => $timestamp->format("Y-m-d\TH:i:s\Z")
            );
        $params = array();
        $options = array(
            \BaiduBce\Auth\SignOption::TIMESTAMP => $timestamp,
            \BaiduBce\Auth\SignOption::EXPIRATION_IN_SECONDS => $expirationInSeconds,
            \BaiduBce\Auth\SignOption::HEADERS_TO_SIGN => $headers
            );

        $signer = new \BaiduBce\Auth\SampleSigner();
        return $signer->sign($credentials, $httpMethod, $uri, $headers, $params, $options);
    }

    private static function signingKey($ak, $sk, $timestamp, $expirationInSeconds) {
        $bceAuthVersion = \BaiduBce\Auth\SampleSigner::BCE_AUTH_VERSION;
        $timeString = $timestamp->format("Y-m-d\TH:i:s\Z");
        $authStringPrefix = $bceAuthVersion . "/" . $ak . "/" . $timeString . "/" . $expirationInSeconds;
        return hash_hmac("SHA256", $authStringPrefix, $sk);
    }
}

?>