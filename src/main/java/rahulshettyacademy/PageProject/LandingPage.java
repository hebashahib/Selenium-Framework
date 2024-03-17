package rahulshettyacademy.PageProject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	WebDriver driver;
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	//.ng-tns-c4-22.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
	
	@FindBy(css="[class*='flyInOut']")
	WebElement ErrorMessage;
	
	@FindBy(id="userEmail")
	WebElement UserEmail;
	
	@FindBy(id="userPassword")
	WebElement UserPassword;
	
	@FindBy(id="login")
	WebElement Submit;
	
	public ProductCatalogPage LoginApplication(String Email, String Password)
	{
		UserEmail.sendKeys(Email);
		UserPassword.sendKeys(Password);
		Submit.click();
		ProductCatalogPage productcatalogue = new ProductCatalogPage(driver);
		return productcatalogue;
	}
	
	public void GoTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String GetErrorMessage()
	{
		WaitForWebElementToAppear(ErrorMessage);
		return ErrorMessage.getText();
	}
	
	

}
