package practice;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;

class CookiesForAutomationpractice2 extends BaseTest
{
	@BeforeEach
	void open() throws Exception
	{
		driver.get("http://automationpractice.com/index.php?controller=authentication");
		driver.manage().window().maximize();
	}

	@Test
	void loginWithCookies1() throws InterruptedException
	{		
		for (Cookie cookie : cookies)
		  driver.manage().addCookie(cookie);
		 	
		driver.get("http://automationpractice.com/index.php?id_category=3&controller=category");	
		Thread.sleep(5000);
	}
	
	@Test
	void loginWithCookies2() throws InterruptedException
	{	
		for (Cookie cookie : cookies)
		  driver.manage().addCookie(cookie);
		 	
		driver.get("http://automationpractice.com/index.php?id_category=3&controller=category");	
		Thread.sleep(5000);
	}
}
