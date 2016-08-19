package com.example;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.ses.SesClient;
import com.baidubce.services.ses.SesClientConfiguration;
import com.baidubce.services.ses.model.EmailDetailModel;
import com.baidubce.services.ses.model.ListVerifiedEmailResponse;

/**
 * 获取所有"发信邮箱"的验证状态。
 *
 * 状态码:
 *  0 - 认证通过
 *  1 - 认证失败
 *  2 - 暂时失败
 *  3 - 认证中
 *  4 - 认证未开始
 *  5 - 不存在
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

        ListVerifiedEmailResponse verifiedEmails = client.listVerifiedEmail();
        for (EmailDetailModel detail : verifiedEmails.getDetails()) {
            String format = "邮箱地址: %s, 验证状态: %d";
            System.out.println(String.format(format, detail.getAddress(), detail.getStatus()));
        }
    }
}
