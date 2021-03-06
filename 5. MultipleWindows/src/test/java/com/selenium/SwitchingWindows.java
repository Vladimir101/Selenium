package com.selenium;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class SwitchingWindows
{
	private WebDriver driver;
	private String firstTabTitle = "The Internet";
	private String titleToSwitch = "New Window";

	@BeforeEach
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://the-internet.herokuapp.com/windows");
		driver.manage().window().maximize();
	}

	@Test
	public void switchingWindows()
	{
		String firstTab = driver.getWindowHandle();
		String secondTab = "";
		driver.findElement(By.linkText("Click Here")).click();

// switch to the second tab without knowing second tab handle		
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows)
		{
			driver.switchTo().window(window);
			if (titleToSwitch.equals(driver.getTitle()))
				break;
		}
		
// find the second tab handle		
		for (String window : allWindows)
			if (!window.equals(firstTab))
				secondTab = window;
		
// switch to the first tab using the first tab handle
		driver.switchTo().window(firstTab);
		assertThat(driver.getTitle(), is(equalTo(firstTabTitle)));
// close the first tab		
		driver.close();
		
// switch to the second tab using the second tab handle
		driver.switchTo().window(secondTab);
		assertThat(driver.getTitle(), is(equalTo(titleToSwitch)));
	}

	@AfterEach
	public void tearDown()
	{
		driver.quit();
	}
}
