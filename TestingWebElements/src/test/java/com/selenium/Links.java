package com.selenium;

import static org.junit.Assert.*;

import java.util.List;

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
	}

	@Before
	public void setUp()
	{
		driver.get("https://www.kmart.com/");
		driver.manage().window().maximize();
	}

	@Test
	public void testingLinks()
	{
		List<WebElement> list = driver.findElements(By.tagName("a"));
// get the number of links
		int numberOfLinks = list.size();
		System.out.println("The number of Kmart links are " + numberOfLinks);
		
// print first 10 link names
		for (int i = 1; i <= 10; i++)
			System.out.println(i + "." + list.get(i - 1).getText());
		
// print first 10 link urls
		int i = 1;
		for (WebElement e: list)
		{
			System.out.println(i + "." + e.getAttribute("href"));
			i++;
			if (i == 11)
				break;
		}
	}
	
	@Test
	public void getElementText()
	{
		WebElement logoLink = driver.findElement(By.className("ribbon-kmart-logo"));
		String logoLinkTest = logoLink.getText();
		System.out.println(logoLinkTest);
	}
	
	@Test
	public void getHTMLElement()
	{
		String body = driver.getPageSource();
		assertTrue(body.contains("Kmart"));
	}
	
	@AfterClass
	public static void oneTimeTearDown()
	{
		driver.quit();
	}
}
