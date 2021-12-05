package com.hrm.employee;

import org.testng.annotations.Test;

import commons.BaseTest;
import environmentConfig.Environment;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;

@Epic("Regression test")
@Feature("Register and Login")
public class Level_23_Multi_Enviroment extends BaseTest {
	WebDriver driver;
	Environment env;

	@Parameters("browser")
	@BeforeClass
	public void beforeTest(String browserName) {
		String environment = System.getProperty("ENV");
		System.out.println("Environment from command line: " + environment);
		log.info("Environment from command line: " + environment);
		
		ConfigFactory.setProperty("env", environment);
		
		env = ConfigFactory.create(Environment.class);
		driver = multiBrowser(browserName, env.getUrlApp());
	}

	@Test
	public void Employee_01_Add_New_Employee() {}

	@AfterClass(alwaysRun = true)
	public void afterTest() {
		log.info("Pre-condition: close browser and driver");
		closeBrowserAndDriver();
	}
}
