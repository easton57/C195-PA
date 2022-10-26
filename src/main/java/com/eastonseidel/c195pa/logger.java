package com.eastonseidel.c195pa;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class logger {
    // Define our logger
    private static final Logger LOGGER = LogManager.getLogger(logger.class);

    /**
     * Simple method for INFO events
     * @param event informative event description
     * @param eventType the type of event to log. Blank for info
     */
    public static void addToLog(String event, String eventType) {
        if (eventType.equalsIgnoreCase("error")) {
            LOGGER.error(event);
        }
        else if (eventType.equalsIgnoreCase("fatal")) {
            LOGGER.fatal(event);
        }
        else {
            LOGGER.info(event);
        }
    }
}
