package com.binarylei.design.factory;

import com.binarylei.design.factory.support.JsonRuleConfigParser;
import com.binarylei.design.factory.support.PropertiesRuleConfigParser;
import com.binarylei.design.factory.support.XmlRuleConfigParser;
import com.binarylei.design.factory.support.YamlRuleConfigParser;

import java.util.HashMap;
import java.util.Map;

/**
 * 简单工厂：缓存(单例)
 *
 * @author binarylei
 * @version 2020-02-29
 */
public class RuleConfigParserFactory_v2 {
    private static final Map<String, IRuleConfigParser> cachedParsers = new HashMap<>();

    static {
        cachedParsers.put("json", new JsonRuleConfigParser());
        cachedParsers.put("xml", new XmlRuleConfigParser());
        cachedParsers.put("yaml", new YamlRuleConfigParser());
        cachedParsers.put("properties", new PropertiesRuleConfigParser());
    }

    public static IRuleConfigParser createParser(String configFormat) {
        if (configFormat == null || configFormat.isEmpty()) {
            return null;//返回null还是IllegalArgumentException全凭你自己说了算
        }
        IRuleConfigParser parser = cachedParsers.get(configFormat.toLowerCase());
        return parser;
    }
}
