import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.iothub.IotHubClient;
import com.baidubce.services.iothub.model.Operation;
import com.baidubce.services.iothub.model.QueryPermissionResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by hongtao on 2016/12/2.
 *
 * 创建一个主题（topic）并且配置相应的权限（SUB、PUB）
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
        String topic = "";

        List<Operation> operations = new ArrayList<>();
        operations.add(Operation.PUBLISH);
        operations.add(Operation.SUBSCRIBE);

        QueryPermissionResponse response = client.createPermission(endpointName, policyName, operations, topic);
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
