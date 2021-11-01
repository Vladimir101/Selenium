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
// new tab		
		driver.switchTo().newWindow(WindowType.TAB);
		driver.navigate().to("https://junit.org/junit5/");

// new browser window		
		driver.switchTo().newWindow(WindowType.WINDOW);
		driver.get("https://maven.apache.org/");
	}
}
