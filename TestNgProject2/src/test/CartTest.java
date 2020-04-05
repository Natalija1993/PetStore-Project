package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

import pages.CartPage;
import pages.StoreItemPage;
import utils.ExcelUtils;

public class CartTest {
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

	@Test (priority=13)
	public void cartTest() throws InterruptedException {
		this.driver.navigate().to(this.locators.getProperty("petStoreUrl"));

		Thread.sleep(1000);
		StoreItemPage item = new StoreItemPage(driver, locators, waiter);
		CartPage cart = new CartPage(driver, locators, waiter);
		ExcelUtils.setExcell(this.locators.getProperty(("data_source")));
		ExcelUtils.setWorkSheet(0);

		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			driver.navigate().to(ExcelUtils.getDataAt(i, 1));
			item.clickAddToCart();
		}

		boolean isMatch = true;
		try {
			for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
				String idSite = cart.getRowItem(i).getText();
				String idSheet = ExcelUtils.getDataAt(i, 0);

				if (idSheet.equals(idSite)) {
					isMatch = true;
					System.out.println("Matching " + idSheet + " with " + idSite);
				}
			}
		} catch (Exception e) {
			isMatch = false;
			System.out.println("Not matching");
		}
		Assert.assertTrue(isMatch);

	}

	@Test (priority=14)
	public void emptyCartTest() throws InterruptedException {

		SoftAssert sa = new SoftAssert();
		StoreItemPage item = new StoreItemPage(driver, locators, waiter);
		CartPage cart = new CartPage(driver, locators, waiter);
		ExcelUtils.setExcell(this.locators.getProperty(("data_source")));
		ExcelUtils.setWorkSheet(0);

		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			driver.navigate().to(ExcelUtils.getDataAt(i, 1));
			item.clickAddToCart();

		}
		this.driver.navigate().to(this.locators.getProperty("cartUrl"));
		driver.navigate().refresh();
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();
		sa.assertTrue(cart.successfulRemove());

	}

	@AfterClass
	public void afterClass() {
		this.driver.close();
	}
}
