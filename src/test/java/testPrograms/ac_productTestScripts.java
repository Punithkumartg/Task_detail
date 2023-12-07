package testPrograms;

import Library.testBase;
import Pages.loginPage;
import Pages.productPage;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import customListener.ExtentReport;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
    @Test(description = "Verify when user login with valid credentiasls" +
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
    @Test(description = "Navigate to poducts page then verify " +
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
    @Test(description = "Verfy user is able to create products",
            priority = 2)
    public void createProducts() {
        Page page1 = page.waitForPopup(() -> {
            Page popupPage = context.pages().get(context.pages().size() - 1);
            productpage.clickOnAddNewProductBtn();
        });
        productpage = new productPage(page1);
        productpage.enterProdctName("madhu");
    }
}
