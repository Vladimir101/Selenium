package com.selenium.performance;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import io.github.bonigarcia.wdm.WebDriverManager;

class PerformanceTest1
{
	private WebDriver driver;
	
	@BeforeAll
	static void oneTimeSetup()
	{
		WebDriverManager.chromedriver().setup();
// search in log file: COMMAND FindElement		
		System.setProperty("webdriver.chrome.logfile", "TestLog.txt");
	}
	
	@BeforeEach
	void setUp()
	{	
		driver = new ChromeDriver();		
		driver.get("https://the-internet.herokuapp.com/login");
	}
	
	@Test
	public void TestFirstAndLastNameFields()
	{

		var pageObject = PageFactory.initElements(driver, LoginPage.class);
		pageObject.username.sendKeys("Vladimir");  			   // a FindBy call is triggered to fetch username
		var value = pageObject.username.getAttribute("value"); // a FindBy call is triggered to fetch username
		System.out.println(value);
	}
	
	@AfterEach
	void tearDown()
	{
		driver.quit();
	}
}
