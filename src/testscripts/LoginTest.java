package testscripts;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.ControlActions;
import pages.LoginPage;

public class LoginTest {

	@BeforeMethod
	public void setup() {

		ControlActions.launchBrowser();
	}

	@Test
	public void verifyLogin() {

		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		LoginPage loginPage = new LoginPage();
		loginPage.login("pratikkabra0391@gmail.com", "Test@123");

		boolean loginFlag = loginPage.isLoginSuccessFullyDisplayed();
		Assert.assertTrue(loginFlag);
	}

	@AfterMethod
	public void tearDown() {
		ControlActions.closeBrowser();
	}
}
