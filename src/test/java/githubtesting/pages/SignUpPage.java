package githubtesting.pages;

import githubtesting.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPage extends BasePage {

    @FindBy(css = "input[id = 'email']")
    private WebElement email;

    @FindBy(css = "p[id='email-err']")
    private WebElement errorEmail;

    @FindBy(css = "button[data-continue-to= 'password-container']")
    private WebElement buttonContinuePassword;

    @FindBy(css = "button[data-continue-to= 'username-container']")
    private WebElement buttonContinueUsername;

    @FindBy(css = "input[id = 'password']")
    private WebElement password;

    @FindBy(css = "p.password-validity-summary")
    private WebElement errorPassword;


    public void enterEmail(String mail) {
        email.sendKeys(mail);
    }

    public String errorMessageEmail() {
        return errorEmail.getAttribute("innerText");
    }

    public void setButtonContinuePassword() {
        buttonContinuePassword.click();
    }


    public void enterPassword(String pass) {
        password.sendKeys(pass);
    }

    public String messagePasswordError() {
        return errorPassword.getText();
    }

    public void setButtonContinueUsername() {
        buttonContinueUsername.click();
    }

}
