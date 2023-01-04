package com.opencart.testcases;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencart.base.BaseTest;
import com.opencart.constants.AppConstants;
import com.opencart.utilities.ExcelUtilities;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class RegistrationPageTest extends BaseTest {
	
	@BeforeClass
	public void regAcctSetUp() {
		registerPage = loginPage.navigateToRegisterPage();
	}
	
	@DataProvider
	public Object[][] registerAcctData() {
		Object[][] data = ExcelUtilities.getExcelData(AppConstants.SHEET_NAME);
		return data;
	}
	
	public String randomEmailGenerator() {
		Random eMail = new Random();
		String eMailId = "testautomation"+eMail.nextInt(20000)+"@yahoo.com";
		return eMailId;
	}
	
	@Description("New user Registration")
	@Severity(SeverityLevel.NORMAL)
	@Test(dataProvider = "registerAcctData")
	public void registerAcctTest(String fName, String lName, String telePhone, String pwd, String subscribe) {
		String actMsg = registerPage.doRegister(fName, lName, randomEmailGenerator(), telePhone, pwd, subscribe);
		Assert.assertEquals(actMsg, AppConstants.SUCC_MSG);
		
	}

}
