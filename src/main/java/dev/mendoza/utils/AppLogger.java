package dev.mendoza.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AppLogger {
	// Create a public static final Logger Object
	// Single logger object to use throughout our entire program
	public static final Logger logger = LogManager.getLogger(AppLogger.class);
}