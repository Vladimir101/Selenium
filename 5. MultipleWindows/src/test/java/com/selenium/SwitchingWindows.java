package com.selenium;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SwitchingWindows
{
	private WebDriver driver;

	@Before
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
		
// find the second tab handle		
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows)
			if (!window.equals(firstTab))
				secondTab = window;
		
// switch to the first tab
		driver.switchTo().window(firstTab);
		assertThat(driver.getTitle(), is(equalTo("The Internet")));
// close the first tab		
		driver.close();
		
// switch to the new window (tab)
		driver.switchTo().window(secondTab);
		assertThat(driver.getTitle(), is(equalTo("New Window")));
	}

	@After
	public void tearDown() throws IOException
	{
		driver.quit();
	}
}
