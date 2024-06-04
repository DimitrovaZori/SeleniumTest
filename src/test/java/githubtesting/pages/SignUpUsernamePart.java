package githubtesting.pages;

import githubtesting.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpUsernamePart extends BasePage {

    @FindBy(css = "input#login")
    private WebElement inputUsername;

    @FindBy(xpath = "//p[@id='login-err']/div/div")
    private WebElement errorUsername;

    @FindBy(css = "p#input-valid")
    private WebElement validUsername;

    @FindBy(css = "[data-continue-to='opt-in-container']")
    private WebElement buttonContinueRegistration;


    @FindBy(css = "input#opt_in")
    private WebElement mailReferenceOption;

    @FindBy(css = "[data-continue-to='captcha-and-submit-container']")
    private WebElement buttonConfirmRegistration;

    public void setInputUsername(String user) {
        inputUsername.sendKeys(user);
    }

    public String messageUsernameError() {
        return errorUsername.getAttribute("outerText");
    }

    public String inputValidUsername() {
        return validUsername.getAttribute("innerText");
    }

    public void setButtonContinueRegistration() {
        buttonContinueRegistration.click();
    }

    public void setButtonConfirmRegistration() {
        mailReferenceOption.click();
        buttonConfirmRegistration.click();
    }

}
