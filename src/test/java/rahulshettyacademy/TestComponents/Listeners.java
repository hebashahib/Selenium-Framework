package rahulshettyacademy.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshettyacademy.resources.ExtentReportsNG;

public class Listeners extends BaseTest implements ITestListener{
	
	ExtentReports extent = ExtentReportsNG.getReportObject();
	ThreadLocal<ExtentTest> threadtest = new ThreadLocal<ExtentTest>();
	ExtentTest test;
	@Override
	public void onTestStart(ITestResult result)
	{
		test = extent.createTest(result.getMethod().getMethodName());
		threadtest.set(test);
	}
	@Override
	public void onTestSuccess(ITestResult result)
	{
		threadtest.get().log(Status.PASS, "Test Passed.");
	}
	@Override
	public void onTestFailure(ITestResult result)
	{
		threadtest.get().fail(result.getThrowable());
		try {
		
		driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		} 
		String filePath = null;
		//driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		try {
			filePath = TakeScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		threadtest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	
	}
	@Override
	public void onTestSkipped(ITestResult result)
	{
		
	}
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result)
	{
		
	}
	@Override
	public void onTestFailedWithTimeout(ITestResult result)
	{
		 onTestFailure(result);
	}
	@Override
	public void onStart(ITestContext context)
	{
		
	}
	@Override
	public void onFinish(ITestContext context)
	{
		extent.flush();
	}

}
