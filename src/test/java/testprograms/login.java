package testprograms;

import Library.testBase;
import Pages.loginpage;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Properties;

public class login extends testBase {
    private Page page;
    private BrowserContext context;
    private Browser browser;
    private Properties properties;

     private loginpage loginpage;




    @BeforeMethod
    public void setup() {
        this.page = super.getPage();
        this.context = super.getContext();
        this.browser = super.getBrowser();
        this.properties = super.getProperties();

        loginpage = new loginpage(page);
    }

    @Test(description = "Verify when the user logs in with valid credentials" +
            "then the Home page is displayed",
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
}
