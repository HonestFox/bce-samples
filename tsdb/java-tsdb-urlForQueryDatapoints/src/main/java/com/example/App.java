package com.example;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.tsdb.TsdbClient;
import com.baidubce.services.tsdb.TsdbConstants;
import com.baidubce.services.tsdb.model.Aggregator;
import com.baidubce.services.tsdb.model.Filters;
import com.baidubce.services.tsdb.model.GroupBy;
import com.baidubce.services.tsdb.model.Query;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * 生成一个预签名的URL，前端可以使用这个URL来查询数据点。
 */
public class App {

    private static Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        // 创建TsdbClient
        String ak = "";  // AccessKeyId
        String sk = "";  // SecretAccessKey
        String endpoint = "";  // {dbName}.tsdb.iot.gz.baidubce.com

        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(ak, sk))
                .withEndpoint(endpoint);
        TsdbClient client = new TsdbClient(config);

        // 创建查询对象
        String metric = "pm25";
        List<Query> queries = Arrays.asList(new Query()
                .withMetric(metric)
                .withFilters(new Filters()
                        .withRelativeStart("1 year ago"))
                .addGroupBy(new GroupBy()
                        .withName(TsdbConstants.GROUP_BY_NAME_TIME)
                        .withTimeRangeSize("1 second")
                        .withGroupCount(1))
                .withLimit(100)
                .addAggregator(new Aggregator()
                        .withName(TsdbConstants.AGGREGATOR_NAME_SUM)
                        .withSampling("1 second")));

        // 生成URL
        Integer expirationInSeconds = 1800;
        URL url = client.generatePresignedUrlForQueryDatapoints(queries, expirationInSeconds);
        logger.info(url.toString());
    }
}
