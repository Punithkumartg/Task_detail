package testPrograms;

import Library.testBase;
import Pages.loginPage;
import Pages.productPage;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Properties;

public class armocodescripts extends testBase {

    private Page page;
    private BrowserContext context;
    private Browser browser;
    private Properties properties;

    private loginPage loginpage;
    private productPage productpage;

    @BeforeMethod
    public void setup() {
        this.page = super.getPage();
        this.context = super.getContext();
        this.browser = super.getBrowser();
        this.properties = super.getProperties();

        loginpage = new loginPage(page);
        productpage = new productPage(page);
    }

    @Test(description = "Verify when user login with valid credentiasls" +
            "then Home page is displayed",
            priority = 0)
    public void logintoApplication() {
        loginpage.clickonMicrosoftBtn();
        loginpage.enterUsername(properties.getProperty("username"));
        loginpage.clickOnNextBtn();
        loginpage.enterPassword(properties.getProperty("password"));
        loginpage.clickOnSigninBtn();
        loginpage.clickOnYesBtn();
        PlaywrightAssertions.assertThat(page)
                .hasTitle("ArmorCode Inc - Application Security Orchestration and Collaboration platform");
    }

    @Test(description = "Navigate to poducts page then verify " +
            "all the elements is displayed",
            priority = 1)
    public void navigateToProductPage(){
        productpage.navigateToProductPage();
    }
}
