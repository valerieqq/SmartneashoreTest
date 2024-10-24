import data.account.AccountRequestDto;
import data.account.GenerateTokenResponseDto;
import data.account.UserResponseDto;
import data.book.AddBooksRequestDto;
import data.book.BookResponseDto;
import data.book.BooksListResponseDto;
import data.book.IsbnDto;
import org.junit.After;
import org.junit.Test;
import services.account.AccountService;
import services.book.BookService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertArrayEquals;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class BackendTest {
    private AccountService accountService;
    private String token;
    private String userId;

    @After
    public void tearDown() throws IOException {
        accountService.deleteUser(userId, token);
    }

    @Test
    public void addBooksToUserTest() throws IOException {
        // Create user and recieve the token
        createUserAndGenerateToken();

        BookService bookService = new BookService();
        // Send the request to get the list of books
        BooksListResponseDto booksListResponseDto = bookService.getBooksList(token);
        // Verify the list of books is not empty
        assertTrue(booksListResponseDto.getBooks().size() > 0, "The list is empty!");

        // Filter the list of books by the publisher
        booksListResponseDto.setBooks(booksListResponseDto.getBooks().stream().filter(book -> book.getPublisher()
                .equals("No Starch Press")).collect(Collectors.toList()));

        // Create model for the request to add books to the profile
        List<IsbnDto> isbns = new ArrayList<>();
        booksListResponseDto.getBooks().forEach(book -> isbns.add(new IsbnDto(book.getIsbn())));
        AddBooksRequestDto addBooksRequestDto = new AddBooksRequestDto();
        addBooksRequestDto.setUserId(userId);
        addBooksRequestDto.setCollectionOfIsbns(isbns);

        // Send the request to add books to the profile
        bookService.addBooksToUser(token, addBooksRequestDto);

        // Verify books has been added
        verifyAddedBooks(isbns);
    }

    private void verifyAddedBooks(List<IsbnDto> expectedBooks) throws IOException {
        UserResponseDto userResponseDto = accountService.getUser(userId, token);
        List<IsbnDto> actualBooks = new ArrayList<>();
        for (BookResponseDto books : userResponseDto.getBooks()) {
            IsbnDto isbnDto = new IsbnDto(books.getIsbn());
            actualBooks.add(isbnDto);
        }
        assertArrayEquals(expectedBooks.toArray(), actualBooks.toArray());
    }

    private void createUserAndGenerateToken() throws IOException {
        accountService = new AccountService();

        AccountRequestDto accountRequestDto = createUserCredentialsDto();
        // Send request to create user
        UserResponseDto userResponseDto = accountService.createUser(accountRequestDto);
        // Check user is created
        verifyCreatedUser(userResponseDto, accountRequestDto);
        userId = userResponseDto.getUserID();

        // Send request to generate the token
        GenerateTokenResponseDto generateTokenResponseDto = accountService.generateToken(accountRequestDto);
        // Check token is generated
        verifyGeneratedToken(generateTokenResponseDto);
        token = "Bearer " + generateTokenResponseDto.getToken();
    }

    private void verifyGeneratedToken(GenerateTokenResponseDto actual) {
        assertEquals(actual.getStatus(), "Success", "Status is not 'Success'!");
        assertEquals(actual.getResult(), "User authorized successfully.", "Authorization is not successful!");
        assertNotNull(actual.getToken());
        assertNotNull(actual.getExpires());
    }

    private void verifyCreatedUser(UserResponseDto actual, AccountRequestDto expected) {
        assertEquals(actual.getUsername(), expected.getUserName(), "Usernames are not equal!");
        assertNotNull(actual.getUserID(), "UserId is null!");
        assertTrue(actual.getBooks().isEmpty(), "Books list is not empty!");
    }

    private AccountRequestDto createUserCredentialsDto() {
        AccountRequestDto accountRequestDto = new AccountRequestDto();
        accountRequestDto.setUserName("userForTest011");
        accountRequestDto.setPassword("Qwerty17!");
        return accountRequestDto;
    }
}
