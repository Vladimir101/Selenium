// Task: specify browser in the .config file
// See solution under the com.selenium.solution
package com.selenium;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

class MultiBrowsers
{
	private WebDriver driver;
	private String browser = "chrome"; 
	
	@BeforeEach
	public void setUp() 
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
		}
	}

	@Test
	public void test()
	{
		driver.get("https://www.amazon.com/");
	}
	
	@AfterEach
	public void tearDown() throws IOException
	{
		driver.quit();
		if (browser.equals("firefox")) // bug with Firefox
		{
			Runtime rt = Runtime.getRuntime();
			rt.exec("taskkill /im geckodriver.exe /f /t");
		}
	}
}
