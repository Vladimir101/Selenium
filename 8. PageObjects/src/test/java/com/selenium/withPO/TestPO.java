package com.selenium.withPO;

import static org.junit.Assert.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestPO
{
	private WebDriver driver;
	
	@BeforeEach
	public void setUp() throws Exception
	{
//		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@AfterEach
	public void tearDown() throws Exception
	{
		driver.quit();
	}

	@Test
	public void validLogin()
	{
		HomePage homePage = LoginPage.open(driver)
				.validLogin("tomsmith", "SuperSecretPassword!");
		assertTrue(homePage.getLoginConfirmation().contains("You logged into"));
		
		LoginPage loginPage = homePage.logout();
		assertTrue(loginPage.getConfirmation().contains("You logged out"));
	}
	 
	@ParameterizedTest
	@CsvFileSource(resources = "ddt.csv", numLinesToSkip = 1)
	void invalidLogin(String username, String password, String errorMessage) 
	{
		LoginPage loginPage = LoginPage.open(driver);
		loginPage.submitLogin(username, password);
		assertTrue(loginPage.getConfirmation().contains(errorMessage));
	}
}
