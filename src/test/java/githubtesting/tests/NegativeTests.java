package githubtesting.tests;

import driver.DriverFactory;
import githubtesting.base.MainSetTest;
import githubtesting.pages.SignUpUsernamePart;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import githubtesting.pages.HeaderComponents;
import githubtesting.pages.SignUpPage;
import utils.CsvReader;

import java.io.IOException;
import java.time.Duration;

import static org.testng.Assert.assertEquals;


public class NegativeTests extends MainSetTest {
    HeaderComponents headerComponents;
    SignUpPage signUpPage;
    SignUpUsernamePart signUpUsernamePart;

    @DataProvider(name = "login-data-email")
    public static Object[][] dataProviderFromScvFile() throws IOException {
        return CsvReader.readCsvFile("src/test/resources/invalidmail-data.csv");
    }


    @Test(dataProvider = "login-data-email")
    public void newAccountLoginInvalidMail(String mail, String message) {
        headerComponents = new HeaderComponents();
        headerComponents.clickSignUp();

        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(80));
        WebElement inputEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[id = 'email']")));

        signUpPage = new SignUpPage();
        signUpPage.enterEmail(mail);

        WebDriverWait waitMessage = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(30));
        WebElement messageEmail = waitMessage.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p[id='email-err']")));

        assertEquals(signUpPage.errorMessageEmail(), message);
    }

    @DataProvider(name = "login-data-password")
    public static Object[][] dataProviderFromScv() throws IOException {
        return CsvReader.readCsvFile("src/test/resources/invalidpassword-data.csv");
    }

    @Test(dataProvider = "login-data-password")
    public void newAccountLoginInvalidPassword(String mail, String pass, String message) {
        headerComponents = new HeaderComponents();
        headerComponents.clickSignUp();

        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(80));
        WebElement fieldEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[id = 'email']")));

        signUpPage = new SignUpPage();
        signUpPage.enterEmail(mail);

        WebDriverWait waiter = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(80));
        WebElement fieldPass = waiter.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-continue-to= 'password-container']")));
        signUpPage.setButtonContinuePassword();

        signUpPage.enterPassword(pass);
        assertEquals(signUpPage.messagePasswordError(), message);

    }

    @DataProvider(name = "login-data-username")
    public static Object[][] dataProviderScv() throws IOException {
        return CsvReader.readCsvFile("src/test/resources/invalidusername-data.csv");
    }

    @Test(dataProvider = "login-data-username")
    public void newAccountInvalidUsername(String mail, String pass, String user, String error) {
        headerComponents = new HeaderComponents();
        headerComponents.clickSignUp();

        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(80));
        WebElement enterEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[id = 'email']")));

        signUpPage = new SignUpPage();
        signUpPage.enterEmail(mail);

        WebDriverWait waiter = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(80));
        WebElement fieldPass = waiter.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-continue-to= 'password-container']")));

        signUpPage.setButtonContinuePassword();
        signUpPage.enterPassword(pass);
        assertEquals(signUpPage.messagePasswordError(), "Password is strong");

        signUpPage.setButtonContinueUsername();

        signUpUsernamePart = new SignUpUsernamePart();
        signUpUsernamePart.setInputUsername(user);

        assertEquals(signUpUsernamePart.messageUsernameError(), error);
        System.out.println(signUpUsernamePart.messageUsernameError());


    }

}
