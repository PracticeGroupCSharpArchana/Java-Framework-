package com.ui.test.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;
import com.ui.test.driverManager.BrowserFactory;


public class KeywordUtility {
	
public Properties envProp;
public Properties testDataProp;
public WebDriver driver;


	
	public KeywordUtility(String browserName,String className) throws IOException{
		envProp=readPropertyFile(".//config/env.properties");
		testDataProp=readPropertyFile(".//resources/testdata/insuranceAgent/"+className+".properties");
		this.driver=BrowserFactory.getBrowser(browserName);
		openURL(envProp.getProperty("AUT_URL"));
	}
	
	public KeywordUtility(String className) throws IOException{
		envProp=readPropertyFile(".//config/env.properties");
		testDataProp=readPropertyFile(".//resources/testdata/insuranceAgent/"+className+".properties");
		this.driver=BrowserFactory.getBrowser(testDataProp.getProperty("browserName"));
		openURL(envProp.getProperty("AUT_URL"));
	}
	
 public Properties readPropertyFile(String filePath) throws IOException{
	 testNGReportLogger("Called Function : [readPropertyFile] with parameters [filePath]=>"+filePath);
	 Properties prop =new Properties();
	 prop.load(new FileInputStream(new File(filePath)));
	 return prop;
 }
 
 private void openURL(String URL){
	 testNGReportLogger("Called Function : [openURL] with parameters [URL]=>"+URL);
	 configureDriver();
	 driver.get(URL);
 }
 
 private void configureDriver(){
	 testNGReportLogger("Called Function : [configureDriver] with parameters [none]=>"+"None");
	 driver.manage().window().maximize();
	 driver.manage().timeouts().implicitlyWait(Integer.parseInt(envProp.getProperty("implicitWait")), TimeUnit.SECONDS);
	 driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(envProp.getProperty("pageLoadWait")), TimeUnit.SECONDS);
	 driver.manage().timeouts().setScriptTimeout(Integer.parseInt(envProp.getProperty("scriptTimeOut")), TimeUnit.SECONDS);
 }
 
 public WebElement findElement(By elementLocator){
	 testNGReportLogger("Called Function : [findElement] with parameters [elementLocator]=>"+elementLocator); 
	WebElement element=fluentWait(elementLocator);
	if(isElementVisible(elementLocator)){
		testNGReportLogger("Element has been Found");
		return element;
	}else{
		testNGReportLogger("Element found but not visible;Hence returning null to stop the execution");
		return null;
	}
 }
 
 public void testNGReportLogger(String message){
	 System.out.println(message);
	 org.testng.Reporter.log(message);
 }
 
 public Boolean isElementVisible(By locator) {
	 	testNGReportLogger("Called Function : [isElementVisible] with parameters [locator]=>"+locator); 
	    WebElement element = null;
	    WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(envProp.getProperty("explicitTimeOut")));
	    element = wait.until(ExpectedConditions.elementToBeClickable(locator));
	  if(element!=null){
		  return true;
	  }else{
		  return false;
	  }
}

	public WebElement fluentWait(final By locator){
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(500, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);
	 	   testNGReportLogger("Called Function : [fluentWait] with parameters [locator]=>"+locator); 
	       WebElement elementToBeFound = wait.until(
	        new Function<WebDriver, WebElement>() {
	            public WebElement apply(WebDriver driver) {
	                return driver.findElement(locator);
	            }
	        }
	    );
	    return elementToBeFound;
	}

	
	public void loginToApplication(String userName,String password){
		testNGReportLogger("Called Function : [loginToApplication] with parameters [userName],[password]=>"+userName+","+password); 
		findElement(UIMap.loginEditBoxName).sendKeys(userName);
	    findElement(UIMap.loginPagePasswrdEditBoxname).sendKeys(password);
		findElement(UIMap.loginPageSignInButtonName).click();
	
	}

	public void closeCurrentBrowser() {
		// TODO Auto-generated method stub
		testNGReportLogger("Called Function : [closeCurrentBrowser]"); 
		driver.close();
	}

	public void closeAllOpenBrowser() {
		// TODO Auto-generated method stub
		testNGReportLogger("Called Function : [closeAllOpenBrowser]");
		driver.quit();
	}
	
	 
	
	
	
	

}
