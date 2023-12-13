package testprograms;

import Library.testBase;
import Pages.globalfilter;
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

 private globalfilter globelfilter;


    @BeforeMethod
    public void setup() {
        this.page = super.getPage();
        this.context = super.getContext();
        this.browser = super.getBrowser();
        this.properties = super.getProperties();

        loginpage = new loginpage(page);
        globelfilter=new globalfilter(page);
    }

@Test(description = "Verify when the user logs in with valid credentials" +
            "then the Home page is displayed",
            priority = 1)
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
    @Test(description ="verify globelfilter should be displayed in topper ",priority=2)
    public void globalfilter()
    {

        globelfilter.globelfildisplayed();
          page.waitForTimeout(4000);
    }
    @Test(description = " verify Box having all BU, subproducts, team, tier and tool category should be seen",priority=2)
    public void fliterbar(){

           globelfilter.clickonfliter();
           globelfilter.testtrain();
           globelfilter.noneoption();
           globelfilter.teamoption();
           globelfilter.tieroption();
           globelfilter.buoption();
    }
     @Test(description = "Select any BU from the dropdown.",priority = 4)
    public void bubutton(){

         globelfilter.buoption();
         globelfilter.dropdown();
         page.waitForTimeout(1000);
         globelfilter.selecbu1();
         page.waitForTimeout(1000);


    }

    @Test(description = "Select any BU1 and defaculte business unit from the dropdown.",priority = 5)
    public void dorpdowds(){
        globelfilter.dropdown();
        page.waitForTimeout(2000);
        globelfilter.selectb2();
        globelfilter.dropdown();
        globelfilter.select3();


    }
    @Test(description = "verifiying the subprodut option",priority = 6)
    public void subprd(){

        globelfilter.team();
        page.waitForTimeout(1000);
        globelfilter.tire();
        page.waitForTimeout(1000);
        globelfilter.none();
    }
     @Test(description = "Check for teams under groupby after selecting products.",priority = 7)
    public void teams(){
           globelfilter.team();
           globelfilter.checkboxteams();
           page.waitForTimeout(2000);
           globelfilter.selectall_teams();
     }









}

