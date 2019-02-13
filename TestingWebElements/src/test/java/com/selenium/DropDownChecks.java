package com.selenium;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class DropDownChecks
{
	private WebDriver driver;

	@Before
	public void setUp() 
	{
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");
		driver.manage().window().maximize();
	}

	@Test
	public void testingDropDown() throws InterruptedException
	{
		WebElement dropDown = driver.findElement(By.name("dropdown"));
		Select s = new Select(dropDown);
		
		s.selectByVisibleText("Drop Down Item 6");
		s.selectByIndex(1);
		String optionSelected = s.getFirstSelectedOption().getText();
		System.out.println("Currently selected menu item: " + optionSelected);
		
// read all options from drop-down
// 1.
		List<WebElement> list = driver.findElements(By.name("dropdown"));
		for (WebElement element: list)
			System.out.println(element.getText());
		
// 2.
		List<WebElement> options = s.getOptions();
		System.out.println("Number of menu items in drop-down is " + options.size());
		
		Thread.sleep(3000);
	}
	
	@Test
	public void testingMultiselect() throws InterruptedException
	{
		WebElement multiSelect = driver.findElement(By.tagName("select"));
		Select s = new Select(multiSelect);
		s.deselectAll();
		s.selectByIndex(0);
		s.selectByIndex(1);
		
		Thread.sleep(3000);
	}
	
	@After
	public void tearDown()
	{
		driver.quit();
	}
}
