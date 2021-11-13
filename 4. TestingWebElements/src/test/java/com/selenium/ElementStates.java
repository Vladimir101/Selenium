package tests.states;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import common.BaseTest;

class ElementStates extends BaseTest
{
	@BeforeEach
	void open() throws Exception
	{
		driver.get("https://formy-project.herokuapp.com/form");
	}

	@Test
	void getStates()
	{
// verify that the radiobutton is visible		
		WebElement radio = driver.findElement(By.id("radio-button-3"));
		assertTrue(radio.isDisplayed());

// verify that the radiobutton is checked		
		radio.click();
		assertTrue(radio.isSelected());
	}
	
	@Test
	void readInputField()
	{
		WebElement first = driver.findElement(By.id("first-name"));
		first.sendKeys("Vlad");
		String name = first.getAttribute("value");
		assertEquals("Vlad", name);
	}
}
