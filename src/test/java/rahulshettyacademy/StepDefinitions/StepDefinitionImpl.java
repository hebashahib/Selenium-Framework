package rahulshettyacademy.StepDefinitions;

import java.io.IOException;

import org.testng.Assert;
import org.testng.AssertJUnit;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.PageProject.CartPage;
import rahulshettyacademy.PageProject.CheckoutPage;
import rahulshettyacademy.PageProject.ConfirmationPage;
import rahulshettyacademy.PageProject.LandingPage;
import rahulshettyacademy.PageProject.ProductCatalogPage;
import rahulshettyacademy.TestComponents.BaseTest;

public class StepDefinitionImpl extends BaseTest{
	
	public LandingPage landinePage;
	public ProductCatalogPage ProductCatalog;
	CartPage cartPage;
	CheckoutPage checkoutPage;
	ConfirmationPage confirmationPage;
	@Given("I landed on Commerce Page")
	public void I_landed_on_Commerce_Page() throws IOException
	{
		landingPage = LaunchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_username_password(String Username, String Password) 
	{
		ProductCatalog = landingPage.LoginApplication(Username, Password);
	}
	
	@When("^I add product (.+) to cart$")
	public void I_add_product_productname_to_cart(String ProductName)
	{
		ProductCatalog.GetProductByName(ProductName);
		ProductCatalog.AddProductToCart(ProductName);
	}
	
	@And("^checkout (.+) and submit the order$")
	public void checkout_productname_and_submit_the_order(String ProductName)
	{
		cartPage = ProductCatalog.GoToCart();
		Boolean match = cartPage.VerifyProductName(ProductName);
		Assert.assertTrue(match);
		checkoutPage = cartPage.ProceedToCheckout();
		checkoutPage.ChooseCountry("india");
		confirmationPage = checkoutPage.Checkout();
		
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_ConfirmationPage(String string)
	{
		String Message = confirmationPage.VerifyOrderConfirmation();
		Assert.assertTrue(string.equalsIgnoreCase(Message));
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void string_message_is_displayed(String string)
	{
		
		AssertJUnit.assertEquals(string, landingPage.GetErrorMessage());
		driver.close();
	}
	
	
	
	
	

}
