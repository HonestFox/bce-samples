import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.iothub.IotHubClient;
import com.baidubce.services.iothub.model.ListResponse;

import java.util.logging.Logger;

/**
 * Created by hongtao on 2016/12/2.
 *
 * 查看策略（policy）列表。
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
        ListResponse response = client.listPolicy(endpointName);
        logger.info(response.getResult().toString());
    }
}
