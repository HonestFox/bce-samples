import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.tsdb.TsdbClient;
import com.baidubce.services.tsdb.model.GetMetricsResponse;

/**
 * 查询所有metrics。
 */
public class App {

    public static void main(String[] args) {
        String ak = "";  // AccessKeyID
        String sk = "";  // SecretAccessKey
        String endpoint = "";  // {database}.tsdb.iot.gz.baidubce.com

        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(ak, sk))
                .withEndpoint(endpoint);
        TsdbClient client = new TsdbClient(config);

        GetMetricsResponse response = client.getMetrics();
        for (String metric : response.getMetrics()) {
            System.out.println(metric);
        }
    }
}
