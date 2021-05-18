package practice;

import java.util.Set;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CookiesForAutomationpractice
{
	private WebDriver driver;
	private static Set<Cookie> cookies;
	
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
	
	@Test
	void loginWithCookies() throws InterruptedException
	{
// comment cookies to see the difference		
		for (Cookie cookie : cookies)
			driver.manage().addCookie(cookie);
		 
		driver.get("http://automationpractice.com/index.php?id_category=3&controller=category");	
		Thread.sleep(5000);
	}
}
