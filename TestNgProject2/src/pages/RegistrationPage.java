package pages;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {
	private static WebDriver driver;
	private static Properties locators;
	private WebDriverWait waiter;

	public RegistrationPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		this.driver = driver;
		this.locators = locators;
		this.waiter = waiter;
	}

	public static WebElement getUserID() {
		return driver.findElement(By.xpath(locators.getProperty("userId")));
	}

	public static void setUserID(String userId) {
		getUserID().clear();
		getUserID().sendKeys(userId);
	}

	public static WebElement getNewPassword() {
		return driver.findElement(By.xpath(locators.getProperty("password")));
	}

	public static WebElement getRepeatPassword() {
		return driver.findElement(By.xpath(locators.getProperty("repeatPasword")));
	}

	public static void setPassword(String password) {
		getNewPassword().clear();
		getNewPassword().sendKeys(password);
	}

	public static void setRepeatPassword(String password) {
		getRepeatPassword().clear();
		getRepeatPassword().sendKeys(password);
	}

	public static WebElement getFirstName() {
		return driver.findElement(By.xpath(locators.getProperty("firstName")));
	}

	public static void setFirstName(String firstName) {
		getFirstName().clear();
		getFirstName().sendKeys(firstName);
	}

	public static WebElement getLastName() {
		return driver.findElement(By.xpath(locators.getProperty("lastName")));
	}

	public static void setLastName(String lastName) {
		getLastName().clear();
		getLastName().sendKeys(lastName);
	}

	public static WebElement getEmail() {
		return driver.findElement(By.xpath(locators.getProperty("email")));
	}

	public static void setEmail(String email) {
		getEmail().clear();
		getEmail().sendKeys(email);
	}

	public static WebElement getPhone() {
		return driver.findElement(By.xpath(locators.getProperty("phone")));
	}

	public static void setPhone(String phone) {
		getPhone().clear();
		getPhone().sendKeys(phone);
	}

	public static WebElement getAddress1() {
		return driver.findElement(By.xpath(locators.getProperty("address1")));
	}

	public static void setAddress1(String address1) {
		getAddress1().clear();
		getAddress1().sendKeys(address1);
	}

	public static WebElement getAddress2() {
		return driver.findElement(By.xpath(locators.getProperty("address2")));
	}

	public static void setAddress2(String address2) {
		getAddress2().clear();
		getAddress2().sendKeys(address2);
	}

	public static WebElement getCity() {
		return driver.findElement(By.xpath(locators.getProperty("city")));
	}

	public static void setCity(String city) {
		getCity().clear();
		getCity().sendKeys(city);
	}

	public static WebElement getState() {
		return driver.findElement(By.xpath(locators.getProperty("state")));
	}

	public static void setState(String state) {
		getState().clear();
		getState().sendKeys(state);
	}

	public static WebElement getZip() {
		return driver.findElement(By.xpath(locators.getProperty("zip")));
	}

	public static void setZip(String zip) {
		getZip().clear();
		getZip().sendKeys(zip);
	}

	public static WebElement getCountry() {
		return driver.findElement(By.xpath(locators.getProperty("country")));
	}

	public static void setCountry(String country) {
		getCountry().clear();
		getCountry().sendKeys(country);
	}

	public static WebElement getLanguage() {
		return driver.findElement(By.xpath(locators.getProperty("languagePereference")));
	}

	public static Select getSelectLanguage() {
		return new Select(getLanguage());
	}

	public static void selectLanguage(String language) {
		getSelectLanguage().selectByValue(language);
	}

	public static WebElement getFavoriteCategory() {
		return driver.findElement(By.xpath(locators.getProperty("favoriteCategory")));
	}

	public static Select getSelectFavoriteCategory() {
		return new Select(getFavoriteCategory());
	}

	public static void selectFavoriteCategory(String category) {
		getSelectFavoriteCategory().selectByValue(category);
	}

	public static WebElement enableMyList() {
		return driver.findElement(By.xpath(locators.getProperty("enableMyList")));
	}

	public static WebElement EnableMyBannerList() {
		return driver.findElement(By.xpath(locators.getProperty("enableMyBanner")));
	}

	public static WebElement getSaveAccauntInformation() {
		return driver.findElement(By.xpath(locators.getProperty("saveAccauntInformation")));
	}

	public static WebElement getSignIn() {
		return driver.findElement(By.xpath(locators.getProperty("signIn")));
	}

	public static void clickSignIn() {
		getSignIn().click();

	}

	public static WebElement getRegisterNow() {
		return driver.findElement(By.xpath(locators.getProperty("registerNow")));
	}

	public static void clickRegisterNow() {
		getRegisterNow().click();

	}

	public static WebElement getLogoImg() {
		return driver.findElement(By.xpath(locators.getProperty("logoImg")));
	}

	public static boolean checkRegistration() {
		boolean isSuccessful = false;
		if (RegistrationPage.getLogoImg().isDisplayed()) {
			isSuccessful = true;
		}
		return isSuccessful;
	}

	public static WebElement getError() {
		return driver.findElement(By.xpath(locators.getProperty("error")));
	}

	public static boolean errorRegistration() {
		boolean error = false;
		if (RegistrationPage.getError().isDisplayed()) {
			error = true;
		}
		return error;
	}

}
