package tests.sync;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.BaseTest;

class ExplicitImplicit extends BaseTest
{
	@BeforeEach
	void setUp() throws Exception
	{
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
	}

	@Test
	void testExplicit()
	{
		driver.findElement(By.tagName("button")).click();
		
		WebElement helloWorld = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));
				
		assertEquals("Hello World!", helloWorld.getText());
	}
	
	@Test
	void testImplicit()
	{
		driver.findElement(By.tagName("button")).click();
		WebElement helloWorld = driver.findElement(By.id("finish"));
		assertEquals("Hello World!", helloWorld.getText());
	}
	
	@Test
	void elementNotOnPageWithoutWait()
	{
		List<WebElement> list = driver.findElements(By.id("VB"));
	}
	
	@Test
	void elementNotOnPageWithImplicitWait()
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElements(By.id("VB"));
	}
	
	@Test
	void elementNotOnPageWithExplicitWait()
	{
		new WebDriverWait(driver, Duration.ofSeconds(10))
			.until(ExpectedConditions.invisibilityOfElementLocated(By.id("VB")));	
	}
	
	
}
