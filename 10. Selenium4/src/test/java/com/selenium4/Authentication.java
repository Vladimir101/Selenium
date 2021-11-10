package com.selenium4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

class Authentication
{
	private WebDriver driver;
	private String URL = "https://the-internet.herokuapp.com/digest_auth";

	@BeforeAll
	static void setUpBeforeClass()
	{
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	void setUp()
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();

	}
	
	@Test
	void loginWithAuthentication()
	{
		((HasAuthentication) driver).register(() -> new UsernameAndPassword("admin", "admin"));		
		driver.get(URL);
		
		String body = driver.findElement(By.tagName("body")).getText();
		System.out.println(body);		
	}

	@AfterEach
	void tearDown() throws Exception
	{
		driver.quit();
	}
}
