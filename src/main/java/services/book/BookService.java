package services.book;

import data.book.AddBooksRequestDto;
import data.book.BooksListResponseDto;
import rest.RetrofitService;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class BookService extends RetrofitService {

    private final BookAPI bookAPI;

    public BookService() {
        bookAPI = createService(BookAPI.class);
    }

    public BooksListResponseDto getBooksList(String token) throws IOException {
        Call<BooksListResponseDto> call = bookAPI.getBooks(token);
        Response<BooksListResponseDto> response = call.execute();
        return response.body();
    }

    public BooksListResponseDto addBooksToUser(String token, AddBooksRequestDto addBooksRequestDto) throws IOException {
        Call<BooksListResponseDto> call = bookAPI.addBooksToUser(token, addBooksRequestDto);
        Response<BooksListResponseDto> response = call.execute();
        return response.body();
    }
}
