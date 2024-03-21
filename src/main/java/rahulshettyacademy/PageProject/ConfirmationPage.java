package rahulshettyacademy.PageProject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {

	WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		
		// TODO Auto-generated constructor stub
	}

	@FindBy(css=".hero-primary")
	WebElement SuccessfulOrder;

	public String VerifyOrderConfirmation()
	{
		
		return SuccessfulOrder.getText();
	}

}
