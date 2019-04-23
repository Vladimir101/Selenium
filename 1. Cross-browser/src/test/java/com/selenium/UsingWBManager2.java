// Task: 1. specify browser in the .config file
//		 2. use Java 12 'switch'
// See solution under the com.selenium.solution
package com.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

class UsingWBManager2
{
	private WebDriver driver;

	@Test
	void test1()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.google.com/");
	}

	@Test
	void test2()
	{
		WebDriverManager.chromedriver().version("73").setup();
		driver = new ChromeDriver();
		driver.get("https://www.amazon.com");
	}

	@Test
	void test3()
	{
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.get("https://www.vanguard.com/");
	}
	
	@Disabled
	@Test
	void test4()
	{
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		driver.get("https://www.vanguard.com/");
	}

	@AfterEach
	void tearDown()
	{
		driver.quit();
	}
}
