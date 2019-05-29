// Task: specify browser in the .config file
// See solution under the com.selenium.solution
package com.selenium;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
	static void oneTimeSetUp()
	{
// specify parameters via System properties
		browser = System.getProperty("browser");
		System.out.println("Browser name is " + browser);
	}
	
	@BeforeEach
	void setUp() 
	{
		switch(browser)
		{
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case "edge":
			System.setProperty("webdriver.edge.driver", "drivers/MicrosoftWebDriver.exe");
			driver = new EdgeDriver();
			break;
		case "headless":
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
	void tearDown() throws IOException
	{
		driver.quit();
		if (browser.equals("firefox")) // bug with Firefox
		{
			Runtime rt = Runtime.getRuntime();
			rt.exec("taskkill /im geckodriver.exe /f /t");
		}
	}
}
