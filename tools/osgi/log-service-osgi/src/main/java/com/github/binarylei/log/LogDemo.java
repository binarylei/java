package com.github.binarylei.log;

import org.osgi.service.log.LogService;

public class LogDemo {
	private LogService logService;

	public void start() {
		logService.log(LogService.LOG_DEBUG, "this is osgi debug log");
		logService.log(LogService.LOG_INFO, "this is osgi info log");
		logService.log(LogService.LOG_WARNING, "this is osgi warning log");
		logService.log(LogService.LOG_ERROR, "this is osgi error log");
		
		logService.log(LogService.LOG_ERROR, "osgi error log", new Exception("error log"));
	}
	
	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
}
