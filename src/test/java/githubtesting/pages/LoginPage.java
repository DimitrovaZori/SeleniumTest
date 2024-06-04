package githubtesting.pages;

import githubtesting.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "login_field")
    private WebElement userField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(name = "commit")
    private WebElement signInButton;

    public String sizeButtonSignIn() {
        return signInButton.getCssValue("font-size");
    }

    public void loginAccountAs(String user, String pass) {
        userField.clear();
        userField.sendKeys(user);
        passwordField.clear();
        passwordField.sendKeys(pass);
        signInButton.click();
    }


}
