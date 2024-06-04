package githubtesting.tests;

import driver.DriverFactory;
import githubtesting.base.MainSetTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import githubtesting.pages.HeaderComponents;
import githubtesting.pages.HomeAccountPage;
import githubtesting.pages.LoginPage;
import githubtesting.pages.SettingsPage;

import java.time.Duration;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ManageAccountTests extends MainSetTest {

    HeaderComponents headerComponents;
    LoginPage loginPage;
    HomeAccountPage homeAccountPage;
    SettingsPage settingsPage;

    @Test(priority = 0)
    public void RepoManageCreate() {
        SoftAssert softAssert = new SoftAssert();
        headerComponents = new HeaderComponents();
        loginPage = new LoginPage();
        headerComponents.clickSignInButton();

        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(40));
        WebElement avatar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("octicon-mark-github")));
        softAssert.assertTrue(avatar.isDisplayed());

        softAssert.assertEquals(loginPage.sizeButtonSignIn(), "14px");
        loginPage.loginAccountAs("dimitrova_z@abv.bg", "imagine-24h");
        //Details - dimitrova_z@abv.bg, pass imagine-24h, user DimitrovaVarna

        //Create
        homeAccountPage = new HomeAccountPage();
        homeAccountPage.setNewRepository("Exam");

        softAssert.assertAll();

    }

    @Test(priority = 1)
    public void RepoManageRename() {

        headerComponents = new HeaderComponents();
        headerComponents.clickSignInButton();

        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(40));
        WebElement avatar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("octicon-mark-github")));

        loginPage = new LoginPage();
        loginPage.loginAccountAs("dimitrova_z@abv.bg", "imagine-24h");

        //Rename
        homeAccountPage = new HomeAccountPage();
        homeAccountPage.setFindRepository("DimitrovaVarna/Exam");

        settingsPage = new SettingsPage();
        settingsPage.selectSettingsTab();
        assertEquals(settingsPage.checkPublic(), "Public");

        settingsPage.renameRepos("RenameExam");

        WebDriverWait waitField = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(40));
        WebElement fieldRename = waitField.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.flex-self-end")));

        settingsPage.setButtonRename();
        assertEquals(settingsPage.checkRepoName(), "RenameExam");
    }


    @Test(priority = 2)
    public void RepoManageDelete() {

        headerComponents = new HeaderComponents();
        loginPage = new LoginPage();
        settingsPage = new SettingsPage();

        headerComponents.clickSignInButton();

        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("octicon-mark-github")));

        loginPage.loginAccountAs("dimitrova_z@abv.bg", "imagine-24h");

        homeAccountPage = new HomeAccountPage();
        homeAccountPage.setFindRepository("DimitrovaVarna/RenameExam");
        assertEquals(settingsPage.checkRepoName(), "RenameExam");

        //Delete
        settingsPage.selectSettingsTab();
        settingsPage.deleteRepo();

        WebDriverWait message = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(40));
        WebElement successful = message.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.js-flash-alert")));
        assertTrue(successful.isDisplayed());
        System.out.println(successful.getText());
    }
}






