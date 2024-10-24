package services.book;

import data.book.AddBooksRequestDto;
import data.book.BooksListResponseDto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface BookAPI {

    @GET("/BookStore/v1/Books")
    Call<BooksListResponseDto> getBooks(@Header("Authorization") String token);

    @POST("/BookStore/v1/Books")
    Call<BooksListResponseDto> addBooksToUser(@Header("Authorization") String token, @Body AddBooksRequestDto addBooksRequestDto);

}
