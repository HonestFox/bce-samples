package com.example;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.iothub.IotHubClient;
import com.baidubce.services.iothub.model.ListResponse;

import java.util.HashMap;
import java.util.logging.Logger;

/**
 * 查看某个endpoint中的全部设备（thing）
 */
public class App {

    private static Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        String ak = "";  // AccessKeyId
        String sk = "";  // SecretAccessKey
        String endpoint = "iot.gz.baidubce.com";

        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(ak, sk))
                .withEndpoint(endpoint);
        IotHubClient client = new IotHubClient(config);

        String endpointName = "";  // 要列出哪一个iothub实例中的thing
        ListResponse response = client.listThings(endpointName);
        for (HashMap<String, String> thingInfo : response.getResult()) {
            logger.info(String.valueOf(thingInfo));
        }
    }
}
