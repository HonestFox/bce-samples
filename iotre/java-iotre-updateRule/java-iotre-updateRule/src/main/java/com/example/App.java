package com.example;

import com.baidubce.services.ruleengine.RuleEngineClient;
import com.baidubce.services.ruleengine.model.Destination;
import com.baidubce.services.ruleengine.model.Rule;
import com.baidubce.services.ruleengine.model.UpdateRuleRequest;

import java.util.logging.Logger;

/**
 * 修改某一条规则
 */
public class App 
{
    private static Logger logger = Logger.getLogger(App.class.getName());

    public static void main( String[] args )
    {
        String ak = "";  // AccessKeyId
        String sk = "";  // SecretAccessKey
        RuleEngineClient client = new RuleEngineClient(ak, sk);

        UpdateRuleRequest request = new UpdateRuleRequest();
        request.setUuid("要修改的规则的UUID");

        // 可修改的值：description, select, from, where
        request.setDescription("零下的温度");
        request.setSelect("*");
        request.setFrom("temperature/#");
        request.setWhere("`value` < 0");

        Rule rule = client.updateRule(request);
        printRule(rule);
    }

    private static void printRule(Rule rule) {
        String info = "uuid: " + rule.getUuid() + "\n"
                + "\tname: " + rule.getName() + "\n"
                + "\tdescription: " + rule.getDescription() + "\n"
                + "\tendpoint: " + rule.getEndpoint() + "\n"
                + "\tendpoint uuid: " + rule.getEndpointUuid() + "\n"
                + "\tselect: " + rule.getSelect() + "\n"
                + "\tfrom: " + rule.getFrom() + "\n"
                + "\twhere: " + rule.getWhere() + "\n"
                + "\tstate: " + rule.getState() + "\n"
                + "\tcreate time: " + rule.getCreateTime() + "\n"
                + "\tupdate time: " + rule.getUpdateTime() + "\n"
                + "\tdestinations:" + "\n";

        for (Destination destination: rule.getDestinations()) {
            info = info + "\t\tuuid: " + destination.getUuid() + "\n"
                    + "\t\t\trule uuid: " + destination.getRuleUuid() + "\n"
                    + "\t\t\tkind: " + destination.getKind() + "\n"
                    + "\t\t\tvalue: " + destination.getValue() + "\n"
                    + "\t\t\tstatus: " + destination.getStatus() + "\n";
        }

        logger.info(info);
    }
}
