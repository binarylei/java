package com.github.binarylei.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KarafLog {
	private static final Logger log = LoggerFactory.getLogger(KarafLog.class);
	
	public void start() {
		log.info("slf4j info log");
		log.warn("slf4j warn log");
		log.error("slf4j error log");
		
		log.error("error log",new Exception("slf4j error log"));
	}
}
