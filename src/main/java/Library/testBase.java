package Library;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import utils.propertyReader;

import java.util.Collections;
import java.util.Properties;

public class testBase {
    private Browser browser;
    private BrowserContext context;
    private Page page;
    private propertyReader propertyreader;
    private Properties configproperties;
    private Properties productproperties;

    Playwright playwright = Playwright.create();

    @BeforeClass
    public void launchApplication() {
        propertyreader = new propertyReader();
        configproperties = propertyreader.readConfigProperty();
        productproperties = propertyreader.readProductProperty();
        String URL = configproperties.getProperty("Application_URL");
        String browsertolaunch = configproperties.getProperty("browser");

        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setChannel(browsertolaunch)
                .setArgs(Collections.singletonList("--start-maximized")));
        context = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(null));
        page = context.newPage();
        page.navigate(URL);

    }

    @AfterClass
    public void tearDownApplication() {
        page.close();
        context.close();
        browser.close();
    }

    protected Page getPage() {
        return page;
    }

    protected Browser getBrowser() {
        return browser;
    }

    protected BrowserContext getContext() {
        return context;
    }
    protected Properties getcofigProperties(){
        return configproperties;
    }
    protected Properties getProductproperties(){
        return productproperties;
    }
}
