package com.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.opencart.utilities.ElementUtilities;

public class SearchResultsPage {
	
	private WebDriver driver;
	private ElementUtilities elementUtil;
	
	private By searchResult = By.cssSelector("div.product-layout");
	
	public SearchResultsPage(WebDriver driver) {
		this.driver=driver;
		elementUtil = new ElementUtilities(driver);
	}
	
	public boolean isSearchSuccessful() {
		List<WebElement> productsSize = elementUtil.fElements(searchResult);
		if(productsSize.size()>=1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public ProductInfoPage selectProduct(String productName) {
		By prName = By.linkText(productName);
		elementUtil.myClick(prName);
		return new ProductInfoPage(driver);
	}

}


