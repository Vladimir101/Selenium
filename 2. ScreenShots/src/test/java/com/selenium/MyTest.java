package com.selenium;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MyTestWatcher.class)
class MyTest extends Base
{	
	@BeforeEach
	public void setUp() throws Exception
	{
		driverSetUp();
		driver.get("https://www.kmart.com/");
	}
	
	@DisplayName("not important test")
	@Test
	void failingTest()
	{
		fail();
	}
	
	@DisplayName("very important test")
	@Test
	void passingTest()
	{
		assertTrue(true);
	}
}
