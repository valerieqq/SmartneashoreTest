package services.account;

import data.MessageResponseDto;
import data.account.AccountRequestDto;
import data.account.GenerateTokenResponseDto;
import data.account.UserResponseDto;
import rest.RetrofitService;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class AccountService extends RetrofitService {

    private final AccountAPI accountAPI;

    public AccountService() {
        accountAPI = createService(AccountAPI.class);
    }

    public GenerateTokenResponseDto generateToken(AccountRequestDto accountRequestDto) throws IOException {
        Call<GenerateTokenResponseDto> call = accountAPI.generateToken(accountRequestDto);
        Response<GenerateTokenResponseDto> response = call.execute();
        return response.body();
    }

    public String getToken(AccountRequestDto accountRequestDto) throws IOException {
        GenerateTokenResponseDto response = generateToken(accountRequestDto);
        return "Bearer " + response.getToken();
    }

    public UserResponseDto getUser(String userUUID, String token) throws IOException {
        Call<UserResponseDto> call = accountAPI.getUser(userUUID, token);
        return call.execute().body();
    }

    public UserResponseDto createUser(AccountRequestDto accountRequestDto) throws IOException {
        Call<UserResponseDto> call = accountAPI.createUser(accountRequestDto);
        Response<UserResponseDto> response = call.execute();
        return response.body();
    }

    public MessageResponseDto deleteUser(String userUUID, String token) throws IOException {
        Call<MessageResponseDto> call = accountAPI.deleteUser(userUUID, token);
        Response<MessageResponseDto> response = call.execute();
        return response.body();
    }
}
