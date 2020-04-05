package test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.RegistrationPage;
import utils.ExcelUtils;

public class RegisrationTest {
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;

	@BeforeClass
	public void setup() throws FileNotFoundException, IOException {

		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
		this.driver = new ChromeDriver();
		this.locators = new Properties();
		locators.load(new FileInputStream("config/selectors.properties"));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test (priority=10)
	public void registerTest() throws InterruptedException {

		this.driver.navigate().to(this.locators.getProperty("registerUrl"));
		Thread.sleep(1000);
		SoftAssert sa = new SoftAssert();
		RegistrationPage register = new RegistrationPage(driver, locators, waiter);

		ExcelUtils.setExcell(this.locators.getProperty(("data_source")));
		ExcelUtils.setWorkSheet(1);
		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			this.driver.navigate().to(this.locators.getProperty("registerUrl"));
			ExcelUtils.setRandomAt(i, 0);
			RegistrationPage.setUserID(ExcelUtils.getDataAt(i, 0));
			RegistrationPage.setPassword(ExcelUtils.getDataAt(i, 1));
			RegistrationPage.setRepeatPassword(ExcelUtils.getDataAt(i, 1));
			RegistrationPage.setFirstName(ExcelUtils.getDataAt(i, 2));
			RegistrationPage.setLastName(ExcelUtils.getDataAt(i, 3));
			RegistrationPage.setEmail(ExcelUtils.getDataAt(i, 4));
			RegistrationPage.setPhone(ExcelUtils.getDataAt(i, 5));
			RegistrationPage.setAddress1(ExcelUtils.getDataAt(i, 6));
			RegistrationPage.setAddress2(ExcelUtils.getDataAt(i, 7));
			RegistrationPage.setCity(ExcelUtils.getDataAt(i, 8));
			RegistrationPage.setState(ExcelUtils.getDataAt(i, 9));
			RegistrationPage.setZip(ExcelUtils.getDataAt(i, 10));
			RegistrationPage.setCountry(ExcelUtils.getDataAt(i, 11));
			ExcelUtils.setLangRandomAt(i, 12);
			RegistrationPage.selectLanguage(ExcelUtils.getDataAt(i, 12));
			ExcelUtils.setFavCategRandomAt(i, 13);
			RegistrationPage.selectFavoriteCategory(ExcelUtils.getDataAt(i, 13));
			RegistrationPage.enableMyList().click();
			RegistrationPage.EnableMyBannerList().click();
			RegistrationPage.getSaveAccauntInformation().click();
			sa.assertTrue(RegistrationPage.getLogoImg().isDisplayed());

		}
		ExcelUtils.closeExcell();
		sa.assertAll();

	}

