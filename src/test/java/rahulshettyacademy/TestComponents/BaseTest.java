package rahulshettyacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.PageProject.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("//Users//bebo//eclipse-workspace//SeleniumFrameworkDesigns//src//main//java//rahulshettyacademy//resources//GlobalData.properties");
		prop.load(fis);
		String BrowserName = prop.getProperty("browser");
		
		System.getProperty(BrowserName);
		if (BrowserName.equalsIgnoreCase("chrome")) 
		{
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			if(BrowserName.contains("headless"))
			{
				options.addArguments("headless");
			}
			
		} 
		else if (BrowserName.equalsIgnoreCase("firefox")) 
		{
			WebDriver driver = new FirefoxDriver();
		} 
		else if (BrowserName.equalsIgnoreCase("edge")) 
		{
			WebDriver driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;

	}
	@BeforeMethod(alwaysRun=true)
	public LandingPage LaunchApplication() throws IOException
	{
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.GoTo();
		return landingPage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void CloseWindow()
	{
		driver.close();
	}
	public List<HashMap<String, String>> TransformJsonToHashMap(String FilePath) throws IOException
	{
		String JsonContent = FileUtils.readFileToString(new File(FilePath));
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(JsonContent, new TypeReference<List<HashMap<String,String>>>()
				{
			
				});
		return data;
	}
	public String TakeScreenshot(String TestCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot Screenshot = (TakesScreenshot) driver;
		File source = Screenshot.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//"+TestCaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+TestCaseName+".png";
	}
	

}
