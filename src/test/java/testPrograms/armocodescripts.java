package testPrograms;

import Library.testBase;
import Pages.loginPage;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class armocodescripts extends testBase {

    private Page page;
    private BrowserContext context;
    private Browser browser;

    private loginPage loginpage;

    @BeforeMethod
    public void setup() {
        this.page = super.getPage();
        this.context = super.getContext();
        this.browser = super.getBrowser();

        loginpage = new loginPage(page);
    }

    @Test(description = "login to application",
            priority = 0)
    public void logintoApplication() {
        loginpage.clickonMicrosoftBtn();
    }
}
