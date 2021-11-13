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
	
	@Test
	public void hoverOver()
	{
		driver.get("https://the-internet.herokuapp.com/hovers");
// name: user1		
		WebElement name = driver.findElement(By.xpath("//div[1]/div[1]/h5[1]"));
		System.out.println(name.isDisplayed()); // false
		
		WebElement image1 = driver.findElement(By.xpath("//div[1]/div[1]/img[1]"));
// hover over		
		builder.moveToElement(image1).perform();
		System.out.println(name.isDisplayed()); // true
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
