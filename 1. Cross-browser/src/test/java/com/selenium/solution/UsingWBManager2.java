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

import io.github.bonigarcia.wdm.WebDriverManager;

class UsingWBManager2
{
	private WebDriver driver;
	private static String browser;
	
	@BeforeAll
	public static void oneTimeSetUp() throws IOException
	{
		Properties prop = new Properties();
		prop.load(new FileInputStream("browser.config"));
		browser = prop.getProperty("browser");
	}
	
	@BeforeEach
	public void setUp()
	{
		switch(browser)
		{
		case "chrome" -> 
			{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			}
		case "firefox" ->
			{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			}
		case "edge" ->
			{
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();			
			}
		}
		driver.manage().window().maximize();
	}

	@Test
	void test1()
	{
		driver.get("https://www.google.com/");
	}

	@Test
	void test2()
	{
		driver.get("https://www.vanguard.com/");
	}
	
	@Test
	void test3()
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
