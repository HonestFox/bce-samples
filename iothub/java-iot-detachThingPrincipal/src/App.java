import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.iothub.IotHubClient;

/**
 * Created by hongtao on 2016/12/2.
 *
 * 解除设备（thing）与身份（principal）之间的绑定
 */
public class App {

    public static void main(String args[]) {
        String ak = "";  // AccessKeyId
        String sk = "";  // SecretAccessKey
        String host = "iot.gz.baidubce.com";

        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(ak, sk))
                .withEndpoint(host);
        IotHubClient client = new IotHubClient(config);

        String endpointName = "";
        String thingName = "";
        String principalName = "";
        client.removeThingToPrincipal(endpointName, thingName, principalName);
    }
}
