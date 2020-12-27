package com.binarylei.design.factory;

/**
 * 工厂方法：提取工厂方法创建的工厂
 *
 * @author binarylei
 * @version 2020-02-29
 */

public class RuleConfigSource_v5 {
    public RuleConfig load(String ruleConfigFilePath) {
        String ruleConfigFileExtension = getFileExtension(ruleConfigFilePath);

        IRuleConfigParserFactory parserFactory = RuleConfigParserFactoryMap.getParserFactory(ruleConfigFileExtension);
        IRuleConfigParser parser = parserFactory.createParser();

        String configText = "";
        //从ruleConfigFilePath文件中读取配置文本到configText中
        RuleConfig ruleConfig = parser.parse(configText);
        return ruleConfig;
    }

    private String getFileExtension(String filePath) {
        //...解析文件名获取扩展名，比如rule.json，返回json
        return "json";
    }

}

