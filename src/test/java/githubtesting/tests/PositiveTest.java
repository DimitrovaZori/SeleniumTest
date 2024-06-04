package githubtesting.tests;

import driver.DriverFactory;
import githubtesting.base.MainSetTest;
import githubtesting.pages.HeaderComponents;
import githubtesting.pages.SignUpPage;
import githubtesting.pages.SignUpUsernamePart;
import githubtesting.pages.VerifyPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class PositiveTest extends MainSetTest {

    HeaderComponents headerComponents;
    SignUpPage signUpPage;
    SignUpUsernamePart signUpUsernamePart;
    VerifyPage verifyPage;
    SoftAssert softAssert;

    @Test
    public void newRegistrationDetails() {

        headerComponents = new HeaderComponents();
        verifyPage = new VerifyPage();
        softAssert = new SoftAssert();

        headerComponents.clickSignUp();

        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(80));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[id = 'email']")));

        signUpPage = new SignUpPage();
        signUpPage.enterEmail("sunset@yahoo.uk");

        WebDriverWait waiter = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(80));
        waiter.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-continue-to= 'password-container']")));

        signUpPage.setButtonContinuePassword();
        signUpPage.enterPassword("intheMorningearly");
        softAssert.assertEquals(signUpPage.messagePasswordError(), "Password is strong");

        signUpPage.setButtonContinueUsername();

        signUpUsernamePart = new SignUpUsernamePart();
        signUpUsernamePart.setInputUsername("Wheniscalm");

        softAssert.assertEquals(signUpUsernamePart.inputValidUsername(), "Input is now valid.");

        WebDriverWait next = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(80));
        next.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-continue-to='opt-in-container']")));

        signUpUsernamePart.setButtonContinueRegistration();

        WebDriverWait submit = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(80));
        submit.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-continue-to='captcha-and-submit-container']")));

        signUpUsernamePart.setButtonConfirmRegistration();

        //Verification part

        softAssert.assertEquals(verifyPage.textWelcomeMessage(), "Welcome to GitHub!Let’s begin the adventure");
        softAssert.assertEquals(verifyPage.textVerifyAccount(), "Verify your account");

        //frames
        DriverFactory.getDriver().switchTo().frame(0);
        WebElement verification = DriverFactory.getDriver().findElement(By.cssSelector("[title = 'Verification challenge']"));
        DriverFactory.getDriver().switchTo().frame(verification);
        DriverFactory.getDriver().switchTo().frame("game-core-frame");

        DriverFactory.getDriver().findElement(By.cssSelector("[data-theme= 'home.verifyButton']")).click();

        //main web page
        DriverFactory.getDriver().switchTo().defaultContent();
        softAssert.assertEquals(verifyPage.textWelcomeMessage(), "Welcome to GitHub!Let’s begin the adventure");
        System.out.println(verifyPage.textWelcomeMessage());

        softAssert.assertAll();
    }


}
