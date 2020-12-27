package com.binarylei.design.factory;

import com.binarylei.design.factory.support.InvalidRuleConfigException;
import com.binarylei.design.factory.support.JsonRuleConfigParser;
import com.binarylei.design.factory.support.PropertiesRuleConfigParser;
import com.binarylei.design.factory.support.XmlRuleConfigParser;
import com.binarylei.design.factory.support.YamlRuleConfigParser;

import java.util.HashMap;
import java.util.Map;

/**
 * 简单工厂：
 *
 * @author binarylei
 * @version 2020-02-29
 */
public class RuleConfigParserFactory_v1 {

    public static IRuleConfigParser createParser(String configFormat) {
        IRuleConfigParser parser = null;
        if ("json".equalsIgnoreCase(configFormat)) {
            parser = new JsonRuleConfigParser();
        } else if ("xml".equalsIgnoreCase(configFormat)) {
            parser = new XmlRuleConfigParser();
        } else if ("yaml".equalsIgnoreCase(configFormat)) {
            parser = new YamlRuleConfigParser();
        } else if ("properties".equalsIgnoreCase(configFormat)) {
            parser = new PropertiesRuleConfigParser();
        } else {
            throw new InvalidRuleConfigException(
                    "Rule config file format is not supported: " + configFormat);
        }
        return parser;
    }
}
