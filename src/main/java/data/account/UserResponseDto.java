package data.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import data.book.BookResponseDto;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponseDto {

    private String userID;
    private String username;
    private List<BookResponseDto> books;
}
