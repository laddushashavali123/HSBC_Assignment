package com.qa.steps;

import static com.qa.utility.CommonUtility.*;
import static com.qa.utility.Config.appURL;
import static com.qa.utility.ReportUtility.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import com.aventstack.extentreports.ExtentTest;
import cucumber.api.Scenario;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;

import com.aventstack.extentreports.ExtentReports;
import com.qa.driver.DriverManager;
import com.qa.utility.ReportUtility;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Hooks extends DriverManager {
    protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();

    public static WebDriver hdriver;
    Logger logger = Logger.getLogger(Hooks.class);
/*
    public ExtentTest extentTest=null;
*/
    @Before()
    public void setUpDriver(Scenario scenario) {
        test=createTest(getExtentReports(), "Scenario : "+scenario.getName());
        logger.info("Scenario is running: "+scenario.getName());
        BasicConfigurator.configure();
        //setDriver();
        hdriver = initializeDriver();
        hdriver.get(appURL);
        hdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       handleAcceptCookies(hdriver);
        logger.info(">>> @Before Hook Step ends");
    }

    @After()
    public void closeDriver(Scenario scenario) {
        logger.info(">>> @After Hook Step Starts");
        if (scenario.isFailed()) {
            logger.info("Scenario : "+scenario.getName()+" - failed");
            setLogStatus(test, "Fail", "Scenario is failed");
            hdriver.quit();
        }
        else
            hdriver.quit();
        logger.info(">>> @After Hook Step ends");
        logger.info(">>> @Sending mail ");
//        emailReport("shasha.testing@gmail.com", "TestResults", "Results of latest Run");
    }

    public static ThreadLocal<RemoteWebDriver> getDriver() {
        return driver;
    }

    public static void setDriver(ThreadLocal<RemoteWebDriver> driver) {
        Hooks.driver = driver;
    }
}
