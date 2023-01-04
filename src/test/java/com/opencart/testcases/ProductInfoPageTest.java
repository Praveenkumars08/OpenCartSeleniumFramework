package com.opencart.testcases;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

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
	
	@Description("Product info page, checks images count")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1, dataProvider = "dataProviderForImageCheck")
	public void productImagesCountTest(String productKey, String productName) {
		searchResults = accountsPage.doSearch(productKey);
		productInfo = searchResults.selectProduct(productName);
		boolean flag = productInfo.getImagesCount();
		Assert.assertTrue(flag);
	}
	
	@DataProvider
	public Object[][] dataProviderForMetaData(){
		return new Object[][] {
			{"MacBook", "MacBook Pro"}
		};
	}
	
	@Description("Product info page, checks the brand, code and availability")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 2, dataProvider = "dataProviderForMetaData")
	public void validateProductMetaDataTest(String productKey, String productName) {
		searchResults = accountsPage.doSearch(productKey);
		productInfo = searchResults.selectProduct(productName);
		HashMap<String, String> actualMetaData = productInfo.getProductMetaData();
		Assert.assertEquals(actualMetaData.get("Brand"), "Apple");
		Assert.assertEquals(actualMetaData.get("Product Code"), "Product 18");
		Assert.assertEquals(actualMetaData.get("Reward Points"), "800");
		Assert.assertEquals(actualMetaData.get("Availability"), "In Stock");
	}

}
