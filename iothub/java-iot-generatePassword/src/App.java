import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.iothub.IotHubClient;
import com.baidubce.services.iothub.model.CreatePrincipalResponse;

import java.util.logging.Logger;

/**
 * Created by hongtao on 2016/12/2.
 *
 * 重新生成身份密钥
 */
public class App {

    private static Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        String ak = "";  // AccessKeyId
        String sk = "";  // SecretAccessKey
        String host = "iot.gz.baidubce.com";

        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(ak, sk))
                .withEndpoint(host);
        IotHubClient client = new IotHubClient(config);

        String endpointName = "";
        String principalName = "";
        CreatePrincipalResponse response = client.regenerateCert(endpointName, principalName);
        printResponse(response);
    }

    private static void printResponse(CreatePrincipalResponse response) {
        String info = String.format("endpoint name: %s\n"
                + "principal name: %s\n"
                + "password: %s\n",
                response.getEndpointName(),
                response.getPrincipalName(),
                response.getPassword()
        );
        logger.info(info);
    }
}
