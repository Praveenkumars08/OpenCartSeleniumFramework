package com.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.log4testng.Logger;

import com.opencart.errors.ApplicationErrors;
import com.opencart.exeptions.FrameworkException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public OptionsManager op;

	public static final Logger LOG = Logger.getLogger(DriverFactory.class);
	public static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

	/**
	 * This method is used to initialize the driver.
	 */
	public WebDriver initializeDriver(Properties prop) {
		String browserName = prop.getProperty("browser").toLowerCase();
		LOG.info("Browser name is :"+browserName);
		op = new OptionsManager(prop);
		if(browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			threadLocalDriver.set(new ChromeDriver(op.getChromeOptions()));
		}
		else if(browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			threadLocalDriver.set(new FirefoxDriver(op.getFireFoxOptions()));
		}
		else if(browserName.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			threadLocalDriver.set(new EdgeDriver(op.getEdgeOptions()));
		}
		else {
			System.out.println("Please pass the valid browser");
			LOG.error("Please pass the right browser"+browserName);
			throw new FrameworkException(ApplicationErrors.BROWSER_NOT_FOUND);
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
		FileInputStream ip = null;

		// mvn clean install -Denv="dev"
		// mvn clean install
		String envName = System.getProperty("env");
		System.out.println("-----> Running test cases on environment: ----->" + envName);
		LOG.info("Running test cases on environment"+envName);

		if (envName == null) {
			System.out.println("No env is given..hence running it on QA env.....");
			try {
				ip = new FileInputStream("./src/test/resources/config/config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		else {
			try {
				switch (envName) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				default:
					System.out.println("please pass the right env name...." + envName);
					throw new FrameworkException(ApplicationErrors.ENVIRONMENT_NOT_FOUND);
					//break;
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

	public static String takeScreenShot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);


		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);

		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}
