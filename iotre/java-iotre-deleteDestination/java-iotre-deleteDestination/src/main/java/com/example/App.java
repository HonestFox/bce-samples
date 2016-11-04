package com.example;

import com.baidubce.services.ruleengine.RuleEngineClient;

/**
 * 从一条规则中移除一个目的地
 */
public class App 
{
    public static void main( String[] args )
    {
        String ak = "";  // AccessKeyId
        String sk = "";  // SecretAccessKey
        RuleEngineClient client = new RuleEngineClient(ak, sk);

        String destinationUuid = "";  // 要删除的destination的UUID
        client.deleteDestination(destinationUuid);
    }
}
