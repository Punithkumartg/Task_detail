package customListener;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener {
    private ExtentReports extent;
    private ExtentTest test;

    @Override
    public void onStart(ITestContext context)
    {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("test-output/extent-report1.html");
        htmlReporter.config().setDocumentTitle("Armorcode_project");
        htmlReporter.config().setReportName("Armorcode_excuetion_report");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        ExtentSparkReporter extentSparkReporter=new ExtentSparkReporter("test-output/spark-report1.html");
        extent.attachReporter(extentSparkReporter);
    }

    @Override
    public void onFinish(ITestContext context)
    {
        extent.flush();
    }

    @Override
    public void onTestStart(ITestResult result)
    {
        test = extent.createTest(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test sucessful");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, "Test failure");
        test.log(Status.FAIL, result.getThrowable());
        test.log(Status.FAIL, "Test '" + result.getName() + "' has failed");
        test.log(Status.FAIL, "Class: " + result.getTestClass().getName());
        test.log(Status.FAIL, "Method: " + result.getMethod().getMethodName());
        test.log(Status.FAIL, "Timestamp: " + result.getEndMillis());
        /*

         */
    }

    @Override
    public void onTestSkipped(ITestResult result)
    {
        test.log(Status.SKIP, "Test '" + result.getName() + "'has been skipped");
        test.log(Status.SKIP, "Class: " + result.getTestClass().getName());
        test.log(Status.SKIP, "Method: " + result.getMethod().getMethodName());
        test.log(Status.SKIP, "Timestamp: " + result.getEndMillis());
    }
}