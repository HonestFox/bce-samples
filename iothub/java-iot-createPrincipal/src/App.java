import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.iothub.IotHubClient;
import com.baidubce.services.iothub.model.CreatePrincipalResponse;

import java.util.logging.Logger;

/**
 * Created by hongtao on 2016/11/28.
 *
 * 创建身份（principal）
 */
public class App {

    private static Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String args[]) {
        String ak = "";  // AccessKeyId
        String sk = "";  // SecretAccessKey
        String host = "iot.gz.baidubce.com";
        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(ak, sk))
                .withEndpoint(host);
        IotHubClient client = new IotHubClient(config);

        String endpointName = "";  // 在哪一个iothub实例中创建principal
        String principalName = "";  // 要创建的principal的名称
        CreatePrincipalResponse response = client.createPrincipal(endpointName, principalName);
        printResponse(response);
    }

    private static void printResponse(CreatePrincipalResponse response) {
        String info = String.format("endpointName: %s\n"
                + "principalName: %s\n"
                + "createTime: %s\n"
                + "password: %s\n",
                response.getEndpointName(),
                response.getPrincipalName(),
                response.getCreateTime(),
                response.getPassword()
        );
        logger.info(info);
    }
}
