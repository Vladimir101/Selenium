package com.selenium4;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

class NewTab
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
	public void openNewTab()
	{
		driver.switchTo().newWindow(WindowType.TAB);
		driver.navigate().to("https://www.google.com");
	}
}
