package rahulshettyacademy.Tests;

import static org.testng.AssertJUnit.assertTrue;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.AbstractComponents.AbstractComponent;
import rahulshettyacademy.PageProject.CartPage;
import rahulshettyacademy.PageProject.CheckoutPage;
import rahulshettyacademy.PageProject.ConfirmationPage;
import rahulshettyacademy.PageProject.LandingPage;
import rahulshettyacademy.PageProject.ProductCatalogPage;
import rahulshettyacademy.TestComponents.BaseTest;

/**
 * Unit test for simple App.
 */
public class SubmitOrderTest extends BaseTest
{
	
	@Test(dataProvider="GetData" , groups = "Purchase")
	public void SubmitOrderCheck(HashMap<String,String> input) throws IOException {
		// TODO Auto-generated method stub
		String Country = "india";
		String OutputMessage = "THANKYOU FOR THE ORDER.";
		
		ProductCatalogPage productCatalog = landingPage.LoginApplication(input.get("Email"), input.get("Password"));
		AbstractComponent CommonFunctions = new AbstractComponent(driver);
		List<WebElement> Products = productCatalog.GetProductList();
		//productCatalog.GetProductByName(input.get("Product"));
		productCatalog.AddProductToCart(input.get("Product"));
		CartPage cartPage = productCatalog.GoToCart();
		Boolean match = cartPage.VerifyProductName(input.get("Product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.ProceedToCheckout();
		checkoutPage.ChooseCountry(Country);
		ConfirmationPage confirmationPage = checkoutPage.Checkout();
		
		String ConfirmationMessage = confirmationPage.VerifyOrderConfirmation();
		Assert.assertTrue(ConfirmationMessage.equalsIgnoreCase(OutputMessage));
		
		
	}
	
	@DataProvider
	public Object[][] GetData() throws IOException
	{
		List<HashMap<String, String>> Maps = TransformJsonToHashMap(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//JsonData.json");
		return new Object[][] {{Maps.get(0)},{Maps.get(1)}};
		
	}

}

//
//HashMap<String,String> FirstMap = new HashMap<String,String>();
//HashMap<String,String> SecondMap = new HashMap<String,String>();
//FirstMap.put("Email", "heba.shahib14@gmail.com");
//FirstMap.put("Password", "HebaShahib1");
//FirstMap.put("Product", "ZARA COAT 3");
//SecondMap.put("Email", "heba.shahib14@gmail.com");
//SecondMap.put("Password", "HebaShahib1");
//SecondMap.put("Product", "ADIDAS ORIGINAL");
//return new Object[][] {{FirstMap},{SecondMap}};

//Registration Code
//driver.findElement(By.className("login-wrapper-footer-text")).click();
//driver.findElement(By.id("firstName")).sendKeys("Heba");
//driver.findElement(By.id("lastName")).sendKeys("Shahib");
//driver.findElement(By.id("userEmail")).sendKeys("heba.shahib14@gmail.com");
//driver.findElement(By.id("userMobile")).sendKeys("1234567899");
//driver.findElement(By.xpath("//div//select[@formcontrolname='occupation']")).click();
//Select checklist = new Select(driver.findElement(By.xpath("//div//select[@formcontrolname='occupation']")));
//checklist.selectByVisibleText("Student");
//driver.findElement(By.xpath("//label//input[@value='Female']")).click();
//driver.findElement(By.id("userPassword")).sendKeys("HebaShahib1");
//driver.findElement(By.id("confirmPassword")).sendKeys("HebaShahib1");
//driver.findElement(By.xpath("//div//input[@formcontrolname='required']")).click();
//driver.findElement(By.id("login")).click();
//driver.findElement(By.xpath("//div//button[@tabindex='0']")).click();
