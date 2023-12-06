package Pages;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class productPage {
    private Page page;
    private Browser browser;
    private BrowserContext context;

    public productPage(Page page) {
        this.page = page;
    }
    public void navigateToProductPage(){
        page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions()
                .setName("Products").setExact(true)).click();
    }
}
