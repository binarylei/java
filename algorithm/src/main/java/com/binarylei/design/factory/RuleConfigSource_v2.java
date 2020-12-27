package com.binarylei.design.factory;

import com.binarylei.design.factory.support.InvalidRuleConfigException;
import com.binarylei.design.factory.support.JsonRuleConfigParser;
import com.binarylei.design.factory.support.PropertiesRuleConfigParser;
import com.binarylei.design.factory.support.XmlRuleConfigParser;
import com.binarylei.design.factory.support.YamlRuleConfigParser;

/**
 * 抽象出单独的 createParser 创建解析器
 *
 * @author binarylei
 * @version 2020-02-29
 */

public class RuleConfigSource_v2 {
    public RuleConfig load(String ruleConfigFilePath) {
        String fileExtension = getFileExtension(ruleConfigFilePath);
        IRuleConfigParser parser = createParser(fileExtension);

        String configText = "";
        //从ruleConfigFilePath文件中读取配置文本到configText中
        RuleConfig ruleConfig = parser.parse(configText);
        return ruleConfig;
    }

    private IRuleConfigParser createParser(String fileExtension) {
        IRuleConfigParser parser = null;
        if ("json".equalsIgnoreCase(fileExtension)) {
            parser = new JsonRuleConfigParser();
        } else if ("xml".equalsIgnoreCase(fileExtension)) {
            parser = new XmlRuleConfigParser();
        } else if ("yaml".equalsIgnoreCase(fileExtension)) {
            parser = new YamlRuleConfigParser();
        } else if ("properties".equalsIgnoreCase(fileExtension)) {
            parser = new PropertiesRuleConfigParser();
        } else {
            throw new InvalidRuleConfigException(
                    "Rule config file format is not supported: " + fileExtension);
        }
        return parser;
    }

    private String getFileExtension(String filePath) {
        //...解析文件名获取扩展名，比如rule.json，返回json
        return "json";
    }
}
