package githubtesting.pages;

import githubtesting.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SettingsPage extends BasePage {

    @FindBy(id = "settings-tab")
    private WebElement tabSettings;

    @FindBy(name = "new_name")
    private WebElement newName;

    @FindBy(css = "button.flex-self-end")
    private WebElement buttonRename;

    @FindBy(css = "span.Label--secondary")
    private WebElement labelPublic;

    @FindBy(css = "strong.flex-self-stretch")
    private WebElement repoNameCheck;

    @FindBy(id = "dialog-show-repo-delete-menu-dialog")
    private WebElement buttonDeleteRepo;

    @FindBy(css = "button#repo-delete-proceed-button")
    private WebElement buttonProceedDelete;

    @FindBy(xpath = "//div[@aria-label='Effects of deleting this repository']//p")
    private WebElement repoName;

    @FindBy(css = "input#verification_field")
    private WebElement fieldConfirm;

    @FindBy(css = "div.js-flash-alert")
    private WebElement successfulMessage;


    public void selectSettingsTab() {
        tabSettings.click();
    }

    public String checkPublic() {
        return labelPublic.getAttribute("textContent");
    }

    public void renameRepos(String renameRepos) {
        newName.clear();
        newName.sendKeys(renameRepos);
    }

    public void setButtonRename() {
        buttonRename.click();
    }

    public String checkRepoName() {
        return repoNameCheck.getAttribute("innerText");
    }

    public void deleteRepo() {
        buttonDeleteRepo.click();
        buttonProceedDelete.click();
        buttonProceedDelete.click();
        fieldConfirm.sendKeys(repoName.getText());
        buttonProceedDelete.click();
    }

}
