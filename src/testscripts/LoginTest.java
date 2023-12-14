package testscripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.ControlActions;
import pages.LoginPage;
import utility.ExcelOperations;

public class LoginTest {
	LoginPage loginPage; 
	
	@BeforeMethod
	public void setup() {
		ControlActions.launchBrowser();
		loginPage =	new LoginPage();
	}

	@Test
	public void verifyLogin() {
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		loginPage.login("pratikkabra0391@gmail.com", "Test@123");
		boolean loginFlag = loginPage.isLoginSuccessFullyDisplayed();
		Assert.assertTrue(loginFlag);
	}
	
	@Test
	public void verifyErrorMessages() {
		System.out.println("STEP: Click on Login Button");
		loginPage.clickOnLoginBtn();
		
		System.out.println("VERIFY: Email required Error message is visible");
		boolean emailErrorMessageFlag = loginPage.isEmailRequiredElementDisplayed();
		Assert.assertTrue(emailErrorMessageFlag);
		
		System.out.println("VERIFY: Password required Error message is visible");
		boolean passwordErrorMessageFlag = loginPage.isPasswordRequiredElementDisplayed();
		Assert.assertTrue(passwordErrorMessageFlag);
	}
	
	@Test
	public void verifyPasswordErrorMessages() {
		System.out.println("STEP: Enter valid User email");
		loginPage.enterUserEmail("pratikkabra0391@gmail.com");
		
		System.out.println("STEP: Click on Login Button");
		loginPage.clickOnLoginBtn();
		
		System.out.println("VERIFY: Email required Error message is not visible");
		boolean emailErrorMessageFlag = loginPage.isEmailRequiredElementDisplayed();
		Assert.assertFalse(emailErrorMessageFlag);
		
		System.out.println("VERIFY: Password required Error message is visible");
		boolean passwordErrorMessageFlag = loginPage.isPasswordRequiredElementDisplayed();
		Assert.assertTrue(passwordErrorMessageFlag);
	}
	
	@Test
	public void verifyEmailErrorMessagesDisplayed() {
		System.out.println("STEP: Enter valid pasword");
		loginPage.enterPassword("Test@123");
		
		System.out.println("STEP: Click on Login Button");
		loginPage.clickOnLoginBtn();
		
		System.out.println("VERIFY: Email required Error message is visible");
		boolean emailErrorMessageFlag = loginPage.isEmailRequiredElementDisplayed();
		Assert.assertTrue(emailErrorMessageFlag);
		
		System.out.println("VERIFY: Password required Error message is not visible");
		boolean passwordErrorMessageFlag = loginPage.isPasswordRequiredElementDisplayed();
		Assert.assertFalse(passwordErrorMessageFlag);
	}
	
	@Test(dataProvider = "LoginDataProvider")
	public void verifyLoginUsingDataDriven(String username, String password, String loginStatus) {
		System.out.println("STEP:Login with given credentials");
		loginPage.login(username, password);
		String currentURL="" ; 
		boolean loginFlag;
		if(loginStatus.equalsIgnoreCase("pass")) {
			System.out.println("VERIFY: Login successful toast message displayed");
			loginFlag=loginPage.isLoginSuccessFullyDisplayed();
			Assert.assertTrue(loginFlag,"Login successfully message was not displayed");
			
			System.out.println("VERIFY: Incorrect email or password message is not displayed");
			loginFlag=loginPage.isLoginUnSuccessfulElemetDisplayed();
			Assert.assertFalse(loginFlag,"Incorrect email or password message was displayed");
			
			currentURL = loginPage.getCurrentURL();
			System.out.println("VERIFY: Application should redirect to dashboard page");
			Assert.assertTrue(currentURL.endsWith("dashboard/dash"),"Current URL : "+currentURL);
			
		}else {
			loginFlag=loginPage.isLoginUnSuccessfulElemetDisplayed();
			Assert.assertTrue(loginFlag,"Incorrect email or password message was not displayed");
			
			loginFlag=loginPage.isLoginSuccessFullyDisplayed();
			Assert.assertFalse(loginFlag,"Login successfully message was displayed");
			
			currentURL = loginPage.getCurrentURL();
			Assert.assertTrue(currentURL.endsWith("/auth/login"));
		}
	}
	
	@DataProvider(name="LoginDataProvider")
	public Object[][] getLoginData() throws IOException {
		return ExcelOperations.getAllRows(".//testData/LoginData.xlsx", "Login");
	}

	@AfterMethod
	public void tearDown() {
		ControlActions.closeBrowser();
	}
}
