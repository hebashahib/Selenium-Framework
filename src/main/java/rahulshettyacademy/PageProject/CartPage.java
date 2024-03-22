package rahulshettyacademy.PageProject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		
		// TODO Auto-generated constructor stub
	}

// add new comment

	@FindBy(css=".cartSection h3")
	List<WebElement> ListOfProductsInCart;
	
	@FindBy(css=".totalRow button")
	WebElement ProceedToCheckoutButton;
	
	public Boolean VerifyProductName(String ProductName) {
		Boolean match = ListOfProductsInCart.stream().anyMatch(CartProduct->CartProduct.getText().equalsIgnoreCase(ProductName));
		return match;
	}
	public CheckoutPage ProceedToCheckout()
	{
		//GoToCart();
		//List<WebElement> CartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		
		ProceedToCheckoutButton.click();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
		
	}

}
