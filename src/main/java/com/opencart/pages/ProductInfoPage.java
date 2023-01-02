package com.opencart.pages;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.opencart.constants.AppConstants;
import com.opencart.utilities.ElementUtilities;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtilities elementUtil;
	private HashMap<String, String> productInfoMap;
	
	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;
		elementUtil = new ElementUtilities(driver);
	}
	
	private By addToCartBtn = By.id("button-cart");
	private By imgCount = By.xpath("//ul[@class='thumbnails']/li");
	private By productMetaData = By.xpath("(//div[@class='col-sm-4']/ul)[1]/li");
	
	public boolean addToCartBtnCheck() {
		WebElement ele = elementUtil.myWaitForPresenceOfElement(addToCartBtn, AppConstants.DEFAULT_TIME_OUT);
		return ele.isDisplayed();
	}
	
	public boolean getImagesCount() {
		List<WebElement> imageCount = elementUtil.myWaitForElements(imgCount, AppConstants.DEFAULT_TIME_OUT);
		int totalImages = imageCount.size();
		if(totalImages>=1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public HashMap<String, String> getProductMetaData() {
		List<WebElement> metaList = elementUtil.fElements(productMetaData);
		productInfoMap = new HashMap<>();
		for(WebElement e : metaList) {
			String text = e.getText();
			String metaText[] = text.split(":");
			String key = metaText[0];
			String val = metaText[1];
			productInfoMap.put(key, val);
		}
		return productInfoMap;
	}
	
	
	
	

}
