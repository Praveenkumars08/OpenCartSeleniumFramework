package com.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	
	public static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
	
	/**
	 * This method is used to initialize the driver.
	 */
	public WebDriver initializeDriver(Properties prop) {
		String browserName = prop.getProperty("browser").toLowerCase();
		if(browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			threadLocalDriver.set(new ChromeDriver());
		}
		else if(browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			threadLocalDriver.set(new FirefoxDriver());
		}
		else if(browserName.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			threadLocalDriver.set(new EdgeDriver());
		}
		else {
			System.out.println("Please pass the valid browser");
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));

		return getDriver();
	}
	
	public static synchronized WebDriver getDriver() {
		return threadLocalDriver.get();
	}

	public Properties initializeProperties() {
		prop = new Properties();
		try {
			FileInputStream file = new FileInputStream("./src/test/resources/config/config.properties");
			try {
				prop.load(file);
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return prop;
	}

}
