package com.selenium;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ElementStates
{
	private WebDriver driver;

	@Before
	public void setUp() 
	{
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@Test
	public void gettingStates()
	{
		driver.get("http://book.theautomatedtester.co.uk/chapter1");
// verify that the checkbox is visible		
		WebElement checkBox = driver.findElement(By.name("selected(1234)"));
		assertTrue(checkBox.isDisplayed());
		
// verify that the checkbox is checked			
		checkBox.click();
		assertTrue(checkBox.isSelected());
	}
	
	@Test
	public void readingInputField()
	{
		driver.get("https://www.kmart.com/");
		WebElement searchBox = driver.findElement(By.name("keyword"));
		searchBox.sendKeys("shoes");
		System.out.println(searchBox.getAttribute("value"));
	}
	
	@After
	public void tearDown()
	{
		driver.quit();
	}
}
