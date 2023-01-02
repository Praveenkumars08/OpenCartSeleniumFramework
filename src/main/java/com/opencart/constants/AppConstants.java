package com.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstants {
	
	public static final String LOGIN_PAGE_URL_PARM = "awesomeqa";
	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String ACCT_PAGE_URL_PARM = "route=account";
	public static final String ACCT_PAGE_TITLE = "My Account";
	
	public static final List<String> ACCT_PAGE_HEADERS = Arrays.asList("My Account", "My Orders",
			"My Affiliate Account", "Newsletter");
	
	public static final int DEFAULT_TIME_OUT = 5;
	public static final int DEFAULT_LARGE_TIME_OUT = 10;
	public static final String SUCC_MSG = "Your Account Has Been Created!";
	public static final String SHEET_NAME = "register";

}
