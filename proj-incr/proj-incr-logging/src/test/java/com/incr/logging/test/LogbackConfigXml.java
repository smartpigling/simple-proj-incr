package com.incr.logging.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackConfigXml {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public void performTask(){
        logger.debug("This is a debug message.");
        logger.info("This is an info message.");
        logger.warn("This is a warn message.");
        logger.error("This is an error message.");

    }
}
