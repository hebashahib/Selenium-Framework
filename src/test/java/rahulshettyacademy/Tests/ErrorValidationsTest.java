package rahulshettyacademy.Tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.AbstractComponents.AbstractComponent;
import rahulshettyacademy.PageProject.CartPage;
import rahulshettyacademy.PageProject.ProductCatalogPage;
import rahulshettyacademy.TestComponents.BaseTest;

public class ErrorValidationsTest extends BaseTest{
	
//	,retryAnalyzer=Retry.class
	@Test(groups = {"ErrorValidation"})
	public void LoginCheck()
	{
		String Email = "heba.sahib14@gmail.com";
		String Password = "HebaShahib1";
		landingPage.LoginApplication(Email, Password);
		AssertJUnit.assertEquals("Incorrect email or password.", landingPage.GetErrorMessage());
	}
	
	@Test
	public void VerifyOrderNameCheck() throws IOException {
		// TODO Auto-generated method stub
		
		String ProductName = "ZARA COAT 3";
		String Email = "heba.shahib14@gmail.com";
		String Password = "HebaShahib1";
		String Country = "india";
		String OutputMessage = "THANKYOU FOR THE ORDER.";	
		ProductCatalogPage productCatalog = landingPage.LoginApplication(Email, Password);
		AbstractComponent CommonFunctions = new AbstractComponent(driver);
		List<WebElement> Products = productCatalog.GetProductList();
		productCatalog.GetProductByName(ProductName);
		productCatalog.AddProductToCart(ProductName);
		CartPage cartPage = productCatalog.GoToCart();
		Boolean match = cartPage.VerifyProductName("ZARA COAT 33");
		Assert.assertFalse(match);
	}
	

}
