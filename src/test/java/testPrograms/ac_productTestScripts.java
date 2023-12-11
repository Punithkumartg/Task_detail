package testPrograms;

import Library.testBase;
import Pages.loginPage;
import Pages.productPage;
import com.beust.ah.A;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import customListener.ExtentReport;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Properties;

public class ac_productTestScripts extends testBase {

    private Page page;
    private BrowserContext context;
    private Browser browser;
    private Properties configproperties;
    private Properties productproperties;

    private loginPage loginpage;
    private productPage productpage;

    @BeforeMethod
    public void setup() {
        this.page = super.getPage();
        this.context = super.getContext();
        this.browser = super.getBrowser();
        this.configproperties = super.getcofigProperties();
        this.productproperties = super.getProductproperties();


        loginpage = new loginPage(page);
        productpage = new productPage(page);
    }

    @ExtentReport(name = "maddy")
    @Test(testName = "Verify when user login with valid credentiasls" +
            "then Home page is displayed",
            priority = 0)
    public void logintoApplication() {
        loginpage.clickonMicrosoftBtn();
        loginpage.enterUsername(configproperties.getProperty("username"));
        loginpage.clickOnNextBtn();
        loginpage.enterPassword(configproperties.getProperty("password"));
        loginpage.clickOnSigninBtn();
        loginpage.clickOnYesBtn();
        PlaywrightAssertions.assertThat(page)
                .hasTitle("ArmorCode Inc - Application Security Orchestration and Collaboration platform");
    }

    @ExtentReport(name = "maddy")
    @Test(testName = "Navigate to poducts page then verify " +
            "all the elements is displayed",
            priority = 1)
    public void navigateToProductPage() {
        productpage.navigateToProductPage();
        PlaywrightAssertions.assertThat(
                productpage.verifySearchfieldDisplayed()).isVisible();
        PlaywrightAssertions.assertThat(
                productpage.verifyNewProductBtnDislplayed()).isVisible();
    }

    @ExtentReport(name = "maddy")
    @Test(testName = "Verfy user is able to create products",
            priority = 2)
    public void createProducts() {
        productpage.clickOnAddNewProductBtn();
        productpage.enterProdctName(productproperties.getProperty("Product_name"));
        productpage.clickOnProductTypeDropdown();
        productpage.selectProductType(productproperties.getProperty("Product_Type"));
        productpage.enterDescription(productproperties.getProperty("Product_description"));
        productpage.clickOnStatusDropdown();
        productpage.selectStatus(productproperties.getProperty("Product_Status"));
        productpage.enterVersionNumber(productproperties.getProperty("Product_Vno"));
        productpage.clickOnTierdropdown();
        productpage.selectTierOption(productproperties.getProperty("Product_tier"));
        productpage.clickOnTags();
        productpage.clickOnAddNewTagBtn();
        productpage.enterTagKey(productproperties.getProperty("Product_TagKey"));
        productpage.enterTagValue(productproperties.getProperty("Product_TagValue"));
        productpage.saveTagValue();
        productpage.clickOnSaveBtn();
        productpage.verifyCreatedPopupdisplayed();
        PlaywrightAssertions.assertThat(page.getByTitle("playproduct").first())
                .hasText("playproduct");
    }

    @ExtentReport(name = "maddy")
    @Test(testName = "Verify user is able to create Sub product",
            priority = 3)
    public void createSubProduct() {
        productpage.clickNewSubproductBtn();
        productpage.enterNewSubproductName(
                productproperties.getProperty("Subproduct_name"));
        productpage.enterSubproductDescrptin(
                productproperties.getProperty("Subproduct_description"));
        productpage.clickOnSubproductTypedropdwon();
        productpage.selectSubproductType(
                productproperties.getProperty("Subproduct_Type"));
        productpage.clickOnSubproductStatusdropdwon();
        productpage.selectSubproductStatus(
                productproperties.getProperty("Subproduct_status"));
        productpage.clickOnSubproductVNo(
                productproperties.getProperty("Subproduct_Vno"));
        productpage.clickOnSubproductTierDropdown();
        productpage.selectSubproductTier(
                productproperties.getProperty("Subproduct_tier"));
        productpage.clivcOnSubprodtSaveBtn();
        PlaywrightAssertions.assertThat(productpage.verifySubproductCreated()).isVisible();
    }

    @ExtentReport(name = "maddy")
    @Test(testName = "Verify user is able to add new Dependency for the product",
            priority = 4)
    public void addNewDependency() {
        productpage.clickOnClosePopup();
        productpage.clickOnAddDependencyBtn();
        page.waitForLoadState();
        productpage.clickOnProductDependable();
        productpage.selectProductdependency();
        productpage.clickOnSubproductDependable();
        productpage.selectSubProductdependency();
        productpage.clickOndependencySaveBtn();
        Assert.assertTrue(page.locator(".ant-message-notice-content").isVisible());
    }

    @ExtentReport(name = "maddy")
    @Test(testName = "Verify user is able to delete added Dependency",
            priority = 5)
    public void deleteAddedDependency() {
        productpage.deleteDependency();
        productpage.dependencyDelete();
        productpage.dependencyDeleteBtn();
    }

    @ExtentReport(name = "maddy")
    @Test(testName = "verify user is able to delete added subproduct",
            priority = 6)
    public void deleteAddedSubproduct() {
        productpage.deleteSubproduct();
        productpage.subproductDelete();
        productpage.subproductDeleteBtn();
    }

    @ExtentReport(name = "maddy")
    @Test(testName = "Verify user is able to delete added product",
            priority = 7)
    public void deleteAddedProduct() {
        productpage.deleteProduct();
        productpage.productDelete();
        productpage.productDeleteBtn();
    }
}
