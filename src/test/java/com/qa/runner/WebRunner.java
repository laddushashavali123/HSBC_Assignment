package com.qa.runner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.aventstack.extentreports.ExtentReports;
import com.qa.utility.Config;
import com.qa.utility.ReportUtility;
import com.vimalselvam.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;



@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/java/com/qa/features" }, 
	glue = { "com.qa.steps" }, 
	plugin = { "pretty","html:reports/cucumber-html", 
			"json:reports/cucumber-json/jsonReport.json",
			"junit:reports/cucumber-xml/xmlReport.xml" },
	monochrome = true,
	strict = false,
	dryRun = false,
	tags = "@addItem")


public class WebRunner {
	static ExtentReports report;
	
	@BeforeClass
	public static void reportInit() {
		report = ReportUtility.initializeReport();
		ReportUtility.setExtentReports(report);
	}
	
	@AfterClass
	public static void reportFlush() {
		ReportUtility.flushReport(report);
	}
} 
