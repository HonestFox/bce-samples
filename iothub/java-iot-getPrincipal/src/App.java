import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.iothub.IotHubClient;
import com.baidubce.services.iothub.model.QueryPrincipalResponse;

import java.util.logging.Logger;

/**
 * Created by hongtao on 2016/11/29.
 *
 * 查看某一个principal的详情
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

        String endpointName = "";  // 在哪一个iothub实例中查询principal
        String principalName = "";  // 要查询的principal的名称
        QueryPrincipalResponse response = client.queryPrincipal(endpointName, principalName);
        printResponse(response);
    }

    private static void printResponse(QueryPrincipalResponse response) {
        String info = String.format("endpointName: %s\n"
                + "principalName: %s\n"
                + "createTime: %s\n",
                response.getEndpointName(),
                response.getPrincipalName(),
                response.getCreateTime()
        );
        logger.info(info);
    }
}
