package githubtesting.pages;

import githubtesting.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.Keys.ENTER;

public class HomeAccountPage extends BasePage {

    @FindBy(id = "repository[name]")
    private WebElement newRepository;

    @FindBy(css = "[data-target= 'primer-text-field.validationMessageElement']")
    private WebElement validationRepoName;

    @FindBy(id = "repository[visibility]_public")
    private WebElement visibilityPublic;

    @FindBy(css = "button[value = 'Create a new repository']")
    private WebElement buttonCreateRepos;

    @FindBy(css = "input[placeholder = 'Find a repository…']")
    private WebElement findFieldRepo;

    @FindBy(xpath = "//div[@class='wb-break-word']/a")
    private WebElement foundRepo;

    //The name of repo is unique and if it is full name it is the first.

    // //ul[@data-filterable-for='dashboard-repos-filter-left']//li

    public void setNewRepository(String reposName) {
        newRepository.clear();
        newRepository.sendKeys(reposName);
        visibilityPublic.click();
        buttonCreateRepos.click();
    }

    public void setFindRepository(String repoName) {
        findFieldRepo.clear();
        findFieldRepo.sendKeys(repoName);
        foundRepo.click();
    }

    public void newRepoValidation(String nameRepo) {
        newRepository.clear();
        newRepository.sendKeys(nameRepo);
        System.out.println(validationRepoName.getText());
    }


    public void setButtonCreateRepos() {
        buttonCreateRepos.click();
    }

}
