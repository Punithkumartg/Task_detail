package testPrograms;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.options.AriaRole;
import customListener.ExtentReport;
import Library.testBase;
import Pages.loginPage;
import Pages.scanspage;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Properties;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ac_scansTestscripts extends testBase {
    private Page page;
    private BrowserContext context;
    private Browser browser;
    private Properties properties;
    private Properties scanprop;

    private loginPage loginpage;
    private scanspage scans;


    @BeforeMethod
    public void setup() {
        this.page = super.getPage();
        this.context = super.getContext();
        this.browser = super.getBrowser();
        this.properties = super.getProperties();
        this.scanprop = super.getscanProperties();
        loginpage = new loginPage(page);
        scans = new scanspage(page);


    }
    @ExtentReport(name = "Punith")
    @Test(description = "Verify when user login with valid credentiasls" +
            "then Home page is displayed",
            priority = 1)
    public void logintoApplication() {
        loginpage.clickonMicrosoftBtn();
        loginpage.enterUsername(properties.getProperty("username"));
        loginpage.clickOnNextBtn();
        loginpage.enterPassword(properties.getProperty("password"));
        loginpage.clickOnSigninBtn();
        loginpage.clickOnYesBtn();
        PlaywrightAssertions.assertThat(page)
                .hasTitle("ArmorCode Inc - Application Security Orchestration and Collaboration platform");
        scans.navigate_to_scanstab();
    }
    @ExtentReport(name = "Punith")
    @Test(description = "check Result details"+"navigate to finding page and same scan id findings should be there"
            ,priority = 2 , enabled = false)
    public void result_details()
    {
        scans.three_dots();
        scans.getdata_row_id();
        scans.resultdetail();
        PlaywrightAssertions.assertThat(page.locator("#filter-button-scan>div span:first-child")).hasText(scans.data_rowkey);
        scans.navigate_to_scanstab();

    }
    @ExtentReport(name = "Punith")
    @Test(description = "check Export Report "+"file should be downloaded",
            priority = 3,enabled = false)
    public void exportreport() {
        scans.three_dots();
        scans.download_file();
    }
    @ExtentReport(name = "Punith")
    @Test(description = "navigate to scans page and clear filter" + "then filter should be removed",
            priority = 4, enabled =false )
    public void clear_filter() {
         scans.clear_filter();
        scans.total_newfilter();
        assertThat(page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions()
            .setName("New > 0"))).isChecked(new LocatorAssertions.IsCheckedOptions().setChecked(false));

    }

    @ExtentReport(name = "Punith")
    @Test(description = "Sorting functinality" + "should sort according to condition",
            priority = 5, enabled = false)
    public void sorting() {
        scans.environment_ascending();
        assertThat(page.getByText("Production").first()).isVisible();
        scans.environment_descending();
        assertThat(page.getByText("Staging").first()).isVisible();

    }

    @ExtentReport(name = "Punith")
    @Test(description = "upload popup validate" + "scan upload popup should be displayed",
            priority = 6, enabled = false)
    public void upload_check() {
        scans.upload_btn();
        assertThat(page.locator(".ant-modal-title")).containsText("Scan Upload");
        scans.close_upload_pop();

    }

    @ExtentReport(name = "Punith")
    @Test(description = "upload valid file" + "file should be upload and tick mark should be displayed",
            priority = 7, enabled = false)
    public void upload_validfile() {
        scans.upload_btn();
        scans.product_dd();
        scans.upload_scan_dd(scanprop.getProperty("Product"));
        scans.subproduct_dd();
        scans.upload_scan_dd(scanprop.getProperty("sub_product"));
        scans.environment_dd();
        scans.environment_opt();
        scans.scan_tool_dd();
        scans.upload_scan_dd(scanprop.getProperty("tool"));
        scans.fileupload(scanprop.getProperty("valid_file"));
        scans.upload_submit();
        assertThat(page.getByText("a few seconds ago").first()).isVisible();
        PlaywrightAssertions.assertThat(page.locator(".ant-table-tbody>tr:nth-child(2)>td:nth-child(6)"))
                .hasText(scanprop.getProperty("tool"));
        assertThat(page.locator(".ant-table-tbody>tr:nth-child(2)>td:nth-child(14)"))
                .hasText("madhusudhan.kv@3ktechnologies.com");
        page.waitForSelector(".ant-table-tbody>tr:nth-child(2)>td:nth-child(10)>div>img[src='images/Finished.png']");
        PlaywrightAssertions.assertThat(page.locator
                        (".ant-table-tbody>tr:nth-child(2)>td:nth-child(10)>div>img"))
                .hasAttribute("src", "images/Finished.png");


    }

    @ExtentReport(name = "Punith")
    @Test(description = "without product & subproduct  " + "error message should be displayed",
            priority = 8, enabled = false)
    public void scanupload_mandatory() {
        scans.upload_btn();
        scans.environment_dd();
        scans.environment_opt();
        scans.scan_tool_dd();
        scans.upload_scan_dd(scanprop.getProperty("tool"));
        scans.fileupload(scanprop.getProperty("invalid_file"));
        scans.upload_submit();
        assertThat(page.getByText("Select a product name.")).isVisible();
        assertThat(page.getByText("Select a subproduct name.")).isVisible();
        scans.close_upload_pop();
    }

    @ExtentReport(name = "Punith")
    @Test(description = "without environment & file " + "error message should be displayed",
            priority = 9, enabled = false)
    public void scanupload_mandatory_env() {
        scans.upload_btn();
        scans.product_dd();
        scans.upload_scan_dd(scanprop.getProperty("Product"));
        scans.subproduct_dd();
        scans.upload_scan_dd(scanprop.getProperty("sub_product"));
        scans.scan_tool_dd();
        scans.upload_submit();
        assertThat(page.getByText("Select an environment name.")).isVisible();
        assertThat(page.getByText("Please upload file.")).isVisible();
        scans.close_upload_pop();
    }

    @ExtentReport(name = "Punith")
    @Test(description = "without tool  " + "error message should be displayed",
            priority = 10, enabled = false)
    public void scanupload_mandatory_tool() {
        scans.upload_btn();
        scans.product_dd();
        scans.upload_scan_dd(scanprop.getProperty("Product"));
        scans.subproduct_dd();
        scans.upload_scan_dd(scanprop.getProperty("sub_product"));
        scans.environment_dd();
        scans.environment_opt();
        scans.upload_scan_dd(scanprop.getProperty("tool"));
        scans.fileupload(scanprop.getProperty("invalid_file"));
        scans.upload_submit();
        assertThat(page.getByText("Select a scan tool.")).isVisible();
        scans.close_upload_pop();
    }

    @ExtentReport(name = "Punith")
    @Test(description = "all fields_error" + "error message should be displayed",
            priority = 11, enabled = false)
    public void scanupload_mandatory_all() {
        scans.upload_btn();
        scans.upload_submit();
        assertThat(page.getByText("Select a product name.")).isVisible();
        assertThat(page.getByText("Select a subproduct name.")).isVisible();
        assertThat(page.getByText("Select an environment name.")).isVisible();
        assertThat(page.getByText("Select a scan tool.")).isVisible();
        assertThat(page.getByText("Please upload file.")).isVisible();
        scans.close_upload_pop();
    }

    @ExtentReport(name = "Punith")
    @Test(description = "upload invalid file" + "cross marak should be displayed",
            priority =12, enabled = false)
    public void upload_invalidfile() {
        scans.upload_btn();
        scans.product_dd();
        scans.upload_scan_dd(scanprop.getProperty("Product"));
        scans.subproduct_dd();
        scans.upload_scan_dd(scanprop.getProperty("sub_product"));
        scans.environment_dd();
        scans.environment_opt();
        scans.scan_tool_dd();
        scans.upload_scan_dd(scanprop.getProperty("tool"));
        scans.fileupload(scanprop.getProperty("invalid_file"));
        scans.upload_submit();
        assertThat(page.getByText("a few seconds ago").first()).isVisible();
        PlaywrightAssertions.assertThat(page.locator(".ant-table-tbody>tr:nth-child(2)>td:nth-child(6)"))
                .hasText(scanprop.getProperty("tool"));
        assertThat(page.locator(".ant-table-tbody>tr:nth-child(2)>td:nth-child(14)"))
                .hasText("madhusudhan.kv@3ktechnologies.com");
        page.waitForSelector(".ant-table-tbody>tr:nth-child(2)>td:nth-child(10)>div>img[src='images/Failed.png']");
        PlaywrightAssertions.assertThat(page.locator
                        (".ant-table-tbody>tr:nth-child(2)>td:nth-child(10)>div>img"))
                .hasAttribute("src", "images/Failed.png");
    }
    @ExtentReport(name = "Punith")
    @Test(description = "upload executable file" + "Error message should be displayed",
            priority =13, enabled = false)
    public void upload_exefile()
    {
        scans.upload_btn();
        scans.product_dd();
        scans.upload_scan_dd(scanprop.getProperty("Product"));
        scans.subproduct_dd();
        scans.upload_scan_dd(scanprop.getProperty("sub_product"));
        scans.environment_dd();
        scans.environment_opt();
        scans.scan_tool_dd();
        scans.upload_scan_dd(scanprop.getProperty("tool"));
        scans.fileupload(scanprop.getProperty("exe_file"));
        scans.upload_submit();
        PlaywrightAssertions.assertThat(page.getByText("invalid format")).containsText("file.exe is of invalid format");
        scans.close_upload_pop();
    }

    @ExtentReport(name = "Punith")
    @Test(description = "Tooltip check"+"tool tip message should be displayed",priority = 14,enabled = false)
    public void tooltip()
    {
        scans.close_btn_hover();
        PlaywrightAssertions.assertThat(page.getByRole(AriaRole.TOOLTIP,new Page.GetByRoleOptions()
                .setName("Clear"))).isVisible();
        scans.scan_id_hover();
        scans.scan_date();
        PlaywrightAssertions.assertThat(page.getByRole(AriaRole.TOOLTIP,new Page.GetByRoleOptions()
                .setName("ArmorCode Ingestion Date"))).isVisible();
        scans.total_hover();
        PlaywrightAssertions.assertThat(page.getByRole(AriaRole.TOOLTIP,new
                Page.GetByRoleOptions().setName("Total = Duplicate + New"))).isVisible();
        scans.reset_filter_hover();
        PlaywrightAssertions.assertThat(page.getByRole(AriaRole.TOOLTIP,new
                Page.GetByRoleOptions().setName("Reset Filter and Sorting").setExact(true))).isVisible();
        scans.additional_columnhover();
        PlaywrightAssertions.assertThat(page.getByRole(AriaRole.TOOLTIP,new
                Page.GetByRoleOptions().setName("Additional columns"))).isVisible();


    }
    @ExtentReport(name = "Punith")
    @Test(description = "upload executable file" + "Error message should be displayed",
            priority =15, enabled = true)
    public void upload_emptyfile()
    {
        scans.upload_btn();
        scans.product_dd();
        scans.upload_scan_dd(scanprop.getProperty("Product"));
        scans.subproduct_dd();
        scans.upload_scan_dd(scanprop.getProperty("sub_product"));
        scans.environment_dd();
        scans.environment_opt();
        scans.scan_tool_dd();
        scans.upload_scan_dd(scanprop.getProperty("tool"));
        scans.fileupload(scanprop.getProperty("empty_file"));
        scans.upload_submit();
        assertThat(page.getByText("a few seconds ago").first()).isVisible();
        page.waitForSelector(".ant-table-tbody>tr:nth-child(2)>td:nth-child(10)>div>img[src='images/Failed.png']");
       String status=".ant-table-tbody>tr:nth-child(2)>td:nth-child(10)>div>img";
        String block="tbody tr:nth-child(2)>td:nth-child(9)";
        PlaywrightAssertions.assertThat(page.locator(block).getByText("T")).containsText("-");
        PlaywrightAssertions.assertThat(page.locator(block).getByText("D")).containsText("-");
        PlaywrightAssertions.assertThat(page.locator(block).getByText("R")).containsText("-");
        PlaywrightAssertions.assertThat(page.locator(block).getByText("N")).containsText("-");
    }
}