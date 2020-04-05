package pages;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;

	public CartPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		this.driver = driver;
		this.locators = locators;
		this.waiter = waiter;
	}

	public List<WebElement> getRows() {
		return this.driver.findElements(By.xpath(this.locators.getProperty("row")));
	}

	public WebElement getRowItem(int rowIndex) {
		return this.getRows().get(rowIndex).findElement(By.xpath(this.locators.getProperty("cellItemID")));
	}

	public WebElement getRowProduct(int rowIndex) {
		return this.getRows().get(rowIndex).findElement(By.xpath(this.locators.getProperty("cellProductID")));
	}

	public WebElement getRowDescription(int rowIndex) {
		return this.getRows().get(rowIndex).findElement(By.xpath(this.locators.getProperty("celldDescription")));
	}

	public WebElement getRowStock(int rowIndex) {
		return this.getRows().get(rowIndex).findElement(By.xpath(this.locators.getProperty("cellInStock")));
	}

	public WebElement getRowQuantity(int rowIndex) {
		return this.getRows().get(rowIndex).findElement(By.xpath(this.locators.getProperty("cellQuantity")));
	}

	public WebElement getRowPrice(int rowIndex) {
		return this.getRows().get(rowIndex).findElement(By.xpath(this.locators.getProperty("cellPriceList")));
	}

	public WebElement getRowTotalCost(int rowIndex) {
		return this.getRows().get(rowIndex).findElement(By.xpath(this.locators.getProperty("totalCost")));
	}

	public WebElement getRowCellRemove(int rowIndex) {
		return this.getRows().get(rowIndex).findElement(By.xpath(this.locators.getProperty("cellRemove")));
	}

	public WebElement getCheckoutBtn() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("checkoutBtn")));
	}

	public void clickCheckoutBtn() {
		this.getCheckoutBtn().click();
	}

	public boolean successfulRemove() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("emptyCart"))).isDisplayed();
	}
}
