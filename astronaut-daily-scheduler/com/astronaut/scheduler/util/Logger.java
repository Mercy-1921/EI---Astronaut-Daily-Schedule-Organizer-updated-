package com.astronaut.scheduler.util;

import java.util.logging.*;

/**
 * Custom lightweight logger for console + file logging.
 */
public class Logger {
    private static final java.util.logging.Logger logger =
            java.util.logging.Logger.getLogger("AstronautScheduler");

    static {
        try {
            FileHandler handler = new FileHandler("astronaut_schedule.log", true);
            handler.setFormatter(new SimpleFormatter() {
                @Override
                public synchronized String format(LogRecord record) {
                    return String.format("[%s] %s: %s%n",
                            new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                                    .format(new java.util.Date(record.getMillis())),
                            record.getLevel(),
                            record.getMessage());
                }
            });
            logger.addHandler(handler);
            logger.setUseParentHandlers(false);
        } catch (Exception e) {
            System.out.println("⚠️ Logging initialization failed: " + e.getMessage());
        }
    }

    public static void info(String message) {
        logger.info(message);
    }

    public static void warning(String message) {
        logger.warning(message);
    }

    public static void severe(String message) {
        logger.severe(message);
    }
}
