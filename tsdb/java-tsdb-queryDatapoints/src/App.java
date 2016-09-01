import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.tsdb.TsdbClient;
import com.baidubce.services.tsdb.model.*;

import java.util.Arrays;
import java.util.List;

/**
 * 从TSDB中查询数据点。
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

        String metric = "cpu_idle";
        String tagKey = "host";
        String tagValue = "server1";
        List<Query> queries = Arrays.asList(new Query()
                .withMetric(metric)
                .withFilters(new Filters()
                        .withRelativeStart("7 days ago")
                        .addTag(tagKey, tagValue)
                )
        );

        QueryDatapointsResponse response = client.queryDatapoints(queries);
        for (Result result : response.getResults()) {
            System.out.println("Result:");

            for (Group group : result.getGroups()) {
                System.out.println("\tGroup:");

                for (GroupInfo info : group.getGroupInfos()) {
                    System.out.println("\t\tGroupInfo:");
                    System.out.println("\t\t\tName: " + info.getName());
                }

                System.out.println("\t\tTimeAndValue:");
                for (Group.TimeAndValue timeAndValue : group.getTimeAndValueList()) {
                    if (timeAndValue.isDouble()) {
                        System.out.println("\t\t\t[" + timeAndValue.getTime() + ", " + timeAndValue.getDoubleValue()
                                + "]");
                    } else if (timeAndValue.isLong()) {
                        System.out.println("\t\t\t[" + timeAndValue.getTime() + ", " + timeAndValue.getLongValue() +
                                "]");
                    } else if (timeAndValue.isString()) {
                        System.out.println("\t\t\t[" + timeAndValue.getTime() + ", " + timeAndValue.getStringValue() +
                                "]");
                    }
                }
            }
        }
    }
}
