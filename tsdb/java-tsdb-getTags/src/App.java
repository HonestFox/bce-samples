import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.tsdb.TsdbClient;
import com.baidubce.services.tsdb.model.GetTagsResponse;

import java.util.List;
import java.util.Map;

/**
 * 查询某个metric对应的所有tags。
 */
public class App {

    public static void main(String[] args) {
        String ak = "";  // AccessKeyID
        String sk = "";  // SecretAccessKey
        String endpoint = "";  // {database}.tsdb.iot.gz.baidubce.com
        String metric = "";  // 查询tags时必须要指定metric

        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(ak, sk))
                .withEndpoint(endpoint);
        TsdbClient client = new TsdbClient(config);

        GetTagsResponse response = client.getTags(metric);
        for (Map.Entry<String, List<String>> entry : response.getTags().entrySet()) {
            String tagKey = entry.getKey();
            List<String> tagValue = entry.getValue();
            System.out.println(tagKey + " : " + tagValue);
        }
    }
}
