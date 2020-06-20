package com.selenium.performance;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import io.github.bonigarcia.wdm.WebDriverManager;

class PerformanceTest2
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
		pageObject.username.sendKeys("Vladimir");  // a FindBy call is triggered to fetch username
		 
// measure timing for a WebElement, which is not cached
		 long withoutCacheStartTime = System.currentTimeMillis();
		 for(int i = 0; i < 1000; i ++)
		 {
			 pageObject.username.getAttribute("value");
		 }
		 long withoutCacheEndTime = System.currentTimeMillis();
		 System.out.println("1000 calls for findElement without cache (secs): " 
				 + ((withoutCacheEndTime - withoutCacheStartTime)/ 1000));
		 
// measure timing for a WebElement, which is cached		 
		 long withCacheStartTime = System.currentTimeMillis();
		 for(int i = 0; i < 1000; i ++)
		 {
			 pageObject.usernameCached.getAttribute("value");
		 }
		 long withCacheEndTime = System.currentTimeMillis();
		 System.out.println("1000 calls for findElement with cache (secs): " 
				 + ((withCacheEndTime - withCacheStartTime)/ 1000));
	}
	
	@AfterEach
	void tearDown()
	{
		driver.quit();
	}
}
