package githubtesting.pages;

import githubtesting.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class VerifyPage extends BasePage {

    @FindBy(css = "[data-target='typing-effect.content']")
    private WebElement messageWelcome;

    @FindBy(css = "div.signup-text-prompt")
    private WebElement verifyAccount;

    public String textWelcomeMessage() {
        return messageWelcome.getAttribute("textContent");
    }

    public String textVerifyAccount() {
        return verifyAccount.getAttribute("innerText");
    }


}
