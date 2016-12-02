import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.iothub.IotHubClient;
import com.baidubce.services.iothub.model.QueryPermissionResponse;

import java.util.logging.Logger;

/**
 * Created by hongtao on 2016/12/2.
 *
 * 查看某个主题的详情
 */
public class App {

    private static Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String args[]) {
        String ak = "";
        String sk = "";
        String host = "iot.gz.baidubce.com";

        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(ak, sk))
                .withEndpoint(host);
        IotHubClient client = new IotHubClient(config);

        String endpointName = "";
        String permissionUuid = "";
        QueryPermissionResponse response = client.queryPermission(endpointName, permissionUuid);
        printResponse(response);
    }

    private static void printResponse(QueryPermissionResponse response) {
        String info = String.format("policy uuid: %s\n"
                + "permission uuid: %s\n"
                + "topic: %s\n"
                + "operations: %s\n",
                response.getPolicyUuid(),
                response.getPermissionUuid(),
                response.getTopic(),
                response.getOperations()
        );
        logger.info(info);
    }
}
