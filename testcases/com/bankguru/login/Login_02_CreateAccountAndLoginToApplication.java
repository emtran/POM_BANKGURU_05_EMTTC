package com.bankguru.login;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Login_02_CreateAccountAndLoginToApplication {
	WebDriver driver;
	String loginUrl;
	
	@Parameters ({"browser"})
	@BeforeClass
	public void beforeClass(String browser) {
		if(browser.equals("firefox")) {
			driver = new FirefoxDriver();
		}else if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
			driver = new ChromeDriver();
		}else if (browser.equals("ie11")) {
			System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		driver.get("http://live.guru99.com/index.php");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
  public void TC_01_CreateAnAccount() {
		driver.get("http://demo.guru99.com/v4/");
		loginUrl = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		
		userId = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		
	}
	
	@Test
	  public void TC_02_LoginWithAboveInformation() {
		driver.get(loginUrl);
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userId);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Manger Id : "+ userId +"']")).isDisplayed());
	}
  
 
	public int randomEmail() {
		Random random = new Random();
		int number = random.nextInt(999999);
		return number;
	}
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
