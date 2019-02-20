package com.qa.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {

	public static final String currentDirectory = System.getProperty("user.dir");
	public static final String fp = File.separator;
	public static final String configFilePath = currentDirectory + "/src/test/resources/config/";

	public static final String appURL = getProperty("appURL");
	public static final String browserType = getProperty("browserType");
	public static final String browserDriverPath = getProperty("browserDriverPath");
	public static final String chromeDriverVersion = getProperty("chromeDriverVersion");
	public static final String firefoxDriverVersion = getProperty("firefoxDriverVersion");
	
	public static final String reportPath = currentDirectory + getProperty("reportPath");
	public static final String screenshotPath = currentDirectory + getProperty("screenshotPath");
	public static final String testEnvironment = getProperty("testEnvironment");
	public static final String reportsUsername = getProperty("reportsUsername");
	public static final String reportsDocumentName = getProperty("reportsDocumentName");
	public static final String reportsName = getProperty("reportsName");
	
	public static final boolean takeScreenshotForPassStep = Boolean.valueOf(getProperty("takeScreenshotForPassStep"));
	public static final boolean takeScreenshotForFailStep = Boolean.valueOf(getProperty("takeScreenshotForFailStep"));

	public static String getProperty(String key) {
		Properties prop = new Properties();
		FileInputStream file;
		try {
			file = new FileInputStream(configFilePath + "config.properties");
			try {
				prop.load(file);
			} catch (IOException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return prop.getProperty(key);
	}

}
