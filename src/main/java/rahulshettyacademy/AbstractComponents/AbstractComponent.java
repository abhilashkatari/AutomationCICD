package rahulshettyacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.OrderPage;

public class AbstractComponent {
	
	WebDriver driver;
	WebDriverWait wait;
	
	public AbstractComponent(WebDriver driver) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(css = "[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderHeader;
	
	public void waitForElementToAppear(By findBy) {
				
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
		
	}
	
	public void waitForWebElementToAppear(WebElement findElement) {
		
		wait.until(ExpectedConditions.visibilityOf(findElement));
		
	}
	
	public CartPage goToCartPage() {
		cartHeader.click();
		return new CartPage(driver);
		
	}

	public OrderPage goToOrdersPage() {
		orderHeader.click();
		return new OrderPage(driver);
	}
	
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException {
		
		Thread.sleep(1000);
		//wait.until(ExpectedConditions.invisibilityOf(ele));
		
	}

}
