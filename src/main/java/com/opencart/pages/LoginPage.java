package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.opencart.constants.AppConstants;
import com.opencart.utilities.ElementUtilities;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtilities elementUtil;
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		elementUtil = new ElementUtilities(driver);
	}
	
	private By eMail = By.id("input-email");
	private By pwd = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.xpath("//a[text()='Forgotten Password']");
	private By registerLink = By.linkText("Register");
	
	public String getLoginPageTitle() {
		String title = elementUtil.waitForExactTitle(AppConstants.DEFAULT_TIME_OUT, AppConstants.LOGIN_PAGE_TITLE);
		System.out.println("Login Page Title is :"+title);
		return title;
	}
	
	public boolean getLoginPageUrl() {
		String url = elementUtil.waitForURLContains(AppConstants.DEFAULT_TIME_OUT, AppConstants.LOGIN_PAGE_URL_PARM);
		if(url.contains(AppConstants.LOGIN_PAGE_URL_PARM)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isForgotPwdLinkExist() {
		return elementUtil.checkEleDisplayed(forgotPwdLink);
	}
	
	public AccountsPage doLogin(String userName, String password) {
		elementUtil.myWaitForPresenceOfElementWithSendKeys(eMail, AppConstants.DEFAULT_TIME_OUT, userName);
		elementUtil.mySendKeys(pwd, password);
		elementUtil.myClick(loginBtn);
		return new AccountsPage(driver);
	}
	
	public RegistrationPage navigateToRegisterPage() {
		elementUtil.myClick(registerLink);
		return new RegistrationPage(driver);
	}

}
