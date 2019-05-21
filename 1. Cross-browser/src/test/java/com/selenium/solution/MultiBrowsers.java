package com.selenium.solution;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

class MultiBrowsers
{
	private WebDriver driver;
	private static String browser;
	
	@BeforeAll
	static void oneTimeSetUp() throws IOException
	{
		Properties prop = new Properties();
		prop.load(new FileInputStream("browser.config"));
		browser = prop.getProperty("browser");
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
		case "edge":
			System.setProperty("webdriver.edge.driver", "drivers/MicrosoftWebDriver.exe");
			driver = new EdgeDriver();
		}
	}

	@Test
	void test()
	{
		driver.get("https://www.amazon.com/");
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
