package com.selenium;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ElementExists
{
	private WebDriver driver;

	@Before
	public void setUp() 
	{
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.kmart.com/");
		driver.manage().window().maximize();
	}

	@Test
	public void verifyElementExistance()
	{
		driver.findElement(By.linkText("Help")).click();
		
// verify that there is only one text box for questions 
		assertEquals(driver.findElements(By.id("question")).size(), 1);
		
// check that element with id = myElement does not exist
		List<WebElement> list = driver.findElements(By.id("myElement"));
		assertTrue(list.size() == 0);	
	}
	
	@After
	public void tearDown()
	{
		driver.quit();
	}
}
