package generic;

import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class DeleteThis {

	public static void main(String[] args) throws IOException {
		//create extent report
		ExtentReports e=new ExtentReports();
		//create extent report type and attach it to extent report
		ExtentHtmlReporter h=new ExtentHtmlReporter("./res/Report.html");
		e.attachReporter(h);
		//create test and say pass/fail
		ExtentTest test=e.createTest("ValidLogin");
		test.pass("This test is pass");
		//create test and say pass/fail
		ExtentTest test2=e.createTest("InValidLogin");
		test2.info("Here also we have different methods like we have in log4j");
		test2.warning("warning message...like log4j");
		test2.fail("This test is fail");
		
		File f=new File("./photo/ForFailure/login.png");
		String p=f.getAbsolutePath();
		test2.addScreenCaptureFromPath(p);
	  // finally publish the report	
		e.flush();
	}
}
