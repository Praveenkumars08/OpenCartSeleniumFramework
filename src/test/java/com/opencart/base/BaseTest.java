package com.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.opencart.factory.DriverFactory;
import com.opencart.pages.AccountsPage;
import com.opencart.pages.LoginPage;
import com.opencart.pages.ProductInfoPage;
import com.opencart.pages.RegistrationPage;
import com.opencart.pages.SearchResultsPage;

public class BaseTest {
	
	public DriverFactory df;
	public Properties prop;
	public WebDriver driver;
	public LoginPage loginPage;
	public AccountsPage accountsPage;
	public SearchResultsPage searchResults;
	public ProductInfoPage productInfo;
	public RegistrationPage registerPage;
	
	
	@BeforeTest
	public void setUp() {
		df = new DriverFactory();
		prop = df.initializeProperties();
		driver = df.initializeDriver(prop);
		loginPage = new LoginPage(driver);
	}
	
	
	@AfterTest
	public void tearDown() {
		//driver.quit();
	}

}
