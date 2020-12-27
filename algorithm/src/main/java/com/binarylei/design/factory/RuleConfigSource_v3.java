package com.binarylei.design.factory;

/**
 * 简单工厂：抽象出单独的 createParser 创建解析器
 *
 * @author binarylei
 * @version 2020-02-29
 */

public class RuleConfigSource_v3 {
    public RuleConfig load(String ruleConfigFilePath) {
        String fileExtension = getFileExtension(ruleConfigFilePath);
        IRuleConfigParser parser = RuleConfigParserFactory_v1.createParser(fileExtension);

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
