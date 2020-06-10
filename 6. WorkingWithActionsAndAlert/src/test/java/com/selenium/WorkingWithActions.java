package com.selenium;

import static org.junit.Assert.assertEquals;
import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WorkingWithActions
{
	private WebDriver driver;
// Advanced User Interactions API
	private Actions builder;
	
	@Before
	public void setUp() 
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		builder = new Actions(driver);
	}

//  JavaScript alerts DO NOT exist in the DOM	
	@Test
	public void moveMouseWithAlert() throws InterruptedException
	{
		driver.get("http://book.theautomatedtester.co.uk/chapter4");
		WebElement mouseOver = driver.findElement(By.id("hoverOver"));
		
// Move the mouse to the middle of the element
		builder.moveToElement(mouseOver).perform();
		
		Alert alert = driver.switchTo().alert();
// read the label of the alert		
		System.out.println(alert.getText());
		alert.accept();
// 		alert.dismiss(); for Cancel button		
// 		driver.switchTo().alert().accept();  without creating the alert object		
	}
	
	@Test
	public void moveMouseWithOffset()
	{
		driver.get("http://www.webminal.org/");
		WebElement button = driver.findElement(By.linkText("Register"));

// X axis goes to the right
// Y axis goes down		
		builder.moveByOffset(button.getLocation().getX() + 6, 
							 button.getLocation().getY() + 6)
				.click()
				.perform();
		assertEquals("Join", driver.findElement(By.xpath("//h2[1]")).getText());
	}
	
	@Test
	public void dragAndDrop()
	{
		driver.get("https://demoqa.com/droppable/");
		WebElement source = driver.findElement(By.id("draggable"));
		WebElement target = driver.findElement(By.id("droppable"));
		builder.dragAndDrop(source, target).perform();
		
		assertEquals("Dropped!", target.getText());
	}
	
	@Test
	public void dragByOffset()
	{
		driver.get("https://demoqa.com/droppable/");
		WebElement source = driver.findElement(By.id("draggable"));
		WebElement target = driver.findElement(By.id("droppable"));
		
		int xSource = source.getLocation().getX();	 
		int ySource = source.getLocation().getY();
		
		int xTarget = target.getLocation().getX();	 
		int yTarget = target.getLocation().getY();
		
		int xMove = xTarget - xSource + 11;
		int yMove = yTarget - ySource + 11;

		builder.dragAndDropBy(source, xMove, yMove).perform();
		assertEquals("Dropped!", target.getText());		
	}
	
	@After
	public void tearDown()
	{
		driver.quit();
	}
}
