package com.selenium;
// run hub
// java -jar selenium-server-standalone-3.141.59.jar –role hub <–port 1111>
//
// register node
//java -jar selenium-server-standalone-3.141.59.jar -role node -hub http://localhost:4444/grid/register
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Grid
{
	private WebDriver driver;

	public void openGoogle(Capabilities capabilities) throws MalformedURLException
	{
		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		driver.get("https://www.google.com");
	}

	@Test
	public void testInChrome() throws MalformedURLException
	{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setBrowserName("chrome");
		capabilities.setPlatform(Platform.WINDOWS);
		openGoogle(capabilities);
	}

	@Test
	public void testInFirefox() throws MalformedURLException
	{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setBrowserName("firefox");
		openGoogle(capabilities);
	}

	@After
	public void tearDown()
	{
		driver.quit();
	}
}
