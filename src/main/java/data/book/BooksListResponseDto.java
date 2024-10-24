package data.book;

import lombok.Data;

import java.util.List;

@Data
public class BooksListResponseDto {

    List<BookResponseDto> books;
}
