package com.example;

import com.baidubce.services.ruleengine.RuleEngineClient;
import com.baidubce.services.ruleengine.model.Destination;
import com.baidubce.services.ruleengine.model.DestinationKind;

/**
 * 为某一条规则增加一个目的地
 */
public class App {

    public static void main(String[] args) {
        String ak = "";  // AccessKeyId
        String sk = "";  // SecretAccessKey
        RuleEngineClient client = new RuleEngineClient(ak, sk);

        Destination destination = new Destination();
        destination.setRuleUuid("要修改的规则的UUID");

        // 增加一个kafka主题为新的目的地
        destination.setKind(DestinationKind.KAFKA);
        destination.setValue("KAFKA主题");

        client.createDestination(destination);
    }
}
