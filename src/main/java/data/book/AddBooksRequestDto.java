package data.book;

import lombok.Data;

import java.util.List;

@Data
public class AddBooksRequestDto {

    private String userId;
    private List<IsbnDto> collectionOfIsbns;
}
