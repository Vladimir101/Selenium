package com.selenium;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ElementExistsAndScrolling
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
		String titleToSwitch = "Home";
		driver.findElement(By.linkText("Help")).click();		
		driver.findElement(By.cssSelector(".bottom-half-margin img")).click();
		
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows)
		{
			driver.switchTo().window(window);
			if (titleToSwitch.equals(driver.getTitle()))
				break;
		}	

// verify that there is only one text box for questions 
		assertEquals(driver.findElements(By.xpath("//input")).size(), 1);
		
// check that element with id = myElement does not exist
		List<WebElement> list = driver.findElements(By.id("myElement"));
		assertTrue(list.size() == 0);	
	}
	
	@Test
	public void scrollingVertically() throws InterruptedException
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
/*
 * window.scroll(x-coord, y-coord)
 * Parameters:
 * x-coord is the number of pixels along the horizontal axis of the document you want to scroll
 * y-coord is the number of pixels along the vertical axis of the document you want to scroll 
*/
		jse.executeScript("window.scroll(0, 5000)");
		Thread.sleep(5000);
	}
	
	@After
	public void tearDown()
	{
		driver.quit();
	}
}
