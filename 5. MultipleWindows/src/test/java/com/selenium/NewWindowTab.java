package com.selenium4;

import java.util.Set;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

class NewWindowTab
{
	private WebDriver driver;
	private String URL = "https://www.seleniumhq.org/";
	private String initialWindowTitle = "Selenium";
	private String tabTitle = "JUnit 5";
	private String secondWindowTitle = "Maven – Welcome to Apache Maven";

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
		//		driver.quit();
	}

	@Test
	public void openNewTab()
	{
// open new tab		
		driver.switchTo().newWindow(WindowType.TAB);
		driver.navigate().to("https://junit.org/junit5/");
		
// open new browser window		
		driver.switchTo().newWindow(WindowType.WINDOW);
		driver.get("https://maven.apache.org/");
		switchTo(tabTitle);
		switchTo(secondWindowTitle);
		switchTo(initialWindowTitle);
	}

	private void switchTo(String windowTitle)
	{
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows)
		{
			driver.switchTo().window(window);
			if (driver.getTitle().equals(windowTitle))
			{
				System.out.println(windowTitle);
				break;
			}
		}
	}
}
