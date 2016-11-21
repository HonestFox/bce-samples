package com.example;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.iothub.IotHubClient;

/**
 * 删除一个设备（thing）
 */
public class App 
{
    public static void main( String[] args )
    {
        String ak = "";  // AccessKeyId
        String sk = "";  // SecretAccessKey
        String endpoint = "iot.gz.baidubce.com";

        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(ak, sk))
                .withEndpoint(endpoint);
        IotHubClient client = new IotHubClient(config);

        String endpointName = "";  // 要删除的thing在哪一个iothub实例中
        String thingName = "";  // 要删除的thing的名称
        client.deleteThing(endpointName, thingName);
    }
}
