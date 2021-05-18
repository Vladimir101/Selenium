package practice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BaseTest
{
	protected WebDriver driver;
	protected static Set<Cookie> cookies;
	
	@BeforeAll
	static void oneTimeSetUp()
	{
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	void setUp() throws Exception
	{
		driver = new ChromeDriver();
		driver.get("http://automationpractice.com/index.php?controller=authentication");
		driver.manage().window().maximize();
	}

	@AfterEach
	void tearDown() throws Exception
	{
		driver.quit();
	}

	@Order(1)
	@Test
	void getCookies()
	{
		driver.findElement(By.id("email")).sendKeys("realsqa@gmail.com");
		driver.findElement(By.id("passwd")).sendKeys("abc123");
		driver.findElement(By.id("SubmitLogin")).click();
		
		cookies = driver.manage().getCookies();
	}
}
