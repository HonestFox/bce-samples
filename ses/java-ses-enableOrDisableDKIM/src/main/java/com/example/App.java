package com.example;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.ses.SesClient;
import com.baidubce.services.ses.SesClientConfiguration;

/**
 * 针对某个"发信邮件域", 启用或禁用DKIM。
 *
 * 一般建议以下列方式使用:
 *
 * (1) 认证邮件域, 并开启DKIM。
 *      client.verifyDomain("abc.com");  // 认证邮件域(生成一条TXT记录, 需手动添加到DNS解析中)
 *      client.enalbeDKIM("abc.com");  // 为该邮件域开启DKIM
 *      client.verifyDKIM("abc.com");  // 认证DKIM(生成一条CNAME记录, 需手动添加到DNS解析中)
 *
 * (2) 认证邮件域, 但不开启DKIM。
 *      client.verifyDomain("abc.com");  // 认证邮件域(生成一条TXT记录, 需手动添加到DNS解析中)
 *      client.disableDKIM("abc.com");  // 为该邮件域禁用DKIM
 */
public class App 
{
    public static void main( String[] args )
    {
        String AK = "cca75974c3134bc1877ad3663b1d197d";  // AccessKeyId
        String SK = "e51fb332ef314a13bf106291b62c52c7";  // SecretAccessKey

        SesClientConfiguration configuration = new SesClientConfiguration();
        configuration.setCredentials(new DefaultBceCredentials(AK, SK));
        SesClient client = new SesClient(configuration);

        String domain = "floodliu.com";
//        client.enableDKIM(domain);  // 启用DKIM
        client.disableDKIM(domain);  // 禁用DKIM
    }
}
