package com.opencart.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.opencart.base.BaseTest;
import com.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class LoginPageTest extends BaseTest{
	
	@Description("Login Page Title Test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void validateTitleTest() {
		String actualTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE);
	}
	
	@Description("Login Page URL Test")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 2)
	public void validateURLTest() {
		boolean flag = loginPage.getLoginPageUrl();
		Assert.assertTrue(flag);
	}
	
	@Description("Login Page Forgot Password Link Exist or not check Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void isForgotPwdLinkTest() {
		boolean flag = loginPage.isForgotPwdLinkExist();
		Assert.assertTrue(flag);
	}
	
	@Description("Check if the user can able to login or not test")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 4)
	public void doLoginTest() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		boolean flag = accountsPage.logOutBtnExist();
		Assert.assertTrue(flag);
	}
}
