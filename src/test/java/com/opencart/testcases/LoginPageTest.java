package com.opencart.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.opencart.base.BaseTest;
import com.opencart.constants.AppConstants;

public class LoginPageTest extends BaseTest{
	
	@Test(priority = 1)
	public void validateTitleTest() {
		String actualTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE);
	}
	
	@Test(priority = 2)
	public void validateURLTest() {
		boolean flag = loginPage.getLoginPageUrl();
		Assert.assertTrue(flag);
	}
	
	@Test(priority = 3)
	public void isForgotPwdLinkTest() {
		boolean flag = loginPage.isForgotPwdLinkExist();
		Assert.assertTrue(flag);
	}
	
	@Test(priority = 4)
	public void doLoginTest() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		boolean flag = accountsPage.logOutBtnExist();
		Assert.assertTrue(flag);
	}
}
