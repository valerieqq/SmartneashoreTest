package services.account;

import data.MessageResponseDto;
import data.account.AccountRequestDto;
import data.account.GenerateTokenResponseDto;
import data.account.UserResponseDto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AccountAPI {

    @POST("/Account/v1/GenerateToken")
    Call<GenerateTokenResponseDto> generateToken(@Body AccountRequestDto accountRequestDto);

    @GET("/Account/v1/User/{UUID}")
    Call<UserResponseDto> getUser(@Path("UUID") String userUUID, @Header("Authorization") String token);

    @POST("/Account/v1/User")
    Call<UserResponseDto> createUser(@Body AccountRequestDto accountRequestDto);

    @DELETE("/Account/v1/User/{UUID}")
    Call<MessageResponseDto> deleteUser(@Path("UUID") String userUUID, @Header("Authorization") String token);
}
