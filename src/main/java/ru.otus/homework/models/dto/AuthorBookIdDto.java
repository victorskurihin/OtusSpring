package ru.otus.homework.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.homework.models.Author;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorBookIdDto extends AuthorDto
{
    private String bookId;

    public AuthorBookIdDto(Author author)
    {
        super(author);
        bookId = "0";
    }
}
