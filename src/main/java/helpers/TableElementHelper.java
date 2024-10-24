package helpers;

import core.BasePage;
import enums.TableHeaderEnum;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TableElementHelper extends BasePage {

    @FindBy(xpath = "//div[@class='rt-table']")
    private WebElement table;
    @FindBy(xpath = "//div[@role='columnheader']")
    private List<WebElement> headerRow;

    public TableElementHelper() {
        PageFactory.initElements(driver, this);
    }

    public List<String> getColumnValues(TableHeaderEnum tableHeader) {
        List<String> tableHeaders = new ArrayList<>();
        for (WebElement header : headerRow) {
            String tableHeaderName = header.getText();
            tableHeaders.add(tableHeaderName);
        }
        int columnIndex = tableHeaders.indexOf(tableHeader.getHeaderName());

        List<WebElement> columnsData = table.findElements(
                By.xpath(String.format("//div[@role='gridcell'][%s+1]", columnIndex)));
        List<String> values = new ArrayList<>();
        for (WebElement columnData : columnsData) {
            values.add(columnData.getText());
        }
        values.removeAll(Collections.singletonList(" "));
        return values;
    }

}
