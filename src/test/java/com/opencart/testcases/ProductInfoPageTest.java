package com.opencart.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest {
	
	@BeforeClass
	public void productInfoSetUp() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] dataProviderForImageCheck() {
		return new Object[][] {
			{"iMac", "iMac"},
			{"Samsung", "Samsung SyncMaster 941BW"},
			{"MacBook Air", "MacBook Air"},
			{"iPhone", "iPhone"},
		};
	}
	
	@Test(dataProvider = "dataProviderForImageCheck")
	public void productImagesCountTest(String productKey, String productName) {
		searchResults = accountsPage.doSearch(productKey);
		productInfo = searchResults.selectProduct(productName);
		boolean flag = productInfo.getImagesCount();
		Assert.assertTrue(flag);
	}

}
