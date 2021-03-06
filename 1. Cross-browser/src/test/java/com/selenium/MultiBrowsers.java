package com.selenium;

import java.io.*;
import java.util.Properties;

import org.apache.commons.lang3.SystemUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;
class MultiBrowsers
{
	private WebDriver driver;
	private static String browser = "headless";

	@BeforeAll
	static void oneTimeSetUp() throws IOException
	{
// 1. specify parameters via System properties
		browser = System.getProperty("browser");  // -Dbrowser=chrome
// 2. specify browser in the .config file
		var prop = new Properties();
		prop.load(new FileInputStream("browser.config"));
		browser = prop.getProperty("browser");	
	}
	
	@BeforeEach
	void setUp() 
	{
		switch(browser)
		{
		case "chrome" ->
			{if (SystemUtils.IS_OS_WINDOWS)
				System.setProperty("webdriver.chrome.driver", "c:/drivers/chromedriver.exe");
			else
				System.setProperty("webdriver.chrome.driver", "c:/mac_drivers/chromedriver.exe");
			driver = new ChromeDriver();}
		case "firefox" ->
			{System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");		
// hiding logs
// create c:/logs directory first			
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"C:/logs/logs.txt");		
			driver = new FirefoxDriver();}
		case "edge" ->
			{System.setProperty("webdriver.edge.driver", "drivers/MicrosoftWebDriver.exe");
			driver = new EdgeDriver();}
		case "headless" ->
// Headless HtmlUnit browser written in Java			
// 1. no browser launched (run in background)
// 2. tests are fast (performance is improved)
// 3. not suitable for Actions class (mouse movement, double click, etc.)			
			driver = new HtmlUnitDriver(/* BrowserVersion.CHROME */); // emulates Chrome
		}
	}

	@Test
	void test()
	{
		driver.get("https://www.seleniumhq.org/");
		System.out.println("Title of the page: " + driver.getTitle());
	}
	
	@AfterEach
	void tearDown()
	{
		driver.quit();
	}
}
