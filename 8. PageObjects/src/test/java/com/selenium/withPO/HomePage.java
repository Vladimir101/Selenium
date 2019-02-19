package com.selenium.withPO;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.selenium.common.Base;

public class HomePage extends Base
{
	private String title = "The Internet";
	
// elements
	@FindBy(id = "flash")
	private WebElement confirmLogin;
	
	@FindBy(css = ".button.secondary.radius")
	private WebElement buttonLogout;
	
// constructor
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		assertEquals("This is not the home page",
				title, driver.getTitle());
	}
	
// services
	public LoginPage logout()
	{
		clickElement(buttonLogout);
		return PageFactory.initElements(driver, LoginPage.class);
	}
	
	public String getLoginConfirmation()
	{
		return confirmLogin.getText();
	}
}
