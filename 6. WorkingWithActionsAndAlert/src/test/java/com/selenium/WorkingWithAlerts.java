package com.selenium;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WorkingWithAlerts
{
	private WebDriver driver;
	// Advanced User Interactions API
	private Actions builder;

	@Before
	public void setUp()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://the-internet.herokuapp.com/javascript_alerts");
		builder = new Actions(driver);		
	}
	
//  JavaScript alerts DO NOT exist in the DOM	
	@Test
	public void jsAlert() throws InterruptedException
	{
		WebElement jsAlert = driver.findElement(By.xpath("//ul[1]/li[1]/button[1]"));
		jsAlert.click();
		
		Alert alert = driver.switchTo().alert();
// read the label of the alert		
		System.out.println(alert.getText());
		alert.accept();
// 		driver.switchTo().alert().accept();  without creating the alert object		
	}
	
	@Test
	public void jsConfirm()
	{
		WebElement jsConfirm = driver.findElement(By.xpath("//ul[1]/li[2]/button[1]"));
		jsConfirm.click();
		
		Alert alert = driver.switchTo().alert();
		// read the label of the alert		
		System.out.println(alert.getText()); 
		alert.dismiss(); // for Cancel button
	}
	
	@After
	public void tearDown()
	{
		driver.quit();
	}
}
