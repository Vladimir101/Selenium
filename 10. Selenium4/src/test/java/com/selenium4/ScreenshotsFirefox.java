package com.selenium4;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import io.github.bonigarcia.wdm.WebDriverManager;

class ScreenshotsFirefox
{
	private WebDriver driver;
	private String URL = "https://www.selenium.dev";

	@BeforeAll
	static void setUpBeforeClass()
	{
		WebDriverManager.firefoxdriver().setup();
	}

	@BeforeEach
	void setUp()
	{
		driver = new FirefoxDriver();		
		driver.get(URL);
		driver.manage().window().fullscreen(); //F11 in keyboard
// minimize the window to the bottom		
//		driver.manage().window().minimize(); 

	}

	@AfterEach
	void tearDown() throws Exception
	{
		driver.quit();
	}
	
	@Test
	void screenshotOfFullPage() throws IOException
	{
		File file = ((FirefoxDriver) driver).getFullPageScreenshotAs(OutputType.FILE);
		FileHandler.copy(file, new File("full_page.png"));
	}

}
