package com.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

//starts with Test*** for Surefire plugin convention
class TestSeleniumJUnit5WithMaven2
{
	private WebDriver driver;

	@BeforeAll
	static void oneTimeSetUp()
	{
		WebDriverManager.chromedriver().setup();
	}
	
	@BeforeEach
	void setUp()
	{
		String name = System.getProperty("os.name");
		System.out.println("OS name is " + name);
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
