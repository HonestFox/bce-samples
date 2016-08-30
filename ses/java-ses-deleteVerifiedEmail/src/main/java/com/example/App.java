package com.example;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.ses.SesClient;
import com.baidubce.services.ses.SesClientConfiguration;

/**
 * 删除单个"发信邮箱"。
 * <p>
 * 不管该"发信邮箱"处于什么认证状态, 被删除后, 都不能再被用作"发信邮箱"。
 * 如果需要再次使其成为"发信邮箱", 需要重新认证。
 */
public class App {

    public static void main(String[] args) {
        String AK = "";  // AccessKeyId
        String SK = "";  // SecretAccessKey

        SesClientConfiguration configuration = new SesClientConfiguration();
        configuration.setCredentials(new DefaultBceCredentials(AK, SK));
        SesClient client = new SesClient(configuration);

        client.deleteVerifiedEmail("发信邮箱的地址");
    }
}
