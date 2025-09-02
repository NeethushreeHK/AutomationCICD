package rahul.Testcomponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import academy.Resources.ExtentreporterNG;

public class Listeners extends Basetest implements ITestListener {//this is interface by testng, when we implement it we get methods like onteststart,ontestsuccess,ontestfailure
	//for execution easier
	ExtentReports extent=ExtentreporterNG.getreportobj();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal();//if we run concurrently,each obj have its own thread,without interupting
	//ontest start
	public void onTestStart(ITestResult result) {
		test=extent.createTest(result.getMethod().getMethodName()); 
		extentTest.set(test);// unique thread id is formed
	} 
	//on success
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test PASSED ALL OK");
	}
	//on failure
	public void onTestFailure(ITestResult result) {
		test.log(Status.FAIL, "Test NOT OK");
		//.get() retrieves the unique id
		extentTest.get().fail(result.getThrowable());//prints error msg
		//passing driver
		try {
			//fields are associated in class not method,hence cannot use methods to get the driver
			driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		//screenshot
		String filepath=null;
		try {
			 filepath=getscreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();//print inoutput
		}
		extentTest.get().addScreenCaptureFromPath(filepath,result.getMethod().getMethodName());
		
	}
	
	//on finish
	
	public void onFinish(ITestContext context) {
		extent.flush(); 
	}

}
