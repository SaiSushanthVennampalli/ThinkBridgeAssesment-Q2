package com.tb.test;

import org.testng.annotations.Test;

import com.tb.ThinkBridgeAssesment.ThinkBridgeBean;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class TestScenarios {
 

	// creating WebDriver object
		public static WebDriver driver;
		
		//object creation for Bean class
		ThinkBridgeBean bean = null;
		
		//application url is saved in a string
		private String url = "https://jt-dev.azurewebsites.net/#/SignUp";
		
		//BrowserDriver Location
		private String driverLocation= System.getProperty("user.dir") + "\\" + "browserDriver" + "\\" + "chromedriver.exe";
			
		@BeforeTest
		public void setUpStep(){
			//Set the path for ChromeDriver
			System.setProperty("webdriver.chrome.driver",driverLocation);
			//Initiate new driver
			driver = new ChromeDriver();
			bean = new ThinkBridgeBean(driver);
			// Launch Website
			driver.get(url);
			// Maximize the browser
			driver.manage().window().maximize();
			//select language from dropdown
			bean.verifyLanguage();
			bean.implicit_Wait(3);
			//Enter text in full name field
			bean.SendName("SaiSushanthVennampalli");
			bean.implicit_Wait(3);
			//Enter text in Organization name field
			bean.SendOrgName("SaiSushanthVennampalli");
			bean.implicit_Wait(3);
			//Check the terms and conditions checkbox
			bean.termsAndConditions();
			
			//Wait for the page to load
			bean.implicit_Wait(3);
		}
		
		@AfterTest
	    public void CloseDriver(){
			//Close the browser
	        driver.close();
	    }

		
		@Test(priority = 1)
		public void TC01_Title_Validation() throws InterruptedException{

			//Expected Title
			String expectedTitle = "Jabatalks";
			//Get Actual Title
			String actualTitle = bean.PageTitle();
			//Verify the Website title
			Assert.assertEquals(expectedTitle, actualTitle);
		}
		@Test(priority = 2)
		public void TC02_InValid_Credentials_Validation() {
			bean.clearEmailField();
			//Enter email in Email field
			bean.SendEmail("saisushanth");
			
			Assert.assertFalse(bean.is_Submit_Enabled(), "Please Enter the Valid Credentials");
		}
		
		@Test(priority = 3)
		public void TC03_Valid_Credentials_Validation() {
			
					//Enter email in Email field
					bean.clearEmailField();
			bean.SendEmail("saisushanth1996@gmail.com");
					bean.implicit_Wait(5);
					//clicking submit button 
					bean.submit();
					 
					bean.implicit_Wait(5);
					//Capture the success message
					String message=bean.getObservedMessage();
					
					//Print Success message
					System.out.println(message);
					
					//Capture Expected Message
					String expectedMessage = "A welcome email has been sent. Please check your email.";
					
					//Validate the message
					Assert.assertEquals(expectedMessage, message);
					
		}
		
		
	   
}
