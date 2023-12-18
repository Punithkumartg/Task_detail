package Pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;

import java.util.List;

public class productPage {
    private Page page;
    private Browser browser;
    private BrowserContext context;


    public productPage(Page page) {
        this.page = page;
    }

    public void navigateToProductPage() {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions()
                .setName("Products").setExact(true)).click();
    }

    public Locator verifySearchfieldDisplayed() {
        return page.getByPlaceholder("Search", new Page.GetByPlaceholderOptions()
                .setExact(true));
    }

    public Locator verifyNewProductBtnDislplayed() {
        return page.getByRole(AriaRole.BUTTON).locator("svg").first();
    }

    public void clickOnAddNewProductBtn() {
        page.locator("button:nth-child(2)").first().click();
    }

    public void enterProdctName(String productName) {
        page.getByLabel("Name").fill(productName);
    }

    public void clickOnProductTypeDropdown() {
        page.getByTitle("Web Application").click();
    }

    public void selectProductType(String productType) {
        List<ElementHandle> productTypes = page.querySelectorAll(".rc-virtual-list-holder-inner>div>div");
        for (ElementHandle prducts : productTypes) {
            String product = prducts.innerText();
            if (product.equalsIgnoreCase(productType)) {
                prducts.click();
            }
        }
    }

    public void enterDescription(String description) {
        page.locator(".ql-editor").fill(description);
    }

    public void clickOnStatusDropdown() {
        page.getByTitle("Active").click();
    }

    public void selectStatus(String status) {
        List<ElementHandle> statusTypes = page.querySelectorAll(".rc-virtual-list-holder-inner>div");
        for (ElementHandle statuses : statusTypes) {
            String Status = statuses.innerText();
            if (Status.equalsIgnoreCase(status)) {
                statuses.click();
            }
        }
    }

    public void enterVersionNumber(String v_no) {
        page.getByLabel("Version Number").fill(v_no);
    }

    public void clickOnTierdropdown() {
        page.getByLabel("Tier").click();
    }

    public void selectTierOption(String tier) {
        List<ElementHandle> tierOptions = page.querySelectorAll(".rc-virtual-list-holder-inner>div");
        for (ElementHandle tiers : tierOptions) {
            String Tier = tiers.innerText();
            if (Tier.equalsIgnoreCase(tier)) {
                tiers.click();
            }
        }
    }

    public void clickOnClassDropdown() {
        page.getByTitle("Software only").click();
    }

    public void selectClass(String c_lass) {
        List<ElementHandle> classList = page.querySelectorAll(".rc-virtual-list-holder-inner>div>div");
        for (ElementHandle classes : classList) {
            String classText = classes.innerText();
            if (classText.equalsIgnoreCase(classText)) {
                classes.click();
            }
        }
    }

    public void clickOnTags() {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
                .setName("Tags")).click();
    }

    public void clickOnAddNewTagBtn() {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
                .setName("Add New")).click();
    }

    public void enterTagKey(String tagKey) {
        page.locator("textarea[name=\"key_0\"]").fill(tagKey);
    }

    public void enterTagValue(String tagValue) {
        page.locator("textarea[name=\"value_0\"]").fill(tagValue);
    }

    public void saveTagValue() {
        page.getByRole(AriaRole.ROW, new Page.GetByRoleOptions()
                .setName("*")).locator("svg").nth(1).click();
    }

    public void clickOnSaveBtn() {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
                .setName("Skip & Save")).click();
    }

    public void verifyCreatedPopupdisplayed() {
        Locator projectcreatedPopup = page.locator(".ant-message-notice-content");
        PlaywrightAssertions.assertThat(projectcreatedPopup).isVisible();
    }

    public void clickNewSubproductBtn() {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
                .setName("New Subproduct")).click();
    }

    public void enterNewSubproductName(String subproductname) {
        page.getByLabel("Name").fill(subproductname);
    }

    public void enterSubproductDescrptin(String subproduct_descrptn) {
        page.locator(".ql-editor").fill(subproduct_descrptn);
    }

    public void clickOnSubproductTypedropdwon() {
        page.locator("span").filter(new Locator.FilterOptions()
                .setHasText("Subproduct Type")).click();
    }

    public void clickOnSubproductStatusdropdwon() {
        page.getByTitle("Active").click();
    }

    public void clickOnSubproductVNo(String subproduct_Vno) {
        page.getByLabel("Version Number").fill(subproduct_Vno);
    }

    public void clickOnSubproductTierDropdown() {
        page.getByLabel("Tier").click();
    }

    public void clivcOnSubprodtSaveBtn() {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
                .setName("Skip & Save")).click();
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

    public void selectSubproductStatus(String status) {
        List<ElementHandle> statusTypes = page.querySelectorAll(".rc-virtual-list-holder-inner>div");
        for (ElementHandle statuses : statusTypes) {
            String Status = statuses.innerText();
            if (Status.equalsIgnoreCase(status)) {
                statuses.click();
            }
        }
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

    public Locator verifySubproductCreated() {
        return page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions()
                .setName("playsub_product"));
    }

    public void clickOnClosePopup() {
        Locator closePopup = page.getByText("Close").first();
        if (closePopup.isVisible()) {
            closePopup.click();
        }
    }

    public void clickOnAddDependencyBtn() {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
                .setName("New Dependency")).click();
    }

    public void clickOnProductDependable() {
        page.getByLabel("Product", new Page.GetByLabelOptions()
                .setExact(true)).click();
    }

    public void clickOnSubproductDependable() {
        page.getByLabel("Subproduct", new Page.GetByLabelOptions()
                .setExact(true)).click();
    }

    public void clickOndependencySaveBtn() {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
                .setName("Save")).click();
    }

    public void selectProductdependency() {
        page.getByText("play_depend").nth(1).click();
    }

    public void selectSubProductdependency() {
        page.getByText("sub_depend").click();
    }

    public void deleteDependency() {
        page.getByRole(AriaRole.ROW, new Page.GetByRoleOptions()
                .setName("sub_depend")).locator("svg").nth(4).click();
    }

    public void dependencyDelete() {
        page.getByPlaceholder("Delete").fill("Delete");
    }

    public void dependencyDeleteBtn() {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
                .setName("Delete")).click();
    }

    public void deleteSubproduct() {
        page.getByRole(AriaRole.ROW, new Page.GetByRoleOptions()
                .setName("playsub_product")).getByRole(AriaRole.BUTTON).click();
        page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions()
                .setName("Delete")).click();
    }

    public void subproductDelete() {
        page.getByPlaceholder("Delete").fill("Delete");
    }

    public void subproductDeleteBtn() {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
                .setName("Delete")).click();
    }

    public void deleteProduct() {
        page.locator("span").filter(new Locator.FilterOptions()
                .setHasText("Edit")).getByRole(AriaRole.BUTTON).click();
        page.getByRole(AriaRole.MENUITEM).locator("a").first().click();
    }

    public void productDelete() {
        page.getByPlaceholder("Delete").fill("Delete");
    }

    public void productDeleteBtn() {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
                .setName("Delete")).click();
    }
}
