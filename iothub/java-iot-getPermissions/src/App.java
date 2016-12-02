import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.iothub.IotHubClient;
import com.baidubce.services.iothub.model.ListPermissionResponse;
import com.baidubce.services.iothub.model.Permission;

import java.util.logging.Logger;

/**
 * Created by hongtao on 2016/12/2.
 *
 * 查询主题（topic）列表
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
        String policyName = "";
        ListPermissionResponse response = client.listPermission(endpointName, policyName);
        printResponse(response);
    }

    private static void printResponse(ListPermissionResponse response) {
        for (Permission permission: response.getResult()) {
            String info = String.format("policy uuid: %s\n"
                    + "permission uuid: %s\n"
                    + "topic: %s\n"
                    + "operations: %s\n",
                    permission.getPolicyUuid(),
                    permission.getPermissionUuid(),
                    permission.getTopic(),
                    permission.getOperations()
            );
            logger.info(info);
        }
    }
}
