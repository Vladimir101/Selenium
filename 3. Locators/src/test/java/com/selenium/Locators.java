package com.selenium;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Locators
{
	private WebDriver driver;
	
	@BeforeAll
	public static void oneTimeSetUp()
	{
		WebDriverManager.chromedriver().setup();
	}
	
	@BeforeEach
	public void setUp() throws Exception
	{
		driver = new ChromeDriver();
		driver.get("http://opencart.abstracta.us/");
		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void test() throws InterruptedException
	{
// by className - > arrow to stop carousel
		driver.findElement(By.className("swiper-button-next")).click();
	
// by CSS selector - magnifying glass button (Dev tools)
		driver.findElement(By.cssSelector(".btn.btn-default.btn-lg")).click();

// id
		driver.findElement(By.id("input-search")).sendKeys("MacBook");
		driver.findElement(By.id("button-search")).click();

// by link text - logo
		driver.findElement(By.linkText("Your Store")).click();		
		
// by name - search box
		driver.findElement(By.name("search")).sendKeys("Selenium");
		
// by partial link text	- Phones & PDAs
		driver.findElement(By.partialLinkText("PDAs")).click();
		
// by tagName
		System.out.println(driver.findElement(By.tagName("body")).getText());
		
// by XPath - HTC Touch HD add to cart (ChroPath)
		driver.findElement(By.xpath("//div[1]/div[1]/div[2]/div[2]/button[1]/span[1]")).click();		
	}
	
	@AfterEach
	public void tearDown() throws Exception
	{
		driver.quit();
	}
}
