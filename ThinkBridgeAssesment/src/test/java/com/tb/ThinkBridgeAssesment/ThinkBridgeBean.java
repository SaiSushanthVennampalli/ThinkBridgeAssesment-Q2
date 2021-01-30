package com.tb.ThinkBridgeAssesment;

import static org.junit.Assert.fail;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ThinkBridgeBean {

	private WebDriver driver;
	 
	//This initElements method will create all WebElements
	public ThinkBridgeBean(WebDriver driver){
        this.driver = driver;
        
        PageFactory.initElements(driver, this);
    }  
	
	@FindBy(xpath="//span[@class='black-color ng-binding']")
	public WebElement TCs;
	
	public void termsAndConditions() {
		new Actions(driver).moveToElement(TCs, 1, 1).click().perform();
	}
	
	@FindBy(xpath="//button[@type='submit']")
	public WebElement submit;
	
	public void submit() {
		submit.click();
	}
	public boolean is_Submit_Enabled() {
		return submit.isEnabled();
	}
	
	@FindBy(xpath="//span[@class='ng-binding'][contains(text(),'A welcome email has')]")
	public WebElement ObservedMessage;
	
	public String getObservedMessage() {
		if(ObservedMessage.isDisplayed()) {
		return ObservedMessage.getText();}
		else {
		 implicit_Wait(5);
		 return getObservedMessage();
		}
	}
	@FindBy(id = "name")
	public WebElement name;
	 
	public void SendName(String fname) {
		name.sendKeys(fname); 
	 }
	
	@FindBy(id = "orgName")
	public WebElement orgnisationName;
	
	public void SendOrgName(String orgName) {
		orgnisationName.sendKeys(orgName);
	 }
	
	@FindBy(id = "singUpEmail")
	public WebElement singUpEmail;
	
	public void SendEmail(String email) {
		singUpEmail.sendKeys(email); 
	 }
	 public void clearEmailField() {
		 singUpEmail.clear();
	 }
	
	@FindBy(id= "language")      // Language element
	WebElement language;
	
	@FindBy(xpath = "//div[contains(@id,'ui-select-choices-row-1-')]")    // all the languages in the dropdown
	List<WebElement> languages;
	
	public void clickLanguage() {
		language.click();
	}
	 public void implicit_Wait(int time) {
		 driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	 }
	 
	 public void verifyLanguage() {
		 language.click();
		 for(int i=0; i<languages.size(); i++) {
				String lang=languages.get(i).getText();
				System.out.println(lang);
			    if(!(lang.contains("English") || lang.contains("Dutch"))) {
				    Assert.fail("The language is not English or Dutch");
				}
	 }
		 }
	 
	
	
	public String PageTitle() {
		return driver.getTitle();
	}

}
