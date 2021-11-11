package com.selenium4;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.NetworkInterceptor;
import org.openqa.selenium.remote.http.*;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NetworkIntercept
{
	private WebDriver driver;
	private int i = 0;
	@BeforeAll
	static void setUpBeforeClass()
	{
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	void setUp()
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@AfterEach
	void tearDown() throws Exception
	{
		driver.quit();
	}

	@Test
	void getStatusCode()
	{
		Filter filter = next -> 
		{
			   return req -> 
			   {
			     HttpResponse res = next.execute(req);
			     System.out.printf("Request: %s -> Response: %s %n", req, res.getStatus());
			     i++;
			     return res;
			   };
		};
		NetworkInterceptor interceptor = new NetworkInterceptor(driver, filter);
		driver.get("http://selenium.dev");
		System.out.println("The number of requests is " + i);
	}
}
