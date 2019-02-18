package com.selenium.noPO;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.selenium.common.Base;

public class LoginLogout extends Base
{
	@Before
	public void setUp() 
	{
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://the-internet.herokuapp.com/login");
	}

	@After
	public void tearDown() throws Exception
	{
		driver.quit();
	}

	@Test
	public void test()
	{
// fill out the login form
		driver.findElement(By.id("username")).sendKeys("tomsmith");
		driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
		WebElement buttonLogin = driver.findElement(By.className("radius"));
		clickElement(buttonLogin);
		
// verify the logged in confirmation message
		WebElement confirmLogin = driver.findElement(By.id("flash"));
		assertTrue(confirmLogin.getText().contains("You logged into"));
		
// logout
		WebElement buttonLogout = driver.findElement(By.cssSelector(".button.secondary.radius"));
		clickElement(buttonLogout);
		
// verify the logged out confirmation message
		WebElement confirmLogout = driver.findElement(By.id("flash"));
		assertTrue(confirmLogout.getText().contains("You logged out"));
	}
}
