package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.opencart.constants.AppConstants;
import com.opencart.utilities.ElementUtilities;

public class RegistrationPage {
	
	private WebDriver driver;
	private ElementUtilities elementUtil;
	
	private By fName = By.id("input-firstname");
	private By lName = By.id("input-lastname");
	private By eMail = By.id("input-email");
	private By telePhone = By.id("input-telephone");
	private By pwd = By.id("input-password");
	private By cPwd = By.id("input-confirm");
	private By subscYes = By.xpath("//div[@class='col-sm-10']/label[1]");
	private By subscNo = By.xpath("//div[@class='col-sm-10']/label[2]");
	private By privacyCheckBox = By.xpath("//input[@name='agree']");
	private By continueBtn = By.xpath("//input[@value='Continue']");
	private By succMesg = By.xpath("//h1");
	private By logOutLink = By.linkText("Logout");
	private By registrationLink = By.linkText("Register");
	
	public RegistrationPage(WebDriver driver) {
		this.driver=driver;
		elementUtil = new ElementUtilities(driver);
		
	}
	
	public String doRegister(String fName, String lName, String eMail, String telePhone, String pwd, String subscribe) {
		
		elementUtil.myWaitForPresenceOfElementWithSendKeys(this.fName, AppConstants.DEFAULT_TIME_OUT, fName);
		elementUtil.mySendKeys(this.lName, lName);
		elementUtil.mySendKeys(this.eMail, eMail);
		elementUtil.mySendKeys(this.telePhone, telePhone);
		elementUtil.mySendKeys(this.pwd, pwd);
		elementUtil.mySendKeys(this.cPwd, pwd);
		if(subscribe.equalsIgnoreCase("yes")) {
			elementUtil.myClick(subscYes);
		}
		else {
			elementUtil.myClick(subscNo);
		}
		elementUtil.myClick(this.privacyCheckBox);
		elementUtil.myClick(this.continueBtn);
		String succMessage = elementUtil.myWaitForPresenceOfElement(succMesg, AppConstants.DEFAULT_TIME_OUT).getText();
		elementUtil.myClick(logOutLink);
		elementUtil.myClick(registrationLink);
		return succMessage;
	
	}
	

}
