package Pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;

import java.util.List;

public class subProductPage {
    private Page page;
    private Browser browser;
    private BrowserContext context;


    public subProductPage(Page page) {
        this.page = page;
    }

    public void navigateToSubproductPage() {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions()
                .setName("Subproducts")).click();
    }

    public Locator verifySearchfieldDisplayed() {
        return page.getByPlaceholder("Search", new Page.GetByPlaceholderOptions()
                .setExact(true));
    }

    public Locator verifyNewSubproductdisplayed() {
        return page.locator("#root").getByRole(AriaRole.BUTTON).nth(2);
    }

    public void clickOnNewSubproductBtn() {
        page.locator("#root").getByRole(AriaRole.BUTTON).nth(2).click();
    }

    public void selctParentProduct(String productname) {
        page.getByLabel("Parent Product").click();
        List<ElementHandle> products = page.querySelectorAll(".rc-virtual-list-holder-inner>div>div");
        for (ElementHandle parentProduct : products) {
            String p_parent = parentProduct.innerText();
            System.out.println(p_parent);
            if (p_parent.equalsIgnoreCase(productname)) {
                parentProduct.click();
            }
        }
    }

    public void enterSubproductName(String name) {
        page.getByLabel("Subproduct Name").fill(name);
    }

    public void enterSubproductDescription(String description) {
        page.getByLabel("Add Subproduct").getByRole(
                AriaRole.PARAGRAPH).fill(description);
    }

    public void clickOnSubproductTypedropdwon() {
        page.locator("span").filter(new Locator.FilterOptions()
                .setHasText("Subproduct Type")).click();
    }

    public void selectSubproductType(String subproductType) {
        List<ElementHandle> subproductypes = page.querySelectorAll(".rc-virtual-list-holder-inner>div>div");
        for (ElementHandle subproducts : subproductypes) {
            String subproduct = subproducts.innerText();
            if (subproduct.equalsIgnoreCase(subproductType)) {
                subproducts.click();
            }
        }
    }

    public void selectSubProductVno(String subproduct_Vno) {
        page.getByLabel("Version Number").fill(subproduct_Vno);
    }

    public void clickOnSubproductTierDropdown() {
        page.getByLabel("Tier").click();
    }

    public void selectSubproductTier(String subproduct_tier) {
        List<ElementHandle> tierOptions = page.querySelectorAll(".rc-virtual-list-holder-inner>div");
        for (ElementHandle tiers : tierOptions) {
            String Tier = tiers.innerText();
            if (Tier.equalsIgnoreCase(subproduct_tier)) {
                tiers.click();
            }
        }
    }

    public void selectSubproductStatus(String status) {
        List<ElementHandle> statusTypes = page.querySelectorAll(".rc-virtual-list-holder-inner>div");
        for (ElementHandle statuses : statusTypes) {
            String Status = statuses.innerText();
            if (Status.equalsIgnoreCase(status)) {
                statuses.click();
            }
        }
    }

    public void clickOnSubproductStatusdropdwon() {
        page.getByTitle("Active").click();
    }

    public void clivcOnSubprodtSaveBtn() {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
                .setName("Skip & Save")).click();
    }

    public void clickOnSubproductNextBtn() {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
                .setName("Next")).click();
    }

    public void verifyCreatedPopupdisplayed() {
        Locator projectcreatedPopup = page.locator(".ant-message-notice-content");
        PlaywrightAssertions.assertThat(projectcreatedPopup).isVisible();
    }
    public void clickOnClosePopup() {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
                .setName("Close")).click();
    }
    public void clickOnEditBtn(){
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions()
                .setName("Edit")).click();
    }
    public void clickOnOwnersOption(){
        page.getByRole(AriaRole.TAB, new Page.GetByRoleOptions()
                .setName("Teams/Owners")).click();
    }
    public void clickOnTeamDropdown(){
        page.getByLabel("Team", new Page.GetByLabelOptions()
                .setExact(true)).click();
    }
    public void clickOnAddTeamBtn(){
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
                .setName("Add Team")).click();
    }
}
