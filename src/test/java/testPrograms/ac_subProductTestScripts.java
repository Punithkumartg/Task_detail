package testPrograms;

import Library.testBase;
import Pages.loginPage;
import Pages.productPage;
import Pages.subProductPage;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import customListener.ExtentReport;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Properties;

public class ac_subProductTestScripts extends testBase {
    private Page page;
    private BrowserContext context;
    private Browser browser;
    private Properties configproperties;
    private Properties productproperties;

    private loginPage loginpage;
    private subProductPage subproductpage;

    @BeforeMethod
    public void setup() {
        this.page = super.getPage();
        this.context = super.getContext();
        this.browser = super.getBrowser();
        this.configproperties = super.getcofigProperties();
        this.productproperties = super.getProductproperties();


        loginpage = new loginPage(page);
        subproductpage = new subProductPage(page);
    }

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

    @ExtentReport(name = "sub product")
    @Test(testName = "Navigate to Sub product page" +
            "then verify all elements is displayed",
            priority = 1)
    public void navigateToSubProductPage() {
        subproductpage.navigateToSubproductPage();
        PlaywrightAssertions.assertThat(
                subproductpage.verifySearchfieldDisplayed()).isVisible();
        PlaywrightAssertions.assertThat(
                subproductpage.verifyNewSubproductdisplayed()).isVisible();
    }

    @ExtentReport(name = "sub product")
    @Test(testName = "Verify user is able to create sub products",
            priority = 2)
    public void createSubproducts() {
        subproductpage.clickOnNewSubproductBtn();
        subproductpage.selctParentProduct(
                productproperties.getProperty("Product_Dependency"));
        subproductpage.enterSubproductName(
                productproperties.getProperty("Subproduct_name"));
        subproductpage.enterSubproductDescription(
                productproperties.getProperty("Subproduct_description"));
        subproductpage.clickOnSubproductTypedropdwon();
        subproductpage.selectSubproductType(
                productproperties.getProperty("Subproduct_Type"));
        subproductpage.clickOnSubproductStatusdropdwon();
        subproductpage.selectSubproductStatus(
                productproperties.getProperty("Subproduct_status"));
        subproductpage.selectSubProductVno(
                productproperties.getProperty("Subproduct_Vno"));
        subproductpage.clickOnSubproductTierDropdown();
        subproductpage.selectSubproductTier(
                productproperties.getProperty("Subproduct_tier"));
        subproductpage.clivcOnSubprodtSaveBtn();
        subproductpage.clickOnClosePopup();
    }

    @ExtentReport(name = "maddy")
    @Test(testName = "Verify the behaviour of Owner option in Edit " +
            "Subproduct popUp window",
            priority = 3)
    public void ownerBehaviourInSubproduct() {
        subproductpage.clickOnEditBtn();
        subproductpage.clickOnOwnersOption();
    }
}
