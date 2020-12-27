package com.binarylei.design.factory;

import com.binarylei.design.factory.support.JsonRuleConfigParser;
import com.binarylei.design.factory.support.PropertiesRuleConfigParser;
import com.binarylei.design.factory.support.XmlRuleConfigParser;
import com.binarylei.design.factory.support.YamlRuleConfigParser;

/**
 * 工厂方法：只有 IRuleConfigParser 一个组件创建。
 *
 * @author binarylei
 * @version 2020-02-29
 */

public interface IRuleConfigParserFactory {
    IRuleConfigParser createParser();
}

class JsonRuleConfigParserFactory implements IRuleConfigParserFactory {
    @Override
    public IRuleConfigParser createParser() {
        return new JsonRuleConfigParser();
    }
}

class XmlRuleConfigParserFactory implements IRuleConfigParserFactory {
    @Override
    public IRuleConfigParser createParser() {
        return new XmlRuleConfigParser();
    }
}

class YamlRuleConfigParserFactory implements IRuleConfigParserFactory {
    @Override
    public IRuleConfigParser createParser() {
        return new YamlRuleConfigParser();
    }
}

class PropertiesRuleConfigParserFactory implements IRuleConfigParserFactory {
    @Override
    public IRuleConfigParser createParser() {
        return new PropertiesRuleConfigParser();
    }
}