package rahulshettyacademy.PageProject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ProductCatalogPage extends AbstractComponent{
	
	WebDriver driver;
	public ProductCatalogPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css=".mb-3")
	List<WebElement> ListOfProducts;
	
	
	
//	@FindBy(css=".card-body button:last-of-type")
//	WebElement AddToCartButton;
	
	
	
	
	
	
	

	
	
	@FindBy(css="[routerlink*='cart']")
	WebElement Cart;
	
	By ProductsBy = By.cssSelector(".mb-3");
	By AddToCartButton = By.cssSelector(".card-body button:last-of-type");
	By SideBar = By.cssSelector("#toast-container");
	By CartButton = By.cssSelector("[routerlink*='cart']");
	
	public List<WebElement> GetProductList()
	{
		WaitForVisibilityOFElement(ProductsBy);
		return ListOfProducts;
	}
	public WebElement GetProductByName(String ProductName)
	{	
		WebElement prod = ListOfProducts.stream().filter(ListOfProduct->ListOfProduct.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst().orElse(null);
		//prod.findElement(AddToCartButton).click();
		return prod;
	}
	
	public void AddProductToCart(String ProductName)
	{
		WebElement prod = GetProductByName(ProductName);
		prod.findElement(AddToCartButton).click();
		WaitForVisibilityOFElement(SideBar);
		WaitForVisibilityOFElement(CartButton);
		
	}
	public CartPage GoToCart()
	{
		
		Cart.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	
	
	

}
