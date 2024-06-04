package githubtesting.pages;

import githubtesting.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderComponents extends BasePage {

    @FindBy(className = "HeaderMenu-link--sign-up")
    private WebElement signUpButton;

    @FindBy(className = "HeaderMenu-link--sign-in")
    private WebElement signInButton;


    public void clickSignUp() {
        signUpButton.click();
    }

    public void clickSignInButton() {
        signInButton.click();
    }


}
