package pages;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage {
	private static WebDriver driver;
	private static Properties locators;
	private WebDriverWait waiter;

	public SignInPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		this.driver = driver;
		this.locators = locators;
		this.waiter = waiter;
	}

	public static WebElement getUsername() {
		return driver.findElement(By.xpath(locators.getProperty("usernameInput")));
	}

	public static void setUsername(String username) {
		getUsername().clear();
		getUsername().sendKeys(username);
	}

	public static WebElement getPassword() {
		return driver.findElement(By.xpath(locators.getProperty("passwordInput")));
	}

	public static void setPassword(String password) {
		getPassword().clear();
		getPassword().sendKeys(password);
	}

	public static WebElement getLogInBtn() {
		return driver.findElement(By.xpath(locators.getProperty("loginBtn")));
	}

	public static void clickLogInBtn() {
		getLogInBtn().click();
	}

	public void fillSignIn(String username, String password) {
		SignInPage.setUsername(username);
		SignInPage.setPassword(password);
		SignInPage.clickLogInBtn();
	}

	public static boolean succesfulSignIn() {
		return driver.findElement(By.xpath(locators.getProperty("welcomeMessage"))).isDisplayed();
	}

	public static boolean unsuccesfulSignIn() {
		return driver.findElement(By.xpath(locators.getProperty("wrongCredentials"))).isDisplayed();
	}
}
