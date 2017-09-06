package com.ui.test.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;

public class UIMap {
		
	//------------------all the Locators in this section--------------------------------	
	
	public static By loginEditBoxName=By.name("username");
	public static By loginPagePasswrdEditBoxname=By.name("password");
	public static By loginPageSignInButtonName=By.xpath("//div[@class='login']/following::input[@class='button'or@type='submit']");
	public static By loginerrormessage= By.className("error");
	public static By logoutlink= By.xpath("//a[text()='Log Out']");

	


}
