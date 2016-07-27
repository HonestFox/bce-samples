# SMS签名算法（PHP实现）

## 简介：

基于百度开放云的API签名算法，针对SMS API的签名要求进行封装。开发者可以直接使用封装后的接口生成SMS API所需要的HTTP请求头（其中包含了认证字符串）。

官方资料：

* 文档：[百度开放云API签名算法](https://bce.baidu.com/doc/Reference/AuthenticationMechanism.html)
* 示例：[百度开放云API签名算法的代码示例](https://bce.baidu.com/doc/Reference/AuthenticationMechanism.html#Sample.20Code)

## 使用方法：

* 第一步：把`sign_sample.php`和`smsAuth.php`拷贝到工程目录内。
* 第二步：导入`smsAuth.php`。
    
    ```php
    require "smsAuth.php";
    ```
* 第三步：调用`SmsAuth::headWithAuth`函数来生成HTTP请求头。

    ```php
    // 代码示例
    $ak = "AccessKeyId";
    $sk = "SecretAccessKey";
    $httpMethod = "POST";
    $uri = "/v1/message";
    $host = "sms.bj.baidubce.com";
    $expirationInSeconds = 3600;
    
    $head = SmsAuth::headWithAuth($ak, $sk, $httpMethod, $uri, $host, $expirationInSeconds);
    ```

## 代码简介：

SMS签名算法包括两个文件：

* `sign_sample.php`
* `smsAuth.php`

其中，`sign_sample.php`是百度开放云官方提供的签名算法的代码。在这份代码的最下面是一些测试相关的代码，我们并不需要，因此把这部分注释掉。如下所示：

```php
//签名示范代码
// date_default_timezone_set("UTC");
// $signer = new SampleSigner();
// $credentials = array("ak" => "0b0f67dfb88244b289b72b142befad0c","sk" => "bad522c2126a4618a8125f4b6cf6356f");
// $httpMethod = "PUT";
// $path = "/v1/test/myfolder/readme.txt";
// $headers = array("Host" => "bj.bcebos.com",
//                 "Content-Length" => 8,
//                 "Content-MD5" => "0a52730597fb4ffa01fc117d9e71e3a9",
//                 "Content-Type" => "text/plain",
//                 "x-bce-date" => "2015-04-27T08:23:49Z");
// $params = array("partNumber" => 9, "uploadId" => "VXBsb2FkIElpZS5tMnRzIHVwbG9hZA");
// $timestamp = new \DateTime();
// $timestamp->setTimestamp(1430123029);
// $options = array(SignOption::TIMESTAMP => $timestamp);
// $ret = $signer->sign($credentials, $httpMethod, $path, $headers, $params, $options);
// print $ret;
```

`smsAuth.php`针对SMS API的要求对`sign_sample.php`进行了封装，并提供了`SmsAuth::headWithAuth`函数来生成HTTP请求头（内含认证字符串）。