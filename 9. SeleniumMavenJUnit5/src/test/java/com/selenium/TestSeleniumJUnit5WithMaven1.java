package com.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
// starts with Test*** for Surefire plugin convention
class TestSeleniumJUnit5WithMaven1
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
		driver = new ChromeDriver();
	}

	@Test
	void openSchoolWebsite()
	{
		System.out.println("Hi, School!");
		driver.get("https://pasv.us/en/");
		String text = driver.findElement(By.cssSelector(".t480__title.t-title.t-title_xs.t-margin_auto"))
				.getText();
		assertEquals("We teach programming\nand QA", text);
	}
	
	@AfterEach
	void tearDown()
	{
		driver.quit();
	}
}
