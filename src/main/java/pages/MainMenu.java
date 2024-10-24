package pages;

import core.BasePage;
import helpers.WebElementsHelper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainMenu extends BasePage {

    @FindBy(xpath = "//span[text()='Login']")
    private WebElement loginButton;
    @FindBy(xpath = "//span[text()='Book Store']")
    private WebElement bookStoreButton;
    @FindBy(xpath = "//span[text()='Profile']")
    private WebElement profileButton;

    public MainMenu() {
        PageFactory.initElements(driver, this);
    }

    public LoginPage navigateToLoginPage() {
        WebElementsHelper.clickElement(loginButton);
        return new LoginPage();
    }

    public BookStorePage navigateToBookStorePage() {
        WebElementsHelper.clickElement(bookStoreButton);
        return new BookStorePage();
    }

    public ProfilePage navigateToProfilePage() {
        WebElementsHelper.clickElement(profileButton);
        return new ProfilePage();
    }
}
