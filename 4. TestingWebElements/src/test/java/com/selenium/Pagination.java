package com.selenium;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

class Pagination
{
	private WebDriver driver;

	@BeforeAll
	static void setUpBeforeClass()
	{
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	void setUp()
	{
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.amazon.com/gp/goldbox");
		driver.manage().window().maximize();
	}

	@Test
	void test()
	{
// Click on 'Computers & Accessories'		
		driver.findElement(By.xpath("//span[11]/div[1]/label[1]/input[1]")).click();
		
// Showing 1-xx of xxx results for	
// Example: Showing 1-40 of 181 results for		
// Use Ranorex Selocity		
		String results = driver.findElement(By.cssSelector("#FilterItemView_all_summary span:nth-child(2)"))
				.getText();
		
		String[] resultWords = results.split(" ");
// total results matching		
		int totalResults = Integer.parseInt(resultWords[3]);
		System.out.println("Total results found: " + totalResults);
// results displayed on one page		
		int resultsDisplayed = Integer.parseInt(resultWords[1].split("-")[1]);
		System.out.println("Maximum results displayed on one page: " + resultsDisplayed);
		
// total number of pages		
		int numberOfPages = (int)Math.ceil((double)totalResults/resultsDisplayed);
		System.out.println("Total number of pages: " + numberOfPages);

// calculate actual number of visible results on all pages		
		int actualTotalResults = 0;
		for (int i = 1; i <= numberOfPages; i++)
		{
			List<WebElement> visibleResults = 
					driver.findElements(By.className("gridColumn5"));
			
			int resultsOnPage = visibleResults.size();
			actualTotalResults += resultsOnPage;
			System.out.println("Page: " + i + " visible results: " + resultsOnPage);
			
			if (i == numberOfPages)
				break;
			driver.findElement(By.linkText("Next→")).click();
		}
		assertEquals(totalResults, actualTotalResults);
	}

	@AfterEach
	void tearDown()
	{
		driver.quit();
	}
}
