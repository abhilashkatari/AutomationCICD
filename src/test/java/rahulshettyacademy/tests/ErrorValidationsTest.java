package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {
	
	@Test(groups = "ErrorHandling", retryAnalyzer = Retry.class)
	public void LoginErrorValidation() throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		landingPage.loginApplication("abhi@access.com", "abacAcabss123$");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		
		
	}
	
	@Test
	public void ProductErrorValidation() throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		String countryName = "India";
		ProductCatalogue productCatalogue = landingPage.loginApplication("rahulshetty@gmail.com", "Iamking@000");
		
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		
		Boolean match = cartPage.verifyCartItems("ZARA COAT 33");
		Assert.assertFalse(match);
		
	}

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
