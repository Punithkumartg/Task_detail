package Library;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.propertyReader;

import java.util.Collections;
import java.util.Properties;

public class testBase {
    private Browser browser;
    private BrowserContext context;
    private Page page;
    private propertyReader propertyreader;
    private Properties properties;
    private Properties scanprop;

    Playwright playwright = Playwright.create();

    @BeforeSuite
    public void launchApplication() {
        propertyreader = new propertyReader();
        properties = propertyreader.readConfigProperty();
        scanprop=propertyreader.readscansproperties();
        String URL = properties.getProperty("Application_URL");
        String browsertolaunch = properties.getProperty("browser");

        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setChannel(browsertolaunch)
                .setArgs(Collections.singletonList("--start-maximized")));
        context = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(null));
        page = context.newPage();
        page.navigate(URL);

    }

    @AfterSuite
    public void tearDownApplication()
    {
        page.close();
        page.context().clearCookies();
        context.close();
        browser.close();
        playwright.close();
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
    protected Properties getProperties(){
        return properties;
    }
    protected Properties getscanProperties(){return scanprop;}
}
