package com.binarylei.design.factory;

import com.binarylei.design.factory.support.JsonRuleConfigParser;
import com.binarylei.design.factory.support.JsonSystemConfigParser;
import com.binarylei.design.factory.support.XmlRuleConfigParser;
import com.binarylei.design.factory.support.XmlSystemConfigParser;

/**
 * 抽象工厂：包含 IRuleConfigParser 和 ISystemConfigParser 两个组件的创建，使用少
 *
 * @author binarylei
 * @version 2020-02-29
 */

public interface IConfigParserFactory {
    IRuleConfigParser createRuleParser();

    ISystemConfigParser createSystemParser();
    //此处可以扩展新的parser类型，比如IBizConfigParser
}

class JsonConfigParserFactory implements IConfigParserFactory {
    @Override
    public IRuleConfigParser createRuleParser() {
        return new JsonRuleConfigParser();
    }

    @Override
    public ISystemConfigParser createSystemParser() {
        return new JsonSystemConfigParser();
    }
}

class XmlConfigParserFactory implements IConfigParserFactory {
    @Override
    public IRuleConfigParser createRuleParser() {
        return new XmlRuleConfigParser();
    }

    @Override
    public ISystemConfigParser createSystemParser() {
        return new XmlSystemConfigParser();
    }
}

// 省略YamlConfigParserFactory和PropertiesConfigParserFactory代码
