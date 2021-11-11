package com.selenium;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

class RemoteWebDriver4
{
	private WebDriver driver;
	
	@AfterEach
	void tearDown() throws Exception
	{
		driver.quit();
	}

// driver is taken from the System PATH	
// whatever driver is available	
	@Test
	void localTest()
	{
		driver = RemoteWebDriver.builder()
				.oneOf(new ChromeOptions(), new EdgeOptions())
				.build();
		driver.get("https://www.google.com/");
	}
	
// when standalone server is running 
// java -jar selenium-server-4.0.0.jar standalone	
	@Test
	void remoteTest() 
	{
		driver = RemoteWebDriver.builder()
				.oneOf(new ChromeOptions(), new EdgeOptions() )
				.address("http://localhost:4444")
				.build();
		driver.get("https://www.google.com/");
	}
	
	@Test
	void remoteTest2() throws MalformedURLException
	{
		FirefoxOptions browserOptions = new FirefoxOptions();
		browserOptions.setPlatformName("Windows 10");

		driver = new RemoteWebDriver(new URL("http://10.0.0.207:4444"), browserOptions);
		driver.get("https://www.google.com/");
	}
}
