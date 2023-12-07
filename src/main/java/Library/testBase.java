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

        Playwright playwright = Playwright.create();

    @BeforeSuite
        public void launchApplication() {
            propertyreader = new propertyReader();
            properties = propertyreader.readConfigProperty();
            String URL = properties.getProperty("url");


            browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false)
                    .setChannel("chrome")
                    .setArgs(Collections.singletonList("--start-maximized")));
            context = browser.newContext(new Browser.NewContextOptions()
                    .setViewportSize(null));
            page = context.newPage();
            page.navigate(URL);
        }


@AfterSuite
        public void tearDownApplication() {
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
        protected Properties getProperties(){
            return properties;
        }
}
