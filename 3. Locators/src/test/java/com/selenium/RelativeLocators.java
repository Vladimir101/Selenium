package com.selenium;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.locators.RelativeLocator;

public class RelativeLocators
{
	private WebDriver driver;

	@BeforeEach
	public void setUp()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://automationbookstore.dev/");
		driver.manage().window().maximize();
	}

	@AfterEach
	public void close()
	{
		driver.quit();
	}

	@Test
	public void test_book5_is_left_of_book6_and_below_book1()
	{
// pid6 - Advanced Selenium in Java
// pid1 - Test Automation in the Real World	
// pid5 - Java For Testers		
		String id = driver
				.findElement(RelativeLocator.with(By.tagName("li"))
				.toLeftOf(By.id("pid6"))
				.below(By.id("pid1")))
				.getAttribute("id");
		assertEquals(id, "pid5");
	}

	@Test
	public void test_book2_is_above_book6_and_right_of_book1()
	{
// pid2 - Experiences of Test Automation		
		String id = driver
				.findElement(RelativeLocator.with(By.tagName("li"))
				.above(By.id("pid6"))
				.toRightOf(By.id("pid1")))
				.getAttribute("id");
		assertEquals(id, "pid2");
	}
}
