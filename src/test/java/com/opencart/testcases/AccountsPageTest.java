package com.opencart.testcases;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencart.base.BaseTest;
import com.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest{
	
	@BeforeClass
	public void acctSetUp() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority = 1)
	public void validateAcctPageTitleTest() {
		String actualTitle = accountsPage.getAcctPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.ACCT_PAGE_TITLE);
	}
	
	@Test(priority = 2)
	public void validateAcctPageUrlTest() {
		boolean flag = accountsPage.getAcctPageUrl();
		Assert.assertTrue(flag);
	}
	
	@Test(priority = 3)
	public void searchIconDisplayTest() {
		boolean flag = accountsPage.searchIconExist();
		Assert.assertTrue(flag);
	}
	
	@Test(priority = 4)
	public void logOutLinkExistTest() {
		boolean flag = accountsPage.logOutBtnExist();
		Assert.assertTrue(flag);
	}
	
	@Test(priority = 5)
	public void acctPageHeadersTest() {
		ArrayList<String> actualHeadersList = accountsPage.getAcctSecHeaderList();
		System.out.println(actualHeadersList);
		Assert.assertEquals(actualHeadersList, AppConstants.ACCT_PAGE_HEADERS);
	}
	
	@DataProvider
	public Object[][] searchDataProvider() {
		return new Object[][] {
			
			{"iMac"},
			{"MacBook"},
			{"iPhone"},
			{"Samsung"},
			};
		}
	
	@Test(priority = 6, dataProvider = "searchDataProvider")
	public void searchCheckTest(String productName) {
		searchResults = accountsPage.doSearch(productName);
		boolean flag = searchResults.isSearchSuccessful();
		Assert.assertTrue(flag);
	}
	
	@DataProvider
	public Object[][] productDataProvider() {
		return new Object[][] {
			{"iMac", "iMac"},
			{"MacBook", "MacBook"},
			{"Samsung", "Samsung Galaxy Tab 10.1"},
			{"iPhone", "iPhone"}
		};
	}
	
	@Test(priority = 7, dataProvider = "productDataProvider")
	public void productSearchTest(String prName, String prKey) {
		searchResults = accountsPage.doSearch(prName);
		productInfo = searchResults.selectProduct(prKey);
		boolean flag = productInfo.addToCartBtnCheck();
		Assert.assertTrue(flag);
	}
}
