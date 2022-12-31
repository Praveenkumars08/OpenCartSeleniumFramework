package com.opencart.utilities;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtilities {

	private WebDriver driver;
	private Select sel;
	private Actions act;

	public ElementUtilities(WebDriver driver) {
		this.driver=driver;
		act = new Actions(driver);
	}

	public WebElement fElement(By locator) {
		return driver.findElement(locator);
	}

	public void mySendKeys(By locator, String value) {
		WebElement ele = fElement(locator);
		ele.clear();
		ele.sendKeys(value);
	}

	public void myClick(By locator) {
		fElement(locator).click();
	}

	public String myGetText(By locator) {
		String txt = fElement(locator).getText();
		return txt;
	}

	public String myGetAttribute(By locator, String attrname) {
		String atrValue = fElement(locator).getAttribute(attrname);
		return atrValue;
	}

	public boolean checkEleDisplayed(By locator) {
		return fElement(locator).isDisplayed();
	}

	public boolean checkEleEnabled(By locator) {
		return fElement(locator).isEnabled();
	}

	public List<WebElement> fElements(By locator) {
		return driver.findElements(locator);
	}

	public int fElementsCount(By locator) {
		return fElements(locator).size();
	}

	public ArrayList<String> fElementsTextList(By locator) {
		List<WebElement> eleList = fElements(locator);
		ArrayList<String> ar = new ArrayList<>();
		for(WebElement e : eleList) {
			String txt = e.getText();
			if(txt.length()!=0) {
				ar.add(txt);
			}
		}
		return ar;
	}

	/**
	 * **********Utilities for Select Class*********
	 */

	public void mySelectDropDownByIndex(By locator, int value) {
		sel = new Select(fElement(locator));
		sel.selectByIndex(value);
	}

	public void mySelectDropDownByVisibleText(By locator, String text) {
		sel = new Select(fElement(locator));
		sel.selectByVisibleText(text);
	}

	public void mySelectDropDownByValue(By locator, String value) {
		sel = new Select(fElement(locator));
		sel.selectByValue(value);
	}

	public int fDropDownCount(By locator) {
		return getDropDownOptions(locator).size();
	}

	public List<WebElement> getDropDownOptions(By locator) {
		sel = new Select(fElement(locator));
		return sel.getOptions();
	}

	public ArrayList<String> getAllDropDownValues(By locator) {
		sel = new Select(fElement(locator));
		List<WebElement> eleList =  sel.getOptions();
		ArrayList<String> ar = new ArrayList<>();
		for(WebElement e : eleList) {
			String txt = e.getText();
			ar.add(txt);
		}
		return ar;
	}

	public void handleDropDownWithoutSelect(By locator, String val) {
		List<WebElement> eleList = fElements(locator);
		for(WebElement e : eleList) {
			String txt = e.getText();
			if(txt.equals(val)) {
				e.click();
				break;
			}
		}
	}

	/**
	 * ********Utilities for Actions Class***************
	 */

	public void mySendKeysWithActions(By locator, String value) {
		act.sendKeys(fElement(locator), value).build().perform();
	}

	public void mySendKeysWithClick(By locator) {
		act.click(fElement(locator)).click().build().perform();
	}

	public void myRightClick(By locator) {
		act.contextClick(fElement(locator));
	}

	public void myDoubleClick(By locator) {
		act.doubleClick(fElement(locator));
	}

	public void myDragAndDrop(By sLocator, By tLocator) {
		act.dragAndDrop(fElement(sLocator), fElement(tLocator));
	}


	/**
	 * ****************Utilities for Wait *********************
	 */

	public WebElement myWaitForPresenceOfElement(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public void myWaitForPresenceOfElementWithSendKeys(By locator, int timeOut, String val) {
		myWaitForPresenceOfElement(locator, timeOut).sendKeys(val);
	}

	public void myClickUsingWait(By locator, int timeOut) {
		myWaitForPresenceOfElement(locator, timeOut).click();
	}

	public WebElement myWaitForVisibleOfElement(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void myWaitForVisibleOfElementSendKeys(By locator, int timeOut, String val) {
		myWaitForVisibleOfElement(locator, timeOut).sendKeys(val);
	}

	public void myWaitForVisibleOfElementWithClick(By locator, int timeOut) {
		myWaitForVisibleOfElement(locator, timeOut).click();
	}

	/**
	 * Wait using Alert
	 */

	public Alert waitForAlert(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public String WaitForAlertGetText(int timeOut) {
		return waitForAlert(timeOut).getText();
	}

	/**
	 * Wait for Title and URL
	 */

	public String waitForExactTitle(int timeOut, String plsPassExactTitle) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if(wait.until(ExpectedConditions.titleIs(plsPassExactTitle))) {
			return driver.getTitle();
		}
		else {
			return null;
		}
	}

	public String waitForPartialTitle(int timeOut, String partialTitleVal) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if(wait.until(ExpectedConditions.titleContains(partialTitleVal))) {
			return driver.getTitle();
		}
		else {
			return null;
		}
	}

	public String waitForURL(int timeOut, String plsPassExactURL) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if(wait.until(ExpectedConditions.urlToBe(plsPassExactURL))) {
			return driver.getCurrentUrl();
		}
		else {
			return null;
		}
	}

	public String waitForURLContains(int timeOut, String plsPassPartialVal) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if(wait.until(ExpectedConditions.urlContains(plsPassPartialVal))) {
			return driver.getCurrentUrl();
		}
		else {
			return null;
		}
	}

	public void waitForFrame(int timeOut, String idOrName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idOrName));
	}

	public void waitForFrame(int timeOut, WebElement frameElement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}

	public void waitForFrame(int timeOut, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}

	public void waitForFrame(int timeOut, int index) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
	}

	public List<WebElement> myWaitForElements(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

}
