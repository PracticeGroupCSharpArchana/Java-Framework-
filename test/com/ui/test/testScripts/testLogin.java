package com.ui.test.testScripts;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ui.test.driverManager.BrowserFactory;
import com.ui.test.utilities.KeywordUtility;

public class testLogin {
	
	KeywordUtility util;
	Properties data;
		
	@BeforeTest
	public void setUp() throws IOException{
		util=new KeywordUtility(this.getClass().getSimpleName());
		data=util.testDataProp;
	}
	
	@Test(description="To Test login functionality with Valid Username and Password")
	public void testValidLogin(){
		util.loginToApplication(data.getProperty("userName"), data.getProperty("password"));
	}
	
	@AfterTest
	public void tearDown(){
		util.closeCurrentBrowser();
		util.closeAllOpenBrowser();
	}
	
}
