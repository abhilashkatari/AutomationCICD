package rahulshettyacademy.pageobjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement userEmail= driver.findElement(By.id("userEmail"));
	//WebElement userPassword = driver.findElement(By.id("userPassword"));
	//WebElement loginButton = driver.findElement(By.id("login"));
	//PageFactory
	
	@FindBy(id = "userEmail")
	WebElement userEmail;
	
	@FindBy(id = "userPassword")
	WebElement userPassword;
	
	@FindBy(id = "login")
	WebElement loginButton;
	
	@FindBy(css = "[class*=flyInOut]")
	WebElement errorMessage;
	
	public ProductCatalogue loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		//loginButton.click();
		loginButton.sendKeys(Keys.RETURN);
		return new ProductCatalogue(driver);
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}

}
