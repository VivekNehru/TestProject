package genericTestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class LoginTests {
	String title;
	WebDriver driver;
	@BeforeSuite
	
	public void initDriver() {
		
		System.setProperty("webdriver.chrome.driver", "H:\\Java\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("http://indiarailinfo.com");
		driver.manage().window().maximize();
	}
	
	@Test
	public void checkTitle() {
		String myTitle=driver.getTitle();
		if(myTitle.equalsIgnoreCase("Indiarailinfo.com")) {
			System.out.println("Correct Title found");
		}
		else{
			System.out.println("Incorrect title found");
		}
	}
	
	@AfterSuite
	
	public void closeBrowser() {
		
		driver.close();
	}
}
