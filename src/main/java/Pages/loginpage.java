package Pages;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class loginpage  {
    private Page page;
    private Browser browser;
    private BrowserContext context;

    public loginpage (Page page)
    {
        this.page = page;
    }
    public void clickonMicrosoftBtn(){
        page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions()
                .setName("Sign in using Microsoft")).click();
    }
    public void enterUsername(String username){
        page.getByPlaceholder("Email or phone").fill("manoj.t@3ktechnologies.com");
    }
    public void clickOnNextBtn(){
        page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions()
                .setName("Next")).click();
    }
    public void enterPassword(String password){
        page.getByPlaceholder("Password").fill("Manu@1234");
    }
    public void clickOnSigninBtn(){
        page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions()
                .setName("Sign in")).click();
    }
    public void clickOnYesBtn(){
        page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions()
                .setName("Yes")).click();
    }

}
