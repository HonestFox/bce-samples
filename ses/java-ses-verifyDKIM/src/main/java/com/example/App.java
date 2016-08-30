package com.example;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.ses.SesClient;
import com.baidubce.services.ses.SesClientConfiguration;
import com.baidubce.services.ses.model.VerifyDKIMResponse;

/**
 * 认证DKIM。
 *
 * 在认证"发信邮件域"的时候, 可以开启"DKIM"认证, 接口调用成功后会生成一条CNAME记录, 需要手动在DNS解析中添加该条目才能完成认证。
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

        String domain = "发信邮件域的域名";
        client.enableDKIM(domain);
        VerifyDKIMResponse response = client.verifyDKIM(domain);
        System.out.println(response);
    }
}
