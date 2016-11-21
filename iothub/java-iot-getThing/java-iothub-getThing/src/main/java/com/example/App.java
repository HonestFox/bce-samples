package com.example;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.iothub.IotHubClient;
import com.baidubce.services.iothub.model.QueryThingResponse;

import java.util.logging.Logger;

/**
 * 查看单个设备（thing）的信息
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

        String endpointName = "";  // 要查询的thing在哪一个iothub实例中
        String thingName = "";  // 要查询的thing的名称
        QueryThingResponse response = client.queryThing(endpointName, thingName);
        printThing(response);
    }

    private static void printThing(QueryThingResponse response) {
        String info = String.format("username: %s\n"
                + "endpoint name: %s\n"
                + "thing name: %s\n"
                + "create time: %s\n",
                response.getUsername(),
                response.getEndpointName(),
                response.getThingName(),
                response.getCreateTime());
        logger.info(info);
    }
}
