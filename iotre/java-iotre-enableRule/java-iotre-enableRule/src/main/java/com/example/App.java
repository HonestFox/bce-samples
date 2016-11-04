package com.example;

import com.baidubce.services.ruleengine.RuleEngineClient;

/**
 * 启用某一条规则
 */
public class App {

    public static void main(String[] args) {
        String ak = "";  // AccessKeyId
        String sk = "";  // SecretAccessKey
        RuleEngineClient client = new RuleEngineClient(ak, sk);

        String ruleUuid = "";
        client.enableRule(ruleUuid);
    }
}
