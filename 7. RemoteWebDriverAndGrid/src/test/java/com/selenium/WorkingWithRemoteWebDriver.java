package com.selenium;
// start Selenium Standalone Server:
// java -jar selenium-server-standalone-3.141.59.jar

import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WorkingWithRemoteWebDriver
{
	private RemoteWebDriver driver;
	
	@Before
	public void setUp() throws MalformedURLException 
	{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setBrowserName("chrome");
		driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), capabilities);
		driver.manage().window().maximize();
	}

	@Test
	public void test()
	{
		driver.get("http://book.theautomatedtester.co.uk/");
		assertTrue(driver.getTitle().contains("Selenium"));
	}
	
	@After
	public void tearDown()
	{
		driver.quit();
	}
}
