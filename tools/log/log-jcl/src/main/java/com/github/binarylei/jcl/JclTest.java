package com.github.binarylei.jcl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * @author leigang
 * @version 2019-04-27
 * @since 2.0.0
 */
public class JclTest {

    @Test
    public void test() {
        Log log = LogFactory.getLog(JclTest.class);
        log.info("jcl log");
    }
}
