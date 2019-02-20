package com.qa.utility;

import static com.qa.utility.Config.reportPath;
import static com.qa.utility.Config.reportsDocumentName;
import static com.qa.utility.Config.reportsUsername;
import static com.qa.utility.Config.screenshotPath;
import static com.qa.utility.Config.takeScreenshotForFailStep;
import static com.qa.utility.Config.takeScreenshotForPassStep;
import static com.qa.utility.Config.testEnvironment;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.driver.DriverManager;

public class ReportUtility {

	static Logger logger = Logger.getLogger(ReportUtility.class);

	private static ExtentHtmlReporter extentHtmlReporter;
	private static ExtentReports extentReports;
	private static ExtentTest extentTest;
	static long currentTime;

	public static ExtentReports initializeReport() {

		try {
			extentHtmlReporter = new ExtentHtmlReporter(reportPath + "TestAutomationReport.html");
			extentReports = new ExtentReports();
			extentReports.attachReporter(extentHtmlReporter);
			extentReports.setSystemInfo("OS", System.getProperty("osname"));
			extentReports.setSystemInfo("Host Name", InetAddress.getLocalHost().getHostName());
			extentReports.setSystemInfo("Environment", testEnvironment);
			extentReports.setSystemInfo("User Name", reportsUsername);
			extentHtmlReporter.config().setTheme(Theme.DARK);
			extentHtmlReporter.config().setDocumentTitle(reportsDocumentName);
			extentHtmlReporter.config().setReportName(Config.reportsName);
			extentHtmlReporter.config().setChartVisibilityOnOpen(true);
			extentHtmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		return extentReports;
	}

	public static ExtentTest createTest(ExtentReports report, String testCaseName) {
		extentTest = report.createTest(testCaseName);
		return extentTest;
	}

	public static ExtentReports getExtentReports() {
		return extentReports;
	}

	public static void setExtentReports(ExtentReports report) {
		extentReports = report;
	}

	public static void setLogStatus(ExtentTest test, String status, String message) {
		String path = "";
		try {
			if (status.equalsIgnoreCase("Info"))
				test.log(Status.INFO, message);
			else if (status.equalsIgnoreCase("Warning"))
				test.log(Status.WARNING, message);
			else if (status.equalsIgnoreCase("Pass")) {
				if (!takeScreenshotForPassStep) {
					test.log(Status.PASS, message);
				} else {
					path = takeScreenshot(DriverManager.driver, message);
					test.log(Status.PASS, message);//, MediaEntityBuilder.createScreenCaptureFromPath(path).build());
					test.log(Status.PASS,"<a href='" + path + "'>ScreenshotPass</a>");
					//test.addScreenCaptureFromPath(path);
				}
			} else if (status.equalsIgnoreCase("Fail")) {
				if (takeScreenshotForFailStep) {
					path = takeScreenshot(DriverManager.driver, message);
					test.log(Status.FAIL, message);//,MediaEntityBuilder.createScreenCaptureFromPath(path).build());
					test.log(Status.FAIL,"<a href='" + path + "'>ScreenshotFail</a>");
					//test.log(Status.FAIL, MarkupHelper.createLabel("", ExtentColor.RED));
				} else {
					test.log(Status.FAIL, MarkupHelper.createLabel(message, ExtentColor.RED));
				}
			} else if (status.equalsIgnoreCase("Error"))
				test.log(Status.ERROR, message);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void flushReport(ExtentReports extentReports) {
		extentReports.flush();
	}

	protected static String takeScreenshot(WebDriver driver, String step) {
		String sdf = new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
		sdf=sdf.replaceAll("[^a-zA-Z0-9]", "");
		currentTime = System.currentTimeMillis();
		String destination = "";
		try {
			File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			destination = screenshotPath + "Screenshot_" + sdf + "_" + currentTime + ".png";
			File finalDestination = new File(destination);
			FileUtils.copyFile(source, finalDestination, false);
			logger.info(destination);
			logger.info("ScreenShot taken for step " + step);
		} catch (Throwable t) {
			logger.error("Error occured " + t.getLocalizedMessage());
		}
		return destination;
	}
	public static void emailReport(String sendTo, String subject, String message){
		Email email = new SimpleEmail();
		try {
			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("shasha.intell@gmail.com", "shasha@432"));
			email.setSSLOnConnect(true);
			email.setFrom("shasha.intell@gmail.com");
			email.setSubject(subject);
			email.setMsg(message);
			email.addTo(sendTo);
			email.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
