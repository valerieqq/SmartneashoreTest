package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookStorePage extends MainMenu {

    @FindBy(id = "login")
    private WebElement loginButton;
    @FindBy(id = "searchBox")
    private WebElement searchBoxInput;
    @FindBy(xpath = "//button[text()='Log out']")
    private WebElement logoutButton;

    public BookStorePage() {
        PageFactory.initElements(driver, this);
    }

}
