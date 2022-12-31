package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.opencart.constants.AppConstants;
import com.opencart.utilities.ElementUtilities;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtilities elementUtil;
	
	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;
		elementUtil = new ElementUtilities(driver);
	}
	
	private By addToCartBtn = By.id("button-cart");
	
	public boolean addToCartBtnCheck() {
		WebElement ele = elementUtil.myWaitForPresenceOfElement(addToCartBtn, AppConstants.DEFAULT_TIME_OUT);
		return ele.isDisplayed();
	}
	
	

}
