package practice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Cookie;

public class ReadCookie
{
	private WebDriver driver;
	
	@BeforeAll
	static void oneTimeSetUp()
	{
		WebDriverManager.chromedriver().setup();
	}
	
	@BeforeEach
	void setUp() throws Exception
	{
		driver = new ChromeDriver();
		driver.get("http://www.memotome.com/login.asp");
// 		driver.get("http://demo.guru99.com/test/cookie/selenium_aut.php");			
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	void recordCookie() throws FileNotFoundException
	{
// login		
		driver.findElement(By.name("LoginName")).sendKeys("testselenium");
		driver.findElement(By.name("Password")).sendKeys("test@123");
		driver.findElement(By.xpath("//input[@value='Log Me In']")).click();		
/*
 * driver.findElement(By.name("username")).sendKeys("abc123");
 * driver.findElement(By.name("password")).sendKeys("123xyz");
 * driver.findElement(By.name("submit")).click();
 */
		Set<Cookie> cookies = driver.manage().getCookies();
		for(Cookie cookie: cookies)
			System.out.println(cookie.toString());
		
		File cookieFile = new File("LoginCookies.dat");
// delete old file if exists
		cookieFile.delete();

		var writeToFile = new PrintWriter(cookieFile);
		for (Cookie cookie : cookies)
			writeToFile.println((cookie.getName() + ";" 
								+ cookie.getValue() + ";" 
								+ cookie.getDomain() + ";" 
								+ cookie.getPath() + ";"
								+ cookie.getExpiry() + ";" 
								+ cookie.isSecure()));
		writeToFile.close();
	}
	
	@AfterEach
	void tearDown()
	{
		driver.quit();
	}
	
}