package com.example;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.iothub.IotHubClient;
import com.baidubce.services.iothub.model.QueryEndpointResponse;

import java.util.logging.Logger;

/**
 * 创建一个iothub实例（endpoint）
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

        String endpointName = "要创建的实例名称";
        QueryEndpointResponse response = client.createEndpoint(endpointName);
        printEndpointInfo(response);
    }

    private static void printEndpointInfo(QueryEndpointResponse response) {
        String info = String.format(
                "uuid: %s\n"
                + "name: %s\n"
                + "create time: %s\n"
                + "host name: %s\n"
                + "mqtt host name: %s\n"
                + "mqtt tls host name: %s\n"
                + "websocket host name: %s\n"
                + "account uuid: %s",
                response.getUuid(),
                response.getEndpointName(),
                response.getCreateTime(),
                response.getHostname(),
                response.getMqttHostname(),
                response.getMqttTlsHostname(),
                response.getWebsocketHostname(),
                response.getAccountUuid());
        logger.info(info);
    }
}
