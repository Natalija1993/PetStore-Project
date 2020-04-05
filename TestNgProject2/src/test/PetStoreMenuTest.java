package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.PetStoreManuPage;

public class PetStoreMenuTest {
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

	@Test (priority=2)
	public void clickOnLinks() throws InterruptedException {
		this.driver.navigate().to(this.locators.getProperty("petMenuUrl"));
		Thread.sleep(1000);
		PetStoreManuPage meni = new PetStoreManuPage(driver, locators, waiter);
		meni.isLeadingToPage();
		Assert.assertTrue(meni.isLeadingToPage());
	}

	@Test (priority=3)
	public void clickOnSignIn() throws InterruptedException {
		this.driver.navigate().to(this.locators.getProperty("petMenuUrl"));
		Thread.sleep(1000);
		PetStoreManuPage meni = new PetStoreManuPage(driver, locators, waiter);
		meni.clickSignInBtn();

		Assert.assertTrue(meni.successfulClick());

	}

	@Test (priority=4)
	public void allValidLinks() throws InterruptedException {
		this.driver.navigate().to(this.locators.getProperty("petMenuUrl"));
		Thread.sleep(1000);
		PetStoreManuPage meni = new PetStoreManuPage(driver, locators, waiter);
		SoftAssert sa = new SoftAssert();

		meni.getAllLinks();
		for (int i = 0; i < meni.getAllLinks().size(); i++) {
			int status = verifyURLStatus(meni.getAllLinks().get(i).getAttribute("href"));
			sa.assertTrue(status < 400);
		}

		sa.assertAll();

	}

	public int verifyURLStatus(String urlString) {
		int status = 404;
		try {
			URL link = new URL(urlString);
			HttpURLConnection hConn = null;
			hConn = (HttpURLConnection) link.openConnection();
			hConn.setRequestMethod("GET");
			hConn.connect();
			status = hConn.getResponseCode();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

	@AfterClass
	public void afterClass() {
		this.driver.close();
	}
}
