package rahul.Testcomponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
//to rerun flaky tests,i.e ensure true failure
	int count=0;
	int maxtry=1;
	@Override
	public boolean retry(ITestResult result) {
		if(count<maxtry) {
			count++;
			return true;
		}
		return false;
	}
	

}
