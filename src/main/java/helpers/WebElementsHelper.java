package helpers;

import core.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class WebElementsHelper extends BasePage {

    public static void setInputField(WebElement webElement, String value) {
        webElement.clear();
        webElement.sendKeys(value);
    }

    public static void clickElement(WebElement webElement) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", webElement);
        Actions action = new Actions(driver);
        action.moveToElement(webElement).click().perform();
    }
}
