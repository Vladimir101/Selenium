package com.selenium.common;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class Base
{
	protected WebDriver driver = null;
	
// using FluentWait
	protected void waitForElementToBeClickable(WebElement element)
	{
		new FluentWait<WebDriver>(driver)
			.withTimeout(Duration.ofSeconds(30))
			.pollingEvery(Duration.ofSeconds(2))
			.ignoring(NoSuchElementException.class)
			.ignoring(StaleElementReferenceException.class)
			.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	protected WebElement waitForElementToBeClickable(By locator)
	{
		return new FluentWait<WebDriver>(driver)
			.withTimeout(Duration.ofSeconds(30))
			.pollingEvery(Duration.ofSeconds(2))
			.ignoring(NoSuchElementException.class)
			.ignoring(StaleElementReferenceException.class)
			.until(ExpectedConditions.elementToBeClickable(locator));
	}

// stable operations	
	protected void clickElement(WebElement element)
	{
		waitForElementToBeClickable(element);
		element.click();
	}
	
	protected void clickElementWithFluentWait(By locator)
	{
		WebElement element = waitForElementToBeClickable(locator);
		element.click();
	}
	
	protected void clickElement(By locator)
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions
				            .elementToBeClickable(locator))
							.click();
	}
}
