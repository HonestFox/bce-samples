import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.iothub.IotHubClient;

/**
 * Created by hongtao on 2016/12/2.
 *
 * 把一个策略（policy）绑定到一个身份（principal）上
 */
public class App {

    public static void main(String args[]) {
        String ak = "";
        String sk = "";
        String host = "iot.gz.baidubce.com";

        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(ak, sk))
                .withEndpoint(host);
        IotHubClient client = new IotHubClient(config);

        String endpointName = "";
        String principalName = "";
        String policyName = "";
        client.attachPrincipalToPolicy(endpointName, principalName, policyName);
    }
}
