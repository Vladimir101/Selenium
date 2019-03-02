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
		driver.get("http://book.theautomatedtester.co.uk/");
		driver.manage().window().maximize();
	}

	@Test
	public void gettingStates()
	{
		driver.findElement(By.linkText("Chapter1")).click();
// verify that the checkbox is visible		
		WebElement checkbox = driver.findElement(By.name("selected(1234)"));
		assertTrue(checkbox.isDisplayed());
		
// verify that the checkbox is checked			
		checkbox.click();
		assertTrue(checkbox.isSelected());
	}
	
	@Test
	public void readingInputField()
	{
		WebElement textbox = driver.findElement(By.id("q"));
		textbox.sendKeys("New text");
		String text = textbox.getAttribute("value");
		assertEquals("New text", text);
	}
	
	@After
	public void tearDown()
	{
		driver.quit();
	}
}
