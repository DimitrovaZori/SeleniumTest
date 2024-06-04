package githubtesting.tests;

import driver.DriverFactory;
import githubtesting.base.MainSetTest;
import githubtesting.pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class VisibilityChangeTest extends MainSetTest {

    HeaderComponents headerComponents;
    LoginPage loginPage;
    HomeAccountPage homeAccountPage;
    SettingsPage settingsPage;

    @Test
    public void changeVisibilityAccount() {

        headerComponents = new HeaderComponents();
        loginPage = new LoginPage();
        homeAccountPage = new HomeAccountPage();
        settingsPage = new SettingsPage();

        headerComponents.clickSignInButton();

        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("octicon-mark-github")));

        loginPage.loginAccountAs("dimitrova_z@abv.bg", "imagine-24h");
        homeAccountPage.newRepoValidation("November");

        homeAccountPage.setButtonCreateRepos();
        settingsPage.selectSettingsTab();

        SettingsVisibilityPart settingsVisibilityPart = new SettingsVisibilityPart();
        assertEquals(settingsVisibilityPart.checkCurrentVisibility(), "This repository is currently private.");
        settingsVisibilityPart.proceedChangeVisibility();

        assertEquals(settingsVisibilityPart.messagePublicConfirm(), "Make DimitrovaVarna/November public");

        settingsVisibilityPart.confirmChangePublic();
        assertEquals(settingsVisibilityPart.checkCurrentVisibility(), "This repository is currently public.");

        settingsPage.deleteRepo();
        WebDriverWait message = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(40));
        WebElement successful = message.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.js-flash-alert")));
        System.out.println(successful.getText());

    }


}
