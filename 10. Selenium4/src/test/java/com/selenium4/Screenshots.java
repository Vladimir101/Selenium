package com.selenium4;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

import io.github.bonigarcia.wdm.WebDriverManager;

class Screenshots
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
	public void screenshotOfWebElement1() throws IOException
	{
		WebElement logo = driver.findElement(By.id("selenium_webdriver"));
		File file = logo.getScreenshotAs(OutputType.FILE);
		FileHandler.copy(file, new File("webdriver_logo.png"));
	}
	
	@Test
	public void screenshotOfWebElement2() throws IOException
	{
		WebElement logo = driver.findElement(By.xpath("//div[6]/a[1]/img[1]"));
		TakesScreenshot ts = (TakesScreenshot)logo;
		File file = ts.getScreenshotAs(OutputType.FILE);
		FileHandler.copy(file, new File("lambdatest_logo.png"));
	}
	
	@Test
	public void getScreenshotOfVisibleArea() throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		FileHandler.copy(ts.getScreenshotAs(OutputType.FILE), 
				new File("visible_area.png"));
	}
}