	@Test (priority=5)
	public void repeatPasswordTest() throws InterruptedException {

		this.driver.navigate().to(this.locators.getProperty("registerUrl"));
		Thread.sleep(1000);
		RegistrationPage register = new RegistrationPage(driver, locators, waiter);

		ExcelUtils.setExcell(this.locators.getProperty(("data_source")));
		ExcelUtils.setWorkSheet(1);
		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			this.driver.navigate().to(this.locators.getProperty("registerUrl"));
			ExcelUtils.setRandomAt(i, 0);
			RegistrationPage.setUserID(ExcelUtils.getDataAt(i, 0));
			RegistrationPage.setPassword(ExcelUtils.getDataAt(i, 1));
			ExcelUtils.setRandomAt(i, 14);
			RegistrationPage.setRepeatPassword(ExcelUtils.getDataAt(i, 14));
			RegistrationPage.setFirstName(ExcelUtils.getDataAt(i, 2));
			RegistrationPage.setLastName(ExcelUtils.getDataAt(i, 3));
			RegistrationPage.setEmail(ExcelUtils.getDataAt(i, 4));
			RegistrationPage.setPhone(ExcelUtils.getDataAt(i, 5));
			RegistrationPage.setAddress1(ExcelUtils.getDataAt(i, 6));
			RegistrationPage.setAddress2(ExcelUtils.getDataAt(i, 7));
			RegistrationPage.setCity(ExcelUtils.getDataAt(i, 8));
			RegistrationPage.setState(ExcelUtils.getDataAt(i, 9));
			RegistrationPage.setZip(ExcelUtils.getDataAt(i, 10));
			RegistrationPage.setCountry(ExcelUtils.getDataAt(i, 11));
			ExcelUtils.setLangRandomAt(i, 12);
			RegistrationPage.selectLanguage(ExcelUtils.getDataAt(i, 12));
			ExcelUtils.setFavCategRandomAt(i, 13);
			RegistrationPage.selectFavoriteCategory(ExcelUtils.getDataAt(i, 13));
			RegistrationPage.enableMyList().click();
			RegistrationPage.EnableMyBannerList().click();
			RegistrationPage.getSaveAccauntInformation().click();

		}
		ExcelUtils.closeExcell();
		assertFalse(RegistrationPage.checkRegistration());

// Expected result: Impossible registration since password and repeat password are not matching
	}

	@Test (priority=6)
	public void emailTest() throws InterruptedException {

		this.driver.navigate().to(this.locators.getProperty("registerUrl"));
		Thread.sleep(1000);
		RegistrationPage register = new RegistrationPage(driver, locators, waiter);

		ExcelUtils.setExcell(this.locators.getProperty(("data_source")));
		ExcelUtils.setWorkSheet(1);
		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			this.driver.navigate().to(this.locators.getProperty("registerUrl"));
			ExcelUtils.setRandomAt(i, 0);
			RegistrationPage.setUserID(ExcelUtils.getDataAt(i, 0));
			RegistrationPage.setPassword(ExcelUtils.getDataAt(i, 1));
			RegistrationPage.setRepeatPassword(ExcelUtils.getDataAt(i, 1));
			RegistrationPage.setFirstName(ExcelUtils.getDataAt(i, 2));
			RegistrationPage.setLastName(ExcelUtils.getDataAt(i, 3));
			ExcelUtils.setRandomAt(i, 14);
			RegistrationPage.setEmail(ExcelUtils.getDataAt(i, 14));
			RegistrationPage.setPhone(ExcelUtils.getDataAt(i, 5));
			RegistrationPage.setAddress1(ExcelUtils.getDataAt(i, 6));
			RegistrationPage.setAddress2(ExcelUtils.getDataAt(i, 7));
			RegistrationPage.setCity(ExcelUtils.getDataAt(i, 8));
			RegistrationPage.setState(ExcelUtils.getDataAt(i, 9));
			RegistrationPage.setZip(ExcelUtils.getDataAt(i, 10));
			RegistrationPage.setCountry(ExcelUtils.getDataAt(i, 11));
			ExcelUtils.setLangRandomAt(i, 12);
			RegistrationPage.selectLanguage(ExcelUtils.getDataAt(i, 12));
			ExcelUtils.setFavCategRandomAt(i, 13);
			RegistrationPage.selectFavoriteCategory(ExcelUtils.getDataAt(i, 13));
			RegistrationPage.enableMyList().click();
			RegistrationPage.EnableMyBannerList().click();
			RegistrationPage.getSaveAccauntInformation().click();

		}
		ExcelUtils.closeExcell();
		assertFalse(RegistrationPage.checkRegistration());
// Expected result: Impossible registration since email does not contain "@"  nor .com/co.uk etc.		
	}

	@Test (priority=7)
	public void numericValueTest() throws InterruptedException {

		this.driver.navigate().to(this.locators.getProperty("registerUrl"));
		Thread.sleep(1000);
		RegistrationPage register = new RegistrationPage(driver, locators, waiter);

		ExcelUtils.setExcell(this.locators.getProperty(("data_source")));
		ExcelUtils.setWorkSheet(1);
		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			this.driver.navigate().to(this.locators.getProperty("registerUrl"));
			ExcelUtils.setRandomAt(i, 0);
			RegistrationPage.setUserID(ExcelUtils.getDataAt(i, 0));
			RegistrationPage.setPassword(ExcelUtils.getDataAt(i, 1));
			RegistrationPage.setRepeatPassword(ExcelUtils.getDataAt(i, 1));
			RegistrationPage.setFirstName(ExcelUtils.getDataAt(i, 2));
			RegistrationPage.setLastName(ExcelUtils.getDataAt(i, 3));
			RegistrationPage.setEmail(ExcelUtils.getDataAt(i, 4));
			RegistrationPage.setPhone(ExcelUtils.getDataAt(i, 6));
			RegistrationPage.setAddress1(ExcelUtils.getDataAt(i, 6));
			RegistrationPage.setAddress2(ExcelUtils.getDataAt(i, 7));
			RegistrationPage.setCity(ExcelUtils.getDataAt(i, 8));
			RegistrationPage.setState(ExcelUtils.getDataAt(i, 9));
			RegistrationPage.setZip(ExcelUtils.getDataAt(i, 10));
			RegistrationPage.setCountry(ExcelUtils.getDataAt(i, 11));
			ExcelUtils.setLangRandomAt(i, 12);
			RegistrationPage.selectLanguage(ExcelUtils.getDataAt(i, 12));
			ExcelUtils.setFavCategRandomAt(i, 13);
			RegistrationPage.selectFavoriteCategory(ExcelUtils.getDataAt(i, 13));
			RegistrationPage.enableMyList().click();
			RegistrationPage.EnableMyBannerList().click();
			RegistrationPage.getSaveAccauntInformation().click();

		}
		ExcelUtils.closeExcell();
		assertFalse(RegistrationPage.checkRegistration());
