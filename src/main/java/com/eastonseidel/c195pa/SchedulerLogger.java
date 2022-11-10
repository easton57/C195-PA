package com.eastonseidel.c195pa;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.*;

public class SchedulerLogger {
    // Define our logger
    static Handler fileHandler = null;
    private static Logger LOGGER = null;

    static {
        // Formatting file
        InputStream stream = SchedulerLogger.class.getClassLoader().getResourceAsStream("ScheduleLogger.properties");

        try {
            LogManager.getLogManager().readConfiguration(stream);
            LOGGER = Logger.getLogger(SchedulerLogger.class.getName());
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        // Load log configuration
        try {
            fileHandler = new FileHandler("./login_activity.txt", true); /* Change to create the file based on the date and utc time*/
            SimpleFormatter simple = new SimpleFormatter();
            fileHandler.setFormatter(simple);

            LOGGER.addHandler(fileHandler);

        } catch (IOException e) {
            System.out.println("Error creating log file! " + e);
        }

        // Set the logger level
        LOGGER.setLevel(Level.INFO);
    }

    /**
     * Simple method for INFO events
     * @param event informative event description
     * @param eventType the type of event to log. Blank for info
     */
    public static void addToLog(String event, String eventType) {
        if (eventType.equalsIgnoreCase("warning")) {
            LOGGER.warning(event);
        }
        else if (eventType.equalsIgnoreCase("severe")) {
            LOGGER.severe(event);
        }
        else {
            LOGGER.info(event);
        }
    }
}
