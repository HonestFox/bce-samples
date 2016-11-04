package com.example;

import com.baidubce.services.ruleengine.RuleEngineClient;
import com.baidubce.services.ruleengine.model.CreateRuleRequest;
import com.baidubce.services.ruleengine.model.Destination;
import com.baidubce.services.ruleengine.model.DestinationKind;
import com.baidubce.services.ruleengine.model.Rule;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * 创建规则。
 *
 * 假设消息格式如下：
 * {
 *      "metric": "temperature",
 *      "value": 10,
 *      "timestamp": 1478156459905,
 *      "city": "beijing"
 * }
 *
 * 然后，我们筛选出所有大于0度的温度，并转发到另外一个topic或tsdb。
 */
public class App {

    private static Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        // 第一步：创建RuleEngineClient
        String ak = "";  // AccessKeyId
        String sk = "";  // SecretAccessKey
        RuleEngineClient client = new RuleEngineClient(ak, sk);

        // 第二步：设置规则的相关信息，并创建CreateRuleRequest对象。
        String ruleName = "temperature_above_0";
        String ruleDescription = "零上的温度";
        String fromEndpointName = "food";  // iothub实例的名称

        String select = "*";
        String fromTopic = "temperature/#";  // iothub实例中的主题
        String where = "`value` > 0";

        CreateRuleRequest request = new CreateRuleRequest();
        request.setName(ruleName);
        request.setDescription(ruleDescription);
        request.setEndpoint(fromEndpointName);
        request.setSelect(select);
        request.setFrom(fromTopic);
        request.setWhere(where);

        // 第三步：对于筛选出的数据，设置其目的地。
        List<Destination> destinations = new ArrayList<Destination>();

        // 把筛选出的数据转发到另一个主题
        String toTopic = "temperature_above_0";
        Destination mqttDestination = new Destination();
        mqttDestination.setKind(DestinationKind.MQTT);
        mqttDestination.setValue(toTopic);
        destinations.add(mqttDestination);

        // 把筛选出的数据转存到TSDB
        String toTsdb = "sampledata.tsdb.iot.gz.baidubce.com";
        Destination tsdbDestination = new Destination();
        tsdbDestination.setKind(DestinationKind.TSDB);
        tsdbDestination.setValue(toTsdb);
        destinations.add(tsdbDestination);

        request.setDestinations(destinations);

        // 发送请求，创建规则，并打印相关信息。
        Rule rule = client.createRule(request);
        logger.info(String.format("rule %s with uuid: %s", rule.getName(), rule.getUuid()));
        for (Destination destination: rule.getDestinations()) {
            logger.info(String.format("destination with uuid: %s", destination.getUuid()));
        }
    }
}
