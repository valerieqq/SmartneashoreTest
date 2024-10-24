import core.BaseTest;
import enums.TableHeaderEnum;
import helpers.TableElementHelper;
import org.junit.Test;
import pages.BookStorePage;
import pages.LoginPage;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class FrontendTest extends BaseTest {

    private static final String USERNAME = "vubeivolk";
    private static final String PASSWORD = "Qwerty1!";

    @Test
    public void frontendTest() {

        TableElementHelper tableElementHelper = new TableElementHelper();
        // Get all books from the Book Store
        List<String> bookStorePublishers = tableElementHelper.getColumnValues(TableHeaderEnum.PUBLISHER);
        List<String> bookStoreAuthors = tableElementHelper.getColumnValues(TableHeaderEnum.AUTHOR);
        List<String> bookStoreTitles = tableElementHelper.getColumnValues(TableHeaderEnum.TITLE);

        // Navigate to login page and login to the profile
        BookStorePage bookStorePage = new BookStorePage();
        LoginPage loginPage = bookStorePage.navigateToLoginPage();
        loginPage.login(USERNAME, PASSWORD);
        List<String> profileBookPublishers = tableElementHelper.getColumnValues(TableHeaderEnum.PUBLISHER);
        List<String> profileBookAuthors = tableElementHelper.getColumnValues(TableHeaderEnum.AUTHOR);
        List<String> profileBookTitles = tableElementHelper.getColumnValues(TableHeaderEnum.TITLE);

        // Assert books from the Book Store and Profile
        assertEquals(profileBookAuthors.size(), 2, "Profile books count is not as expected!");
        assertTrue(bookStorePublishers.stream().anyMatch(profileBookPublishers::contains));
        assertTrue(bookStoreAuthors.stream().anyMatch(profileBookAuthors::contains));
        assertTrue(bookStoreTitles.stream().anyMatch(profileBookTitles::contains));
    }
}
