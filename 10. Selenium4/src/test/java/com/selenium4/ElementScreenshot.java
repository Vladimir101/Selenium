package com.selenium4;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

import io.github.bonigarcia.wdm.WebDriverManager;

class ElementScreenshot
{
	private WebDriver driver;
	private String URL = "https://www.seleniumhq.org/";

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
		driver.get(URL);
	}

	@AfterEach
	void tearDown() throws Exception
	{
		driver.quit();
	}

	@Test
	public void screenshotOfWebElement() throws IOException
	{
		WebElement logo = driver.findElement(By.id("selenium_webdriver"));
		File file = logo.getScreenshotAs(OutputType.FILE);
		FileHandler.copy(file, new File("webdriver_logo.png"));
	}
}
