package com.selenium.withPO;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.selenium.common.Base;

public class TestPO
{
	private WebDriver driver;
	
	@Before
	public void setUp() throws Exception
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
	public void testValidLogin()
	{
		HomePage homePage = LoginPage.open(driver)
				 					 .validLogin("tomsmith", "SuperSecretPassword!");
		assertTrue(homePage.getLoginConfirmation().contains("You logged into"));
		
		LoginPage loginPage = homePage.logout();
		assertTrue(loginPage.getLogoutConfirmation().contains("You logged out"));
	}

// create test for invalid login	
}
