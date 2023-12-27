package Pages;

import com.microsoft.playwright.Download;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import org.testng.Assert;
import utils.propertyReader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

public class scanspage {
    private final Page page;
    private propertyReader propertyreader;
    private Properties scanprop;

    public void prop() {
        propertyreader = new propertyReader();
        scanprop = propertyreader.readscansproperties();
    }
    public void navigate_to_scanstab() {
        //  page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Findings")).click();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Scans")).click();
    }

    public void clear_filter() {
        page.locator("#filter-button-findings > div > svg").click();
    }
    public void total_newfilter() {
        page.locator("//span[.='Total/Duplicate/Resolved/New Findings ']/..//span[@role='button']")
                .click();
    }
    public void environment_ascending() {
        page.locator("//span[.='Environment']//span[@aria-label='caret-up']").click();
        ////span[.='Product']//span[@aria-label='caret-up']
    }
    public void environment_descending() {
        Locator lo = page.locator("//span[.='Environment']//span[@aria-label='caret-down']");
        lo.click();
    }
    public String scan_id;
    public String scanid_data() {
        scan_id = page.getByRole(AriaRole.ROW).locator(".ant-table-row ").getAttribute("data-row-key");
        return scan_id;
    }
    public void upload_btn() {
//        page.getByText("Upload Scan").click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Upload Scan")).click();
    }
    public void close_upload_pop() {
        page.getByLabel("close", new Page.GetByLabelOptions().setExact(true)).click();
    }

    public void product_dd() {
        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("product")).first().click();
    }


    public void subproduct_dd() {
        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("subProduct")).first().click();
    }

    public void environment_dd() {
        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions()
                .setName("environment")).first().click();
    }

    public void environment_opt() {
        page.locator(".rc-virtual-list-holder-inner>div").
                filter(new Locator.FilterOptions().setHasText("Production")).click();
    }

    public void scan_tool_dd() {
        page.getByLabel(" Scan Tool").click();
    }

    public void upload_scan_dd(String element) {
        // page.waitForSelector(".rc-virtual-list-holder-inner>div[title]");
        try {
            Thread.sleep(500);
        } catch (Exception e) {
        }
        List<ElementHandle> dd_list =
                page.querySelectorAll(".rc-virtual-list-holder-inner>div[title]");
        for (ElementHandle dd_opt : dd_list) {
            String option = dd_opt.getAttribute("title");
            if (option.equalsIgnoreCase(element)) {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                }
                dd_opt.click();
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                }
            }
        }
    }

    public void fileupload(String file) {
        page.querySelector("input[type='file']")
                .setInputFiles(Paths.get(file));
    }

    public void upload_submit() {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Upload")
                .setExact(true)).click();
    }

    public void upload_scan_assert() {
        String s_id = page.locator(".ant-table-tbody>tr:nth-child(2)")
                .getAttribute("data-row-key");
        System.out.println(s_id);
        PlaywrightAssertions.assertThat(page.locator(".ant-table-tbody>tr:nth-child(2)>td:nth-child(6)"))
                .hasText(scanprop.getProperty("tool"));
        PlaywrightAssertions.assertThat(page.locator
                        (".ant-table-tbody>tr:nth-child(2)>td:nth-child(10)>div>img"))
                .hasAttribute("src", "images/Finished.png");

    }

    public void three_dots() {
        page.locator(".ant-table-row:nth-child(2) > td:nth-child(15)>button").click();
    }

    public void download_file() {
        String downloadPath = "src/main/resources/download/";
        Locator extentreport = page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("Export Report"));
        extentreport.hover();
        page.mouse().down();
        String text = page.getByRole(AriaRole.TOOLTIP, new Page.GetByRoleOptions().setName("Export Report")).innerText();
        System.out.println(text);
        text = text.replace(':', ' ');
        Download download = page.waitForDownload(() ->
        {
            extentreport.click();
        });
//        Path filePath = download.path();
//        System.out.println("Downloaded file path: " + filePath);
        download.saveAs(Paths.get(downloadPath + text));
        Path finalpath = Path.of(downloadPath, text);
        Assert.assertTrue(Files.exists(finalpath), text);
    }

    public String data_rowkey;

    public void getdata_row_id() {
        data_rowkey = page.locator(".ant-table-tbody>tr:nth-child(2)").getAttribute("data-row-key");
    }

    public void resultdetail() {
        page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("Result Details")).click();
    }
    public void close_btn_hover() {
        page.locator("#filter-button-findings >div> svg").hover();
    }
    public void scan_id_hover() {
        Locator scanid = page.getByText("Scan ID", new Page.GetByTextOptions().setExact(true));
        scanid.hover();
        PlaywrightAssertions.assertThat(page.getByRole(AriaRole.TOOLTIP,
                new Page.GetByRoleOptions().setName("Click to sort in ascending"))).isVisible();
        scanid.click();
        scanid.hover();
        PlaywrightAssertions.assertThat(page.getByRole(AriaRole.TOOLTIP,
                new Page.GetByRoleOptions().setName("Click to sort in descending"))).isVisible();
        scanid.click();
        scanid.hover();
        PlaywrightAssertions.assertThat(page.getByRole(AriaRole.TOOLTIP,
                new Page.GetByRoleOptions().setName("Click to cancel sorting"))).isVisible();
    }
    public void scan_date() {
        page.getByText("Scan Date").locator("svg").hover();
    }
    public void total_hover() {
        page.getByText("Total/Duplicate/Resolved/New Findings ").locator("svg").nth(1).hover();
    }
    public void reset_filter_hover() {
        page.locator(".ant-card-body >div .ant-space-item:nth-child(7) span").hover();
    }
    public void additional_columnhover() {
        page.locator(".ant-card-body >div .ant-space-item:nth-child(9) span").hover();
    }
    public void export_report_hover() {
        page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("Export Report")).hover();

    }
    public void refreshbtn() {
        page.getByTitle("Refresh").click();
    }
    public void hoveron_crossmark() {
        String cross = ".ant-table-tbody>tr:nth-child(2)>td:nth-child(10)>div>img[src='images/Failed.png']";
        page.waitForSelector(cross);
        page.locator(cross).hover();
    }
    public void errormsg_scanfail() {
        String txt = page.getByRole(AriaRole.TOOLTIP, new Page.GetByRoleOptions().setName("Scan failed :")).innerText();
    }
    public void pageoption_click() {
        page.locator(".ant-pagination-options div .ant-select-selector").click();
    }
    public void pagination_option() {
        List<ElementHandle> pagination_option = page.querySelectorAll(".rc-virtual-list-holder-inner div[title]");
        for (ElementHandle options : pagination_option) {
            options.click();
            String title = options.getAttribute("title");
            page.locator(".ant-checkbox input").first().click();
            if (title.equals("10 / page")) {
                PlaywrightAssertions.assertThat(page.getByText("Selected ")).hasText("Selected 10");
            } else if (title.equals("20 / page")) {
                PlaywrightAssertions.assertThat(page.getByText("Selected ")).hasText("Selected 20");
            } else if (title.equals("50 / page")) {
                PlaywrightAssertions.assertThat(page.getByText("Selected ")).hasText("Selected 50");
            } else if (title.equals("100 / page")) {
                PlaywrightAssertions.assertThat(page.getByText("Selected ")).hasText("Selected 100");
            }
            page.locator(".ant-pagination-options div .ant-select-selector").click();
        }
    }
    public scanspage(Page page) {
        this.page = page;
    }
}
