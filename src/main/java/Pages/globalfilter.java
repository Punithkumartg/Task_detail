package Pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import javax.xml.stream.events.EndElement;

public class globalfilter {
    private Page page;
    private Browser browser;
    private BrowserContext context;

    public globalfilter(Page page) {
        this.page = page;
    }

    public void globelfildisplayed() {
        ElementHandle globelfildisplayed = page.waitForSelector(".app-header-search-v2-suffix", new Page.WaitForSelectorOptions().setTimeout(10000));
        boolean isElementVisible = globelfildisplayed.isVisible();

        if (isElementVisible) {
            System.out.println("globel filter is displayed in topper");
        } else {
            System.out.println("globelfliter is not visible!");
        }
    }
   public void clickonfliter(){
      ElementHandle fliter= page.waitForSelector(".app-header-search-v2-suffix", new Page.WaitForSelectorOptions().setTimeout(10000));

    if (fliter!=null){
        fliter.click();
    System.out.println("element is clicked");
    }
else {
    System.out.println("element iss not clicked");
    }
   }
    public void  testtrain(){

       Locator test= page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("right Subproducts"));
        if (test != null) {
            test.click();
            System.out.println("subproduct clicked");
        } else {
            System.out.println("Element not found or not visible");
        }}
          public void noneoption() {
        Locator none = page.locator("label").filter(new Locator.FilterOptions().setHasText("None")).locator("span").first();

              if (none != null && none.isVisible()) {
                  System.out.println("none is visible");
              } else {
                  System.out.println("none is not displayed");
              }
          }
        public void teamoption(){

          Locator team=  page.locator(".ant-radio-button.ant-radio-button-checked");
         if (team!=null&& team.isVisible()){
             System.out.println("team is visible");
         }else {
             System.out.println("team is not visible");
         }

        }
        public void tieroption() {

            Locator teir = page.locator("label").filter(new Locator.FilterOptions().setHasText("Tier")).locator("span").first();
            if (teir != null && teir.isVisible()) {
                System.out.println("teir option is visible");
            } else {
                System.out.println("teir option is not visible");
            }
        }
        public void buoption(){
            page.getByText("OrganizationDefault Business UnitHierarchiesAllSubproductAllTool CategoryAll").click();

        }
        public void dropdown(){
//            page.getByRole(AriaRole.TOOLTIP).getBy("Developer").click();
         Locator dropdown=  page.locator("span[class='ant-select-selection-item'] div[class='ant-col ant-col-16 p-b-0 p-l-0 text-ellipsis']");
            if (dropdown!=null){
                dropdown.click();
                System.out.println("dropdown is visible and is clicked");
            }else {
                System.out.println("dropdown is not visible is not clicked");
            }


        }
        public void selecbu1(){
            Locator ele=page.getByText("Developer", new Page.GetByTextOptions().setExact(true)).nth(3);
            if (ele!=null){
                ele.click();
                System.out.println("bussiness unit is selected");
            }else {
                System.out.println("bussiness unitis not selected");
            }
        }

        public void selectb2(){
          Locator bu1=page.getByText("BU 1");
            if (bu1!=null){
                 bu1.click();
                System.out.println("bu1  is selected");
            }else {
                System.out.println("bu1 not selected");
            }
        }
        public  void select3(){

         Locator ele3=   page.getByText("Developer", new Page.GetByTextOptions().setExact(true)).nth(4);
            if (ele3!=null){
                ele3.click();
                System.out.println(" defacult bussiness unit is selected");
            }else {
                System.out.println(" defacult bussiness unitis not selected");
            }
        }
    public void subprd(){

        page.locator("ant-collapse-header").click();

    }
    public void team(){

       // page.waitForSelector("input[value='Team']").click();
        Locator teamInput =  page.locator("label").filter(new Locator.FilterOptions().setHasText("Team")).locator("span").first();
        if (teamInput != null) {
            teamInput.click();
            System.out.println("Clicked on Team input");
        } else {
            System.out.println("Team input not found within the specified timeout");
        }
    }

    public void none(){
        Locator teamInput2 =  page.locator("label").filter(new Locator.FilterOptions().setHasText("None")).locator("span").first();
        if (teamInput2 != null) {
            teamInput2.click();
            System.out.println("Clicked on none input");
        } else {
            System.out.println("Team input not found within the specified timeout");
        }


    }
    public void tire(){
        Locator teamInput =page.locator("label").filter(new Locator.FilterOptions().setHasText("Tier")).locator("span").first();
        if (teamInput != null) {
            teamInput.click();
            System.out.println("Clicked on tire input");
        } else {
            System.out.println("Team input not found within the specified timeout");
        }
    }

    public void checkboxteams(){
        Locator check=page.locator("//div[@class='ant-tree-list-holder-inner']//span[@class='ant-tree-checkbox']");
        for (int i=0;i<check.count();i++){
            check.nth(i).click();
        }
    }
    public void selectall_teams(){
        page.locator("Select All").click();


    }















          }