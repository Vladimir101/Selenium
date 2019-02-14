package com.selenium;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class WorkingWithActions
{
	private WebDriver driver;
// Advanced User Interactions API
	private Actions builder;
	
	@Before
	public void setUp() 
	{
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		builder = new Actions(driver);
	}

	@Test
	public void moveMouseWithAlert() throws InterruptedException
	{
		driver.get("http://book.theautomatedtester.co.uk/chapter4");
		WebElement mouseOver = driver.findElement(By.id("hoverOver"));
		
// Move the mouse to the middle of the element
		builder.moveToElement(mouseOver).perform();
		
		Thread.sleep(2000);
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	
	@Test
	public void moveMouseWithOffset()
	{
		driver.get("http://www.webminal.org/");
		WebElement button = driver.findElement(By.linkText("Register"));

// X axis goes to the right
// Y axis goes down		
		builder.moveByOffset(button.getLocation().getX() + 5, 
							 button.getLocation().getY())
				.click()
				.perform();
		assertEquals("Join", driver.findElement(By.xpath("//h2[1]")).getText());
	}
	
	@After
	public void tearDown()
	{
		driver.quit();
	}
}
