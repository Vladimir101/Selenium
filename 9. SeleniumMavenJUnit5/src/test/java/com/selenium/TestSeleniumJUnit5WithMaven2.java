package com.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class TestSeleniumJUnit5WithMaven2
{
	private WebDriver driver;

	@BeforeEach
	void setUp()
	{
		String name = System.getProperty("OS");
		System.out.println("OS name is " + name);
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Test
	void openSeleniumWebsite()
	{
		System.out.println("Hi, Selenium!");
		driver.get("https://www.seleniumhq.org/");
		System.out.println("Title of the page: " + driver.getTitle());
	}
	
	@AfterEach
	void tearDown()
	{
		driver.quit();
	}
}
