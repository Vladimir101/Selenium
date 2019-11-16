package com.selenium;

import java.io.*;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

class Base
{
	protected static WebDriver driver;

	@BeforeEach
	void setUp()
	{
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://www.kmart.com/");
	}

	protected void doScreenshot()
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		try
		{
			FileHandler.copy(ts.getScreenshotAs(OutputType.FILE), new File("VisibleArea.png"));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
