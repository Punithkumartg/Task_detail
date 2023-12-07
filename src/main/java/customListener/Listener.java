package customListener;


import com.aventstack.extentreports.*;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.*;
import com.lowagie.text.DocumentException;
import org.codehaus.groovy.control.CompilationFailedException;
import org.testng.*;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;

public class Listener implements ITestListener {

    private static ExtentReports extent = createExtentReport();
    private static ExtentHtmlReporter htmlreporter;
    private ExtentTest test;
    private static final String htmlpath = "test-output/ExtentReport.html";
    private static final String sparkpath = "test-output/ExtentSparkReport.html";
    private static final String pdfPath = "test-output/ExtentPdfReport.pdf";


    private static ExtentReports createExtentReport() {
        if (extent == null) {
            extent = new ExtentReports();
            htmlreporter = new ExtentHtmlReporter(htmlpath);
            extent.attachReporter(htmlreporter);
            ExtentSparkReporter extentsparkreporter = new ExtentSparkReporter(sparkpath);
            extent.attachReporter(extentsparkreporter);
        }
        return extent;
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        ExtentReport extentReport = result.getMethod().getConstructorOrMethod().getMethod()
                .getAnnotation(ExtentReport.class);
        if (extentReport != null) {
            test.assignCategory(extentReport.name());
            test.createNode(extentReport.name()).info(MarkupHelper.createTable(getTestInformationTable(extentReport, result)));
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, "Test failed");
        test.log(Status.FAIL, result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        generatePdfReport();
    }

    private String[][] getTestInformationTable(ExtentReport extentReport, ITestResult result) {
        String[][] table = {
                {"Browser", extentReport.browser()},
                {"Pass Percentage", getPassPercentage(result)},
                {"Fail Percentage", getFailPercentage(result)},
        };
        return table;
    }

    public String getPassPercentage(ITestResult result) {

        int totalTests = result.getTestContext().getAllTestMethods().length;
        int passedTests = result.getTestContext().getPassedTests().size();
        double passPercentage = (double) passedTests / totalTests * 100;
        return String.format("%.2f%%", passPercentage);
    }

    public String getFailPercentage(ITestResult result) {
        int totalTests = result.getTestContext().getAllTestMethods().length;
        int failedTests = result.getTestContext().getFailedTests().size();
        double failPercentage = (double) failedTests / totalTests * 100;
        return String.format("%.2f%%", failPercentage);
    }

    private void generatePdfReport() {
        try {
            File htmlFile = new File(htmlpath);
            File pdfFile = new File(pdfPath);

            if (!htmlFile.exists()) {
                throw new FileNotFoundException("HTML file not found in: " + htmlpath);
            }
            try (FileOutputStream fileOutputStream = new FileOutputStream(pdfFile)) {
                ITextRenderer iTextRenderer = new ITextRenderer();
                iTextRenderer.setDocument(htmlFile.toURI().toURL().toString());
                iTextRenderer.layout();
                iTextRenderer.createPDF(fileOutputStream, true);
                System.out.println("PDF report generated successfully: " + pdfPath);
            } catch (DocumentException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            System.err.println("Error while converting HTML to PDF: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error while converting HTML to PDF", e);
        }
    }
}