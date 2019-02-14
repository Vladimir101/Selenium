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
		String firstWindow = driver.getWindowHandle();
		String secondWindow = "";
		
		driver.findElement(By.linkText("Click Here")).click();

// find the second window handle		
		Set<String> allWindows = driver.getWindowHandles();
		for (String window: allWindows)
			if (!window.equals(firstWindow))
				secondWindow = window;
		
// switch to the first window
		driver.switchTo().window(firstWindow);
		assertThat(driver.getTitle(), is(equalTo("The Internet")));
		
// switch to the second windows
		driver.switchTo().window(secondWindow);
		assertThat(driver.getTitle(), is(equalTo("New Window")));
	}
	
	@After
	public void tearDown() throws IOException
	{
		driver.quit();
	}
}
