
package pages;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PetStoreManuPage {
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;

	public PetStoreManuPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		this.driver = driver;
		this.locators = locators;
		this.waiter = waiter;
	}

	public WebElement getSignIn() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("signIn")));
	}

	public void clickSignInBtn() {
		this.getSignIn().click();
	}

	public boolean successfulClick() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("loginBtn"))).isDisplayed();
	}

	public List<WebElement> getAllLinks() {
		return this.driver.findElements(By.tagName("a"));
	}

	public boolean isLeadingToPage() throws InterruptedException {
		List<WebElement> links = this.getAllLinks();
		boolean logged = false;
		for (int i = 0; i < links.size(); i++) {

			if (links.get(i).getAttribute("href").contains("FISH")) {
				logged = true;
				System.out.println("Url contains Fish");
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			} else if (links.get(i).getAttribute("href").contains("DOGS")) {
				logged = true;
				System.out.println("url contains DOGS");
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			} else if (links.get(i).getAttribute("href").contains("CATS")) {
				logged = true;
				System.out.println("url contains CATS");
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			} else if (links.get(i).getAttribute("href").contains("REPTILES")) {
				logged = true;
				System.out.println("url contains REPTIELS");
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			} else if (links.get(i).getAttribute("href").contains("BIRDS")) {
				logged = true;
				System.out.println("url contains BIRDS");
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			}
			links = this.getAllLinks();
		}
		return logged;

	}
}