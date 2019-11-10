// https://deviceatlas.com/blog/samsung-phones-user-agent-strings-list
package com.selenium4;

import java.util.Optional;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.network.Network;

import io.github.bonigarcia.wdm.WebDriverManager;

class MobileWeb
{
	private WebDriver driver;
	private DevTools chromeDevTools;
	private String URL = "https://www.google.com/";
	private String userAgentSamsungGalaxyS8 = 
			"Mozilla/5.0 (Linux; Android 7.0; SM-G892A Build/NRD90M; wv)" +
			"AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/60.0.3112.107 Mobile Safari/537.36";
	
	@BeforeAll
	static void setUpBeforeClass()
	{
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	void setUp()
	{
		driver = new ChromeDriver();
		chromeDevTools = ((ChromeDriver) driver).getDevTools();
	    chromeDevTools.createSession();
		driver.manage().window().maximize();
	}

	@Test
	public void openMobileBrowser()
	{
		chromeDevTools.send(Network.setUserAgentOverride(userAgentSamsungGalaxyS8,
		        Optional.empty(), Optional.empty()));
		 
		driver.navigate().to(URL);
	}
}
