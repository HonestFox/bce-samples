package com.example;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.ses.SesClient;
import com.baidubce.services.ses.SesClientConfiguration;
import com.baidubce.services.ses.model.VerifyDomainResponse;

/**
 * 认证"发信邮件域"。
 *
 * 该功能与"管理控制台"中的"添加发信邮件域"相同, 接口调用成功之后, SES会生成一条DNS TXT记录。
 * 需要进入"管理控制台"查看生成的TXT记录的详细信息, 并把它添加到域名的DNS解析设置中。
 */
public class App
{
    public static void main( String[] args )
    {
        String AK = "";  // AccessKeyId
        String SK = "";  // SecretAccessKey

        SesClientConfiguration configuration = new SesClientConfiguration();
        configuration.setCredentials(new DefaultBceCredentials(AK, SK));
        SesClient client = new SesClient(configuration);

        VerifyDomainResponse response = client.verifyDomain("域名");  // 用作"发信邮件域"的域名, 如: abc.com
        System.out.println(String.format("DNS记录值: %s", response.getToken()));
    }
}
