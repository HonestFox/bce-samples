package com.example;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.ses.SesClient;
import com.baidubce.services.ses.SesClientConfiguration;
import com.baidubce.services.ses.model.GetQuotaResponse;

/**
 * 查询配额。
 *
 * 主要包括:
 *  maxPerDay - 每日发信配额(单位: 封/天)
 *  maxPerSecond - 最大发送频率(单位: 封/秒)
 *  usedToday - 今日已发信数量(单位: 封)
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

        GetQuotaResponse quota = client.getQuota();
        System.out.println("每日发信配额: " + quota.getMaxPerDay());
        System.out.println("最大发送频率: " + quota.getMaxPerSecond());
        System.out.println("今日已发送: " + quota.getUsedToday());
    }
}
