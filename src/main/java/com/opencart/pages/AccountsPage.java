package com.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.opencart.constants.AppConstants;
import com.opencart.utilities.ElementUtilities;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtilities elementUtil;
	
	public AccountsPage(WebDriver driver) {
		this.driver=driver;
		elementUtil = new ElementUtilities(driver);
	}
	
	private By searchIcon = By.xpath("//input[@name='search']");
	private By searchBtn = By.xpath("//button[@class='btn btn-default btn-lg']");
	private By logOutLink = By.xpath("(//a[text()='Logout'])[2]");
	private By acctSecHeaders = By.xpath("//h2");
	
	
	public String getAcctPageTitle() {
		String title = elementUtil.waitForExactTitle(AppConstants.DEFAULT_TIME_OUT, AppConstants.ACCT_PAGE_TITLE);
		return title;
	}
	
	public boolean getAcctPageUrl() {
		String url = elementUtil.waitForURLContains(AppConstants.DEFAULT_TIME_OUT, AppConstants.ACCT_PAGE_URL_PARM);
		if(url.contains(AppConstants.ACCT_PAGE_URL_PARM)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean searchIconExist() {
		return elementUtil.checkEleEnabled(searchIcon);
	}
	
	public boolean logOutBtnExist() {
		return elementUtil.checkEleDisplayed(logOutLink);
	}
	
	public ArrayList<String> getAcctSecHeaderList() {
		return elementUtil.fElementsTextList(acctSecHeaders);
	}
	
	public SearchResultsPage doSearch(String productKey) {
		elementUtil.mySendKeys(searchIcon, productKey);
		elementUtil.myClick(searchBtn);
		return new SearchResultsPage(driver);
	}

}
