package com.selenium;

import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Links
{
	private static WebDriver driver;

	@BeforeClass
	public static void oneTimeSetUp()
	{
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Before
	public void setUp()
	{
		driver.get("http://book.theautomatedtester.co.uk/");
		driver.manage().window().maximize();
	}

	@Test
	public void testingLinks()
	{
		List<WebElement> list = driver.findElements(By.tagName("a"));
// get the number of links
		int numberOfLinks = list.size();
		System.out.println("The number of links on the home page are " + numberOfLinks);
				
// print all links with names and urls
		int i = 1; 
		for (WebElement element: list)
		{
			System.out.println(i + ". " + element.getText() + ": " + element.getAttribute("href"));
			i++;
		}
	}
	
	@Test
	public void getHTMLElement()
	{
		String body = driver.getPageSource();
		System.out.println("Page HTML code:");
		System.out.println(body);
		assertTrue(body.contains("Selenium"));
	}
	
	@AfterClass
	public static void oneTimeTearDown()
	{
		driver.quit();
	}
}
