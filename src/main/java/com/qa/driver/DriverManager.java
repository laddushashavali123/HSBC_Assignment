package com.qa.driver;

import com.aventstack.extentreports.ExtentTest;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.ExtentReports;
import com.qa.utility.ReportUtility;

import static com.qa.utility.Config.*;
import static com.qa.utility.ReportUtility.createTest;
import static com.qa.utility.ReportUtility.getExtentReports;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DriverManager {

	static Logger logger = Logger.getLogger(DriverManager.class);
	public static ExtentTest test = null;
			//createTest(getExtentReports(), "Add a box to the cart && Boxes display");

	public static WebDriver driver;
	
	public static WebDriver initializeDriver() {
		if (browserType.equalsIgnoreCase("chrome")) {
			/** Using WebDriverManager to create WebDrive without driver binary */
			//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
			WebDriverManager.chromedriver().arch32().version(chromeDriverVersion).setup();
			ChromeOptions opt = new ChromeOptions();
			opt.addArguments("-incognito");
			opt.addArguments("--disable-popup-blocking");
			opt.addArguments("disable-extensions");
			opt.addArguments("--start-maximized");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			capabilities.setCapability(ChromeOptions.CAPABILITY, opt);
			driver = new ChromeDriver(capabilities);
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(1000, TimeUnit.SECONDS);
			//driver = new ChromeDriver();
			logger.info(">>> Chrome Driver initialized");
		} else if (browserType.equalsIgnoreCase("firefox")) {
			/** Using WebDriverManager to create WebDrive without driver binary */
			// DRIVER_PATH = PROJECT_PATH + Config.browserDriverPath + "geckodriver.exe";
			// System.setProperty("webdriver.gecko.driver", DRIVER_PATH);
			WebDriverManager.firefoxdriver().arch64().version(firefoxDriverVersion).setup();
			driver = new FirefoxDriver();
			logger.info(">>> Firefox Driver initialized");
		}
		return driver;
	}
}
