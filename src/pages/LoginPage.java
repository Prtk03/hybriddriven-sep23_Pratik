package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import base.ControlActions;

public class LoginPage extends ControlActions {

	public void login(String email, String password) {
		System.out.println("STEP: Entered Email address");
		driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("pratikkabra0391@gmail.com");

		System.out.println("STEP: Entered Password");
		driver.findElement(By.xpath("//input[@id='userPassword']")).sendKeys("Test@123");

		System.out.println("STEP: Clicked on Login Button");
		driver.findElement(By.xpath("//input[@id='login']")).click();
	}

	public boolean isLoginSuccessFullyDisplayed() {
		WebElement loginSuccesfulElement = getElement("XPATH", "//div[@aria-label='Login Successfully']", true);
		return loginSuccesfulElement.isDisplayed();
	}
}
