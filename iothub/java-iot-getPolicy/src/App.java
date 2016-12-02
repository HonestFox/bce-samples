import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.iothub.IotHubClient;
import com.baidubce.services.iothub.model.QueryPolicyResponse;

import java.util.logging.Logger;

/**
 * Created by hongtao on 2016/12/2.
 *
 * 查询单个策略（policy）详情。
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

        String endpointName = "";
        String policyName = "";
        QueryPolicyResponse response = client.queryPolicy(endpointName, policyName);
        printResponse(response);
    }

    private static void printResponse(QueryPolicyResponse response) {
        String info = String.format("endpoint name: %s\n"
                + "policy name: %s\n"
                + "create time: %s\n",
                response.getEndpointName(),
                response.getPolicyName(),
                response.getCreateTime()
        );
        logger.info(info);
    }
}
