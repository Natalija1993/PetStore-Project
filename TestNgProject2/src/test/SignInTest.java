package test;

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

import pages.SignInPage;
import utils.ExcelUtils;

public class SignInTest {
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;

	@BeforeClass
	public void setup() throws FileNotFoundException, IOException {

		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
		driver = new ChromeDriver();
		locators = new Properties();
		locators.load(new FileInputStream("config/selectors.properties"));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test (priority=11)
	public void signInTest() throws InterruptedException {

		this.driver.navigate().to(this.locators.getProperty("signInUrl"));
		Thread.sleep(1000);
		SoftAssert sa = new SoftAssert();

		ExcelUtils.setExcell(this.locators.getProperty(("data_source")));
		ExcelUtils.setWorkSheet(1);
		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			this.driver.navigate().to(this.locators.getProperty("signInUrl"));

			String username = ExcelUtils.getDataAt(i, 0);
			String password = ExcelUtils.getDataAt(i, 1);
			SignInPage signIn = new SignInPage(driver, locators, waiter);
			signIn.fillSignIn(username, password);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			sa.assertTrue(SignInPage.succesfulSignIn());

		}
		ExcelUtils.closeExcell();

		sa.assertAll();

	}

	@Test (priority=12)
	public void signInWithWrongPasswordTest() throws InterruptedException {

		this.driver.navigate().to(this.locators.getProperty("signInUrl"));
		Thread.sleep(1000);
		SoftAssert sa = new SoftAssert();

		ExcelUtils.setExcell(this.locators.getProperty(("data_source")));
		ExcelUtils.setWorkSheet(1);

		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			this.driver.navigate().to(this.locators.getProperty("signInUrl"));
			ExcelUtils.setRandomAt(i, 14);
			String username = ExcelUtils.getDataAt(i, 0);
			String password = ExcelUtils.getDataAt(i, 14);
			SignInPage signIn = new SignInPage(driver, locators, waiter);
			signIn.fillSignIn(username, password);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			sa.assertTrue(SignInPage.unsuccesfulSignIn());
// Expected result: Impossible sign in since password is not correct

		}
		ExcelUtils.closeExcell();

		sa.assertAll();

	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}

}
