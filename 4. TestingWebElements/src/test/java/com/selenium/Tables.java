package com.selenium;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tables
{
	private WebDriver driver;

	@Before
	public void setUp() 
	{
		System.setProperty("webdriver.chrome.driver", "c:/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/tables");
		driver.manage().window().maximize();
	}

	@Test
	public void getColumn()
	{
// list of data rows in the Example 2 table		
		List<WebElement> rows = driver.findElements(By.xpath("//table[2]/tbody/tr"));
		int numberOfRows = rows.size();
		System.out.println("Number of data rows in the Example 2 table is " + numberOfRows);
		
// print data from column 4	
// 1.		
		System.out.println("First method");
		for (WebElement row: rows)
			System.out.println(row.getText().split(" ")[3]);
		
// 2.
		System.out.println("Second method");
		String cellXPath;
		for (int i = 1; i <= numberOfRows; i++)
		{
			cellXPath = "//table[2]/tbody/tr[" + i + "]/td[4]";
			System.out.println(driver.findElement(By.xpath(cellXPath)).getText());
		}	
		
// 3. 	
		System.out.println("Third method");
		var cellText = new ArrayList<String>(); // if I need compare column cells in assertEquals
		List<WebElement> column = driver.findElements(By.cssSelector("table#table2 tr>td:nth-child(4)"));
		for(WebElement cell: column)
		{
			String text = cell.getText();
			System.out.println(text);
			cellText.add(text);
		}
	}
	
	@After
	public void tearDown()
	{
		driver.quit();
	}
}
