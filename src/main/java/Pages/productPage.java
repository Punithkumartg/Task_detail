package Pages;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Locator;
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
    public Locator verifySearchfieldDisplayed(){
        return page.getByPlaceholder("Search",new Page.GetByPlaceholderOptions()
                .setExact(true));
    }
    public Locator verifyNewProductBtnDislplayed(){
       return page.getByRole(AriaRole.BUTTON).locator("svg").first();
    }
    public void clickOnAddNewProductBtn(){
        page.locator("button:nth-child(2)").first().click();
    }
    public void enterProdctName(String productName){
        page.querySelector("#name").fill(productName);
    }
}
