package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory
	
	@FindBy(css = ".cartSection h3")
	private List<WebElement> cartProducts;
	
	@FindBy(css = ".totalRow button")
	WebElement checkoutButton;
	
	By cartsection =  By.cssSelector(".cartSection h3");
	
	public Boolean verifyCartItems(String productName) {
		waitForElementToAppear(cartsection);		
		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;		
	}
	
	public CheckoutPage goToCheckoutPage() {
		checkoutButton.click();
		return new CheckoutPage(driver);
	}
	
}
