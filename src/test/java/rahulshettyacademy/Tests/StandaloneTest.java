package rahulshettyacademy.Tests;

import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import rahulshettyacademy.PageProject.LandingPage;

/**
 * Unit test for simple App.
 */
public class StandaloneTest 
{
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String ProductName = "ZARA COAT 3";
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
//		driver.findElement(By.className("login-wrapper-footer-text")).click();
//		driver.findElement(By.id("firstName")).sendKeys("Heba");
//		driver.findElement(By.id("lastName")).sendKeys("Shahib");
//		driver.findElement(By.id("userEmail")).sendKeys("heba.shahib14@gmail.com");
//		driver.findElement(By.id("userMobile")).sendKeys("1234567899");
//		driver.findElement(By.xpath("//div//select[@formcontrolname='occupation']")).click();
//		Select checklist = new Select(driver.findElement(By.xpath("//div//select[@formcontrolname='occupation']")));
//		checklist.selectByVisibleText("Student");
//		driver.findElement(By.xpath("//label//input[@value='Female']")).click();
//		driver.findElement(By.id("userPassword")).sendKeys("HebaShahib1");
//		driver.findElement(By.id("confirmPassword")).sendKeys("HebaShahib1");
//		driver.findElement(By.xpath("//div//input[@formcontrolname='required']")).click();
//		driver.findElement(By.id("login")).click();
//		driver.findElement(By.xpath("//div//button[@tabindex='0']")).click();
		
		LandingPage landingPage = new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("heba.shahib14@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("HebaShahib1");
		driver.findElement(By.id("")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		//driver.findElements(By.cssSelector(".mb-3"));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
			//System.out.println(webelements.size());
//			webelements.get(i).findElement(By.xpath("//button[@class='btn w-10 rounded']")).click();
//			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[routerlink*='cart']")));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> CartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = CartProducts.stream().anyMatch(CartProduct->CartProduct.getText().equalsIgnoreCase(ProductName));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
		String ConfirmationMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(ConfirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
		//driver.quit();
	}

}
