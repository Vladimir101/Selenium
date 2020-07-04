package practice;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.*;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BypassLoginWithCookie
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
	void recordCookies() throws FileNotFoundException, ParseException
	{
		File cookieFile = new File("LoginCookies.dat");
		var readFromFile = new Scanner(cookieFile);
		
		while (readFromFile.hasNextLine())
		{
			String dataLine = readFromFile.nextLine();
			String[] cookieData = dataLine.split(";");
			String name = cookieData[0];
			String value = cookieData[1];
			String domain = cookieData[2];
			String path = cookieData[3];
			String expiry = cookieData[4];
			String secure = cookieData[5];
			Date expires = null;
			
// regardless of the cookie expiration date, add one year to it			
			if (!expiry.equals("null"))
			{
				SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
				expires = new Date();
				formatter.format(expires);
				Calendar cal = Calendar.getInstance();
				cal.setTime(expires);
		        cal.add(Calendar.YEAR, 1);
		        expires = cal.getTime();
			}
			boolean isSecure = Boolean.parseBoolean(secure);

			Cookie cookie = new Cookie(name, value, domain, path, expires, isSecure);
			System.out.println(cookie);
			driver.manage().addCookie(cookie);
		}
		readFromFile.close();
		driver.get("http://www.memotome.com/home.asp");
// 		driver.get("http://demo.guru99.com/test/cookie/selenium_cookie.php");	
	}

	@AfterEach
	void tearDown()
	{
		driver.quit();
	}
}