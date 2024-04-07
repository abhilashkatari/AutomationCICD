package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;

public class SubmitOrderTest extends BaseTest {
	
	String productName = "ZARA COAT 3";
	
	@Test(dataProvider = "getData", groups = {"Purchase"})
	//public void submitOrder(String email, String password, String productName) throws InterruptedException, IOException {
	public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		
		String countryName = "India";
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();
		
		Boolean match = cartPage.verifyCartItems(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckoutPage();
		
		checkoutPage.selectCountry(countryName);
		ConfirmationPage orderDetails = checkoutPage.placeOrder();
		
		String confirmationMessage = orderDetails.getConfirmationMessage();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistoryTest() {
		//"ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("abhi@access.com", "abacAcab123$");
		OrderPage orderPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
		
	}
	
	//Extent Reports - 
	
	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
		
	}
	
	/*
	@DataProvider
	public Object[][] getData() {		
		return new Object[][] {{"abhi@access.com","abacAcab123$","ZARA COAT 3"},{"shetty@gmail.com","Iamking@000","IPHONE 13 PRO"}};		
	}*/
	
	//HashMap<String, String> map = new HashMap<String, String>();
	//map.put("email", "abhi@access.com");
	//map.put("password", "abacAcab123$");
	//map.put("product", "ZARA COAT 3");
	//
	//HashMap<String, String> map1 = new HashMap<String, String>();
	//map1.put("email", "shetty@gmail.com");
	//map1.put("password", "Iamking@000");
	//map1.put("product", "IPHONE 13 PRO");

}
















/*
driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("ind");
wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("span[class='ng-star-inserted']"))));
List<WebElement> places = driver.findElements(By.cssSelector("span[class='ng-star-inserted']"));
WebElement selPlace = places.stream().filter(place->place.getText().equals("India")).findFirst().orElse(null);
selPlace.click();

driver.findElement(By.cssSelector(".action__submit")).click();

List<WebElement> orderIds = driver.findElements(By.cssSelector("label[class='ng-star-inserted']"));
orderIds.stream().filter(s->s.getText().equals(" || ")).forEach(s->System.out.println(s));
*/
