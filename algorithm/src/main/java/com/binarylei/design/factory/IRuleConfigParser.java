package com.binarylei.design.factory;

/**
 * @author binarylei
 * @version 2020-02-29
 */
public interface IRuleConfigParser {

    RuleConfig parse(String configText);
}
