package com.example;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.ses.SesClient;
import com.baidubce.services.ses.SesClientConfiguration;
import com.baidubce.services.ses.model.IsInRecipientBlacklistResponse;

/**
 * 查询某个邮箱是否在"接收邮箱黑名单"中。
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

        String email = "";  // 待查询的邮箱地址, 比如: xyz@abc.com
        IsInRecipientBlacklistResponse response = client.isInRecipientBlacklist(email);
        System.out.println(response);
    }
}
