package com.selenium;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

public class Base implements TestWatcher
{
	protected static WebDriver driver;

	@BeforeEach
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	
	public void testAborted(ExtensionContext extensionContext, Throwable throwable)
	{
		// do something
	}

	public void testDisabled(ExtensionContext extensionContext, Optional<String> optional)
	{
		// do something
	}

	public void testFailed(ExtensionContext extensionContext, Throwable throwable)
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		try
		{
			FileHandler.copy(ts.getScreenshotAs(OutputType.FILE), new File("VisibleArea.png"));
		}
		catch (IOException e)
		{ // TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("'" + extensionContext.getDisplayName() + "' failed");
		driver.quit();
	}

	public void testSuccessful(ExtensionContext extensionContext)
	{
		System.out.println("'" + extensionContext.getDisplayName() + "' passed");
		driver.quit();
	}
}