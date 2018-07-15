package genericTestCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import pageObjects.LoginPage;

public class LoginTests {
	String title;
	WebDriver driver;
	LoginPage login =new LoginPage();
	String userdir=System.getProperty("user.dir");
	
	@BeforeSuite
	/*Initializing the driver*/
	public void initMethod() {
		
		driver=login.initDriver();
		login.config(userdir+"/src/main/resources/Config.Properties");
		driver.get("http://indiarailinfo.com");
	}
	
	@Test(priority=1)
	public void checkTitle() {
		String myTitle=driver.getTitle();
		if(myTitle.equalsIgnoreCase("Indiarailinfo.com")) {
			System.out.println("Correct Title found");
		}
		else{
			System.out.println("Incorrect title found");
		}
	}
	
	@Test (priority=0)
	public void CheckLogin() {
		login.Login("uid", "pwd");
	}
	
	@Test (priority=2)
	public void logout() {
		login.logout();
	}
	@AfterSuite
	
	public void closeBrowser() {
		
		driver.close();
	}
}
