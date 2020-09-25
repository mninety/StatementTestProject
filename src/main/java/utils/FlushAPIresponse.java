package utils;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

public class FlushAPIresponse {
	
	@AfterMethod
	public void tearDown(ITestResult result) {

	   if (result.getStatus() == ITestResult.FAILURE) {
		   System.out.println(result.getMethod().getMethodName()+ " is failed");
	   }
	   else {
		   System.out.println(result.getMethod().getMethodName()+ " is passed");
	   }
	}

}
