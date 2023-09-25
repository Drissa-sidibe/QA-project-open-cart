package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testBase.BaseClass;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager implements ITestListener {
/* this is a utility class because every class can use it*/

    public ExtentSparkReporter sparkReporter;  // UI of the report
    public ExtentReports extent;  //populate common info on the report
    public ExtentTest test; // creating test case entries in the report and update status of the test methods
    String reportName;
    public void onStart(ITestContext context) {
        String timeStamp = new SimpleDateFormat("yyy.dd.HH.mm.ss").format(new Date());
        reportName="Test-Report-" + timeStamp + "html";
        sparkReporter=new ExtentSparkReporter(".\\reports\\" + reportName);

        sparkReporter.config().setDocumentTitle("Opencart Automation Report"); // TiTle of report
        sparkReporter.config().setReportName("Opencart Functional Testing"); // name of the report
        sparkReporter.config().setTheme(Theme.DARK);

        extent=new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Application","Opencart");
        extent.setSystemInfo("Module","Admin");
        extent.setSystemInfo("Sub Module","Customer");
        extent.setSystemInfo("Operating System",System.getProperty("os.name"));
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment","QA");

    }


    public void onTestSuccess(ITestResult result) {

        test = extent.createTest(result.getName()); // create a new entry in the report
        test.log(Status.PASS, "Test case PASSED is:" + result.getName()); // update status p/f/s

    }

    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getName());
        test.log(Status.FAIL, "Test case FAILED is:" + result.getName());
        test.log(Status.FAIL, "Test Case FAILED cause is: " + result.getThrowable());
        try {
            String imgPath = new BaseClass().captureScreen(result.getName());
            test.addScreenCaptureFromPath(imgPath);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getName());
        test.log(Status.SKIP, "Test Skipped");
        test.log(Status.SKIP, result.getThrowable().getMessage());

    }
    public void onFinish(ITestContext context) {
        extent.flush();
//        try {
//            URL url = new URL("file:///"+ System.getProperty("user.dir")+"\\reports\\"+reportName);
//            //create the email message
//            //ImageHtmlEmail email = new ImageHtmlEmail();
//        }
    }


}
