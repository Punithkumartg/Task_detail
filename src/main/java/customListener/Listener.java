package customListener;


import com.aventstack.extentreports.*;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.*;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.DocumentException;
import org.codehaus.groovy.control.CompilationFailedException;
import org.testng.*;
import org.xhtmlrenderer.pdf.ITextRenderer;
import com.itextpdf.text.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Listener implements ITestListener {

    private static ExtentReports extent = createExtentReport();
    private static ExtentHtmlReporter htmlreporter;
    private ExtentTest test;
    private Document document;

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
            test.createNode(extentReport.name())
                    .info(MarkupHelper.createTable(getTestInformationTable(extentReport, result)));
        }
        try {
            document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
            document.open();
        } catch (Exception e) {
            e.printStackTrace();
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
    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, "Test SKipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();

    }

    private String[][] getTestInformationTable(ExtentReport extentReport, ITestResult result) {
        String[][] table = {
                {"Browser", extentReport.browser()},

        };
        return table;
    }





}