// Expected result: Impossible registration since the phone field does not accept text only numbers
	}

	@Test (priority=8)
	public void registrationWithoutPasswordTest() throws InterruptedException {

		this.driver.navigate().to(this.locators.getProperty("registerUrl"));
		Thread.sleep(1000);
		RegistrationPage register = new RegistrationPage(driver, locators, waiter);

		ExcelUtils.setExcell(this.locators.getProperty(("data_source")));
		ExcelUtils.setWorkSheet(1);
		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			this.driver.navigate().to(this.locators.getProperty("registerUrl"));
			ExcelUtils.setRandomAt(i, 0);
			RegistrationPage.setUserID(ExcelUtils.getDataAt(i, 0));
			RegistrationPage.setFirstName(ExcelUtils.getDataAt(i, 2));
			RegistrationPage.setLastName(ExcelUtils.getDataAt(i, 3));
			RegistrationPage.setEmail(ExcelUtils.getDataAt(i, 4));
			RegistrationPage.setPhone(ExcelUtils.getDataAt(i, 5));
			RegistrationPage.setAddress1(ExcelUtils.getDataAt(i, 6));
			RegistrationPage.setAddress2(ExcelUtils.getDataAt(i, 7));
			RegistrationPage.setCity(ExcelUtils.getDataAt(i, 8));
			RegistrationPage.setState(ExcelUtils.getDataAt(i, 9));
			RegistrationPage.setZip(ExcelUtils.getDataAt(i, 10));
			RegistrationPage.setCountry(ExcelUtils.getDataAt(i, 11));
			ExcelUtils.setLangRandomAt(i, 12);
			RegistrationPage.selectLanguage(ExcelUtils.getDataAt(i, 12));
			ExcelUtils.setFavCategRandomAt(i, 13);
			RegistrationPage.selectFavoriteCategory(ExcelUtils.getDataAt(i, 13));
			RegistrationPage.enableMyList().click();
			RegistrationPage.EnableMyBannerList().click();
			RegistrationPage.getSaveAccauntInformation().click();

		}
		ExcelUtils.closeExcell();
		assertTrue(RegistrationPage.errorRegistration());
// Expected result: Impossible registration since the password field and repeat password field are not filled
	}

	@Test (priority=9)
	public void incompleteRegistrationTest() throws InterruptedException {

		this.driver.navigate().to(this.locators.getProperty("registerUrl"));
		Thread.sleep(1000);
		RegistrationPage register = new RegistrationPage(driver, locators, waiter);

		ExcelUtils.setExcell(this.locators.getProperty(("data_source")));
		ExcelUtils.setWorkSheet(1);
		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			this.driver.navigate().to(this.locators.getProperty("registerUrl"));
			ExcelUtils.setRandomAt(i, 0);
			RegistrationPage.setUserID(ExcelUtils.getDataAt(i, 0));
			RegistrationPage.setPassword(ExcelUtils.getDataAt(i, 1));
			RegistrationPage.setRepeatPassword(ExcelUtils.getDataAt(i, 1));
			RegistrationPage.setFirstName(ExcelUtils.getDataAt(i, 2));
			RegistrationPage.setLastName(ExcelUtils.getDataAt(i, 3));
			RegistrationPage.setPhone(ExcelUtils.getDataAt(i, 5));
			ExcelUtils.setLangRandomAt(i, 12);
			RegistrationPage.selectLanguage(ExcelUtils.getDataAt(i, 12));
			ExcelUtils.setFavCategRandomAt(i, 13);
			RegistrationPage.getSaveAccauntInformation().click();

		}
		ExcelUtils.closeExcell();
		assertTrue(RegistrationPage.errorRegistration());
// Expected result: Impossible registration since all fields are not filled
	}

	@AfterClass
	public void afterClass() {
		this.driver.close();
	}

}
