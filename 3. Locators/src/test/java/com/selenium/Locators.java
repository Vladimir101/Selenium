package com.selenium;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Locators
{
	private WebDriver driver;
	
	@BeforeClass
	public static void oneTimeSetUp()
	{
		WebDriverManager.chromedriver().setup();
	}
	
	@Before
	public void setUp() throws Exception
	{
//		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.kmart.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void test() throws InterruptedException
	{
// by className - Kmart logo
		driver.findElement(By.className("ribbon-kmart-logo")).click();
	
// by CSS selector - Sign In, Accounts & Points
// to avoid stale element
		boolean staleElement = true;
		while(staleElement)
		{
			try
			{
				driver.findElement(By.cssSelector("#yourAccount")).click();
				staleElement = false;
			}
			catch(org.openqa.selenium.StaleElementReferenceException e)
			{
				if (e.getMessage().contains("element is not attached"))
					staleElement = true;
			}
		}
		driver.navigate().refresh();
		
// id
// after refresh to avoid stale element
		staleElement = true;
		while(staleElement)
		{	
			try 
			{ 
				driver.findElement(By.id("keyword")).sendKeys("milk"); 
				staleElement = false;
			}
			catch(org.openqa.selenium.StaleElementReferenceException e)
			{
				if (e.getMessage().contains("element is not attached"))
					staleElement = true;
			}
		}	

// by link text - Kmart logo
		driver.findElement(By.linkText("Kmart home")).click();		
		
// by name - search box
		driver.findElement(By.name("keyword")).sendKeys("coke");
		
// by partial link text	- search box
		driver.findElement(By.partialLinkText("home")).click();
		
// by tagName
		System.out.println(driver.findElement(By.tagName("body")).getText());
		
// by XPath - Shoes
		driver.findElement(By.xpath("//span[@class='gnf-skinny-3']")).click();		
	}
	
	@After
	public void tearDown() throws Exception
	{
		driver.quit();
	}
}
