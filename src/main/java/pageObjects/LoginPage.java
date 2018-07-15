package pageObjects;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage {
	WebDriver driver;
	Properties Cprop;
	Actions action;
	WebDriverWait wait;
	
	public WebDriver initDriver() {
	System.setProperty("webdriver.chrome.driver","H:\\Java\\chromedriver.exe");	
	driver=new ChromeDriver();
	action=new Actions(driver);
	wait=new WebDriverWait(driver,20);
	driver.manage().window().maximize();
	return driver;
	}
	
		public void config(String path) {
			try {
				File file1= new File(path);
				FileInputStream fis = new FileInputStream(file1);
				Cprop=new Properties();
				Cprop.load(fis);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void gotoPage(String url) {
		driver.get(Cprop.getProperty(url));
	}
	
	public void Login(String uname,String pwd) {
		WebElement Uname=driver.findElement(By.id("LoginEMail"));
		WebElement pswd=driver.findElement(By.id("LoginPwd"));
		WebElement login= driver.findElement(By.xpath("//input[@type='submit' and @tabindex='405']"));
		Uname.sendKeys(Cprop.getProperty(uname));
		pswd.sendKeys(Cprop.getProperty(pwd));
		login.click();
		
		/*WebElement member=driver.findElement(By.xpath("//a[@class='inactive' and text()='Member #1335551']"));*/
		WebElement member=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='inactive' and text()='Member #1335551']")));
		if (member.getText().equals("Member #1335551")) {
		System.out.println("Login Successful");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		else {
			System.out.println("Login Failed");
		}
	}
	
	public void logout() {
		WebElement member=driver.findElement(By.xpath("//a[@class='inactive' and text()='Member #1335551']"));
		WebElement logout=driver.findElement(By.xpath("//td[contains(text(),'Member')]"));
		action.moveToElement(member).moveToElement(logout).click().build().perform();
		
		
		WebElement login=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='inactive' and contains(text(),'Login')]")));
		if (login.getText().equals("Login")) {
			System.out.println("Logout Successful");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			System.out.println("Logout failed");
		}
	}
}
