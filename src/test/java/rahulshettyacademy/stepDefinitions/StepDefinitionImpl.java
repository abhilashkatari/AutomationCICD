package rahulshettyacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage orderDetails;
	
	@Given("I landed on the Ecommerce Page")
	public void I_landed_on_the_Ecommerce_Page() throws IOException {
		
		landingPage = launchApplication();
	}
	
	@Given("^Logged in with the username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password) {
		productCatalogue = landingPage.loginApplication(username, password);
	}
	
	
	@When("^I add the product (.+) to cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException {
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		
	}
	
	@And("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName) {
		CartPage cartPage = productCatalogue.goToCartPage();
		
		Boolean match = cartPage.verifyCartItems(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckoutPage();
		
		checkoutPage.selectCountry("india");
		orderDetails = checkoutPage.placeOrder();
		
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_confirmationPage(String message) {
		String confirmationMessage = orderDetails.getConfirmationMessage();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase(message));
		driver.close();
	}
	
	@Then("^\"([^\"]*)\" message is displayed$")
	public void something_message_is_displayed(String strArg1) throws Throwable {
		Assert.assertEquals(strArg1, landingPage.getErrorMessage());
		driver.close();
		
	}
	 

}
