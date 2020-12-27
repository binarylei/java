package com.binarylei.design.factory;

import com.binarylei.design.factory.support.InvalidRuleConfigException;

/**
 * 工厂方法：IRuleConfigParserFactory 采用工厂方法创建
 * 定义一个创建对象的接口，但让实现这个接口的类决定实例化哪个类，工厂方法让类的实例化推迟到子类中进行。
 *
 * @author binarylei
 * @version 2020-02-29
 */

public class RuleConfigSource_v4 {
    public RuleConfig load(String ruleConfigFilePath) {
        String fileExtension = getFileExtension(ruleConfigFilePath);

        IRuleConfigParserFactory parserFactory = null;
        if ("json".equalsIgnoreCase(fileExtension)) {
            parserFactory = new JsonRuleConfigParserFactory();
        } else if ("xml".equalsIgnoreCase(fileExtension)) {
            parserFactory = new XmlRuleConfigParserFactory();
        } else if ("yaml".equalsIgnoreCase(fileExtension)) {
            parserFactory = new YamlRuleConfigParserFactory();
        } else if ("properties".equalsIgnoreCase(fileExtension)) {
            parserFactory = new PropertiesRuleConfigParserFactory();
        } else {
            throw new InvalidRuleConfigException("Rule config file format is not supported: " + ruleConfigFilePath);
        }
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
