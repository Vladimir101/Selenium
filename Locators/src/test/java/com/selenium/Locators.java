package com.selenium;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Locators
{
	private WebDriver driver;
	
	@Before
	public void setUp() throws Exception
	{
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.kmart.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void test()
	{
		// by id
		driver.findElement(By.id("keyword")).sendKeys("milk");
		
		driver.navigate().refresh();
		// by name
		driver.findElement(By.name("keyword")).sendKeys("coke"); 
		
		// by link text
		driver.findElement(By.linkText("Kmart home")).click();
		
		// by partial link text
		driver.findElement(By.partialLinkText("home")).click();
		
		// by CSS selector
		driver.findElement(By.cssSelector("#yourAccount")).click();
		
		driver.navigate().refresh();
		// by XPath
		driver.findElement(By.xpath("//a[@class='gnf_tree_junction'][contains(text(),'Shoes')]")).click();
		
		driver.navigate().refresh();
		// by className
		driver.findElement(By.className("ribbon-kmart-logo")).click();
		
		// by tagName
		System.out.println(
				driver.findElement(By.tagName("body")).getText());	
	}

	@After
	public void tearDown()
	{
		driver.quit();
	}
}

	
