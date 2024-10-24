package pages;

import helpers.WebElementsHelper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends MainMenu {

    @FindBy(xpath = "//input[@id='userName']")
    private WebElement usernameInput;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordInput;

    @FindBy(id = "login")
    private WebElement loginButton;

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    public ProfilePage login(String username, String password) {
        WebElementsHelper.setInputField(usernameInput, username);
        WebElementsHelper.setInputField(passwordInput, password);
        loginButton.click();
        return new ProfilePage();
    }
}
