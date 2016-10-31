import com.baidubce.BceClientConfiguration;
import com.baidubce.Protocol;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.tsdb.TsdbClient;
import com.baidubce.services.tsdb.model.Datapoint;
import com.baidubce.services.tsdb.model.WriteDatapointsResponse;

import java.util.Arrays;
import java.util.List;

/**
 * 向TSDB写入数据点。
 */
public class App {

    public static void main(String[] args) {
        String ak = "";  // AccessKeyID
        String sk = "";  // SecretAccessKey
        String endpoint = "";  // TSDB的连接地址, 如: {database}.tsdb.iot.gz.baidubce.com

        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(ak, sk))
                .withEndpoint(endpoint);
        TsdbClient client = new TsdbClient(config);

        // 如果需要创建HTTPS连接, 可以使用如下代码。
//        BceClientConfiguration config = new BceClientConfiguration()
//                .withProtocol(Protocol.HTTPS)
//                .withCredentials(new DefaultBceCredentials(ak, sk))
//                .withEndpoint(endpoint);
//        TsdbClient client = new TsdbClient(config);

        String metric = "cpu_idle";
        String tagKey = "host";
        String tagValue = "server1";

        List<Datapoint> datapoints = Arrays.asList(new Datapoint()
                .withMetric(metric)
                .addTag(tagKey, tagValue)
                .addDoubleValue(System.currentTimeMillis(), 0.1)
        );
        WriteDatapointsResponse response = client.writeDatapoints(datapoints);
        System.out.println(response.getMetadata());

        // 如果有多个数据具有相同的metric和tags, 可以使用如下的方式减少HTTP请求的payload。
//        List<Datapoint> datapoints = Arrays.asList(new Datapoint()
//                .withMetric(metric)
//                .addTag(tagKey, tagValue)
//                .addDoubleValue(System.currentTimeMillis(), 0.1)
//                .addLongValue(System.currentTimeMillis(), 99)
//                .addStringValue(System.currentTimeMillis(), "hello world")
//                .addBytesValue(System.currentTimeMillis(), new byte[] {0x01, 0x02})
//        );
//        WriteDatapointsResponse response = client.writeDatapoints(datapoints);
//        System.out.println(response.getMetadata());
    }
}
