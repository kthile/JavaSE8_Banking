package com.revature.services;

import org.apache.log4j.Logger;

public class LoggingService {

	private static Logger log = Logger.getRootLogger();

	public void logTrace(String msg) {
		log.trace(msg);
	}

	public void logDebug(String msg) {
		log.debug(msg);
	}

	public void logInfo(String msg) {
		log.info(msg);
	}

	public void logWarn(String msg) {
		log.warn(msg);
	}

	public void logError(String msg) {
		log.error(msg);
	}

	public void logFatal(String msg) {
		log.error(msg);
	}

}
