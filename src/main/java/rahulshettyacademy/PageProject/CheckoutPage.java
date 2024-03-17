package rahulshettyacademy.PageProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{

	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement CountryToSelect;
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement InsertCountry;
	
	@FindBy(css=".action__submit")
	WebElement CheckoutButton;
	
	By DropDownSelectList = By.cssSelector(".ta-results");
	public void ChooseCountry(String CountryToInsert)
	{
		Actions a = new Actions(driver);
		a.sendKeys(InsertCountry,CountryToInsert).build().perform();
		WaitForVisibilityOFElement(DropDownSelectList);
		CountryToSelect.click();
		
	}
	
	public ConfirmationPage Checkout()
	{
		CheckoutButton.click();
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
	}

}
