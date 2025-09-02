package academy.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentreporterNG {
	
	public static ExtentReports getreportobj() {
		String path=System.getProperty("user.dir")+"//reports//index.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter(path);//repsonsible to create html files and do config
		reporter.config().setReportName("Web automation reports");//changing report name
		reporter.config().setDocumentTitle("Test Results");//title on chrome
		
		ExtentReports extent=new ExtentReports();//attach the report created, it is responsible for consolidating the report
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Neethu");
		return extent;
		//extent.createTest(testname);//create entry to test
	}

}
