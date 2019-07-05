package com.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
// starts with Test*** for Surefire plugin convention
class TestSeleniumJUnit5WithMaven1
{
	private WebDriver driver;

	@BeforeEach
	void setUp()
	{
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Test
	void openSchoolWebsite()
	{
		System.out.println("Hi, School!");
		driver.get("https://www.portnov.com");
		System.out.println("Title of the page: " + driver.getTitle());
	}
	
	@AfterEach
	void tearDown()
	{
		driver.quit();
	}
}
