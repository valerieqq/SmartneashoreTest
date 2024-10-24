package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage extends MainMenu {

    @FindBy(id = "searchBox")
    private WebElement searchBoxInput;
    @FindBy(xpath = "//button[text()='Log out']")
    private WebElement logoutButton;

    public ProfilePage() {
        PageFactory.initElements(driver, this);
    }
}
