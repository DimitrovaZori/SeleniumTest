package githubtesting.pages;

import githubtesting.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SettingsVisibilityPart extends BasePage {

    @FindBy(css = "div[class='mb-0']")
    private WebElement currentVisibility;

    @FindBy(id = "visibility_menu-text")
    private WebElement buttonChangeVisibility;

    @FindBy(css = ".js-repo-change-visibility-button")
    private WebElement selectChangeVisibility;

    @FindBy(css = "#repo-visibility-proceed-button-public")
    private WebElement proceedButtonChange;

    @FindBy(id = "visibility-menu-dialog-public-title")
    private WebElement textConfirmationPublic;

    public String checkCurrentVisibility() {
        return currentVisibility.getAttribute("innerText");
    }

    public void proceedChangeVisibility() {
        buttonChangeVisibility.click();
        selectChangeVisibility.click();
        System.out.println(proceedButtonChange.getText());
        proceedButtonChange.click();
        proceedButtonChange.click();
    }

    public String messagePublicConfirm() {
        return textConfirmationPublic.getAttribute("innerText");
    }

    public void confirmChangePublic() {
        proceedButtonChange.click();

    }


}
